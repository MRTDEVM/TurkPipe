package org.schabi.newpipe.player.helper;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SponsorBlockHelper {
    private static final String TAG = "SponsorBlockHelper";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    public static class Segment {
        public final double start;
        public final double end;
        public final String category;

        public Segment(final double start, final double end, final String category) {
            this.start = start;
            this.end = end;
            this.category = category;
        }
    }

    private static final List<Segment> CURRENT_SEGMENTS = new ArrayList<>();
    private static String currentVideoId = "";

    private SponsorBlockHelper() {
        // Utility class
    }

    public static void fetchSegments(final String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            synchronized (CURRENT_SEGMENTS) {
                CURRENT_SEGMENTS.clear();
                currentVideoId = "";
            }
            return;
        }

        EXECUTOR.execute(() -> {
            Log.d(TAG, "Fetching SponsorBlock segments for: " + videoId);
            final String url = "https://sponsor.ajay.app/api/skipSegments?videoID=" + videoId
                    + "&categories=[\"sponsor\",\"intro\",\"outro\",\"interaction\",\"selfpromo\"]";

            final Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = CLIENT.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    final ResponseBody body = response.body();
                    if (body != null) {
                        final String jsonStr = body.string();
                        final JSONArray jsonArray = new JSONArray(jsonStr);
                        final List<Segment> newSegments = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            final JSONObject obj = jsonArray.getJSONObject(i);
                            final JSONArray segmentArr = obj.getJSONArray("segment");
                            final double start = segmentArr.getDouble(0);
                            final double end = segmentArr.getDouble(1);
                            final String category = obj.getString("category");
                            newSegments.add(new Segment(start, end, category));
                            Log.d(TAG, "Found segment: " + start + " to " + end
                                    + " (" + category + ")");
                        }
                        synchronized (CURRENT_SEGMENTS) {
                            CURRENT_SEGMENTS.clear();
                            CURRENT_SEGMENTS.addAll(newSegments);
                            currentVideoId = videoId;
                        }
                    }
                } else {
                    Log.d(TAG, "SponsorBlock request failed (HTTP " + response.code() + ")");
                    synchronized (CURRENT_SEGMENTS) {
                        CURRENT_SEGMENTS.clear();
                        currentVideoId = videoId;
                    }
                }
            } catch (final IOException e) {
                Log.e(TAG, "Failed to fetch segments", e);
                synchronized (CURRENT_SEGMENTS) {
                    CURRENT_SEGMENTS.clear();
                    currentVideoId = videoId;
                }
            } catch (final Exception e) {
                Log.e(TAG, "Parsing error", e);
            }
        });
    }

    public static Segment checkSkip(final long currentPositionMs) {
        final double currentPositionSec = currentPositionMs / 1000.0;
        synchronized (CURRENT_SEGMENTS) {
            for (final Segment segment : CURRENT_SEGMENTS) {
                if (currentPositionSec >= segment.start && currentPositionSec < segment.end) {
                    return segment;
                }
            }
        }
        return null;
    }
}
