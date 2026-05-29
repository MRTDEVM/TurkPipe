package org.schabi.newpipe.player.helper;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ReturnYouTubeDislikeHelper {
    private static final String TAG = "RYDHelper";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    // Cache to prevent duplicate requests
    private static final ConcurrentHashMap<String, Long> DISLIKE_CACHE =
            new ConcurrentHashMap<>();

    public interface DislikeCallback {
        void onDislikesFetched(long dislikes);
    }

    private ReturnYouTubeDislikeHelper() {
        // Utility class
    }

    public static void fetchDislikes(final String videoId, final DislikeCallback callback) {
        if (videoId == null || videoId.isEmpty()) {
            return;
        }

        if (DISLIKE_CACHE.containsKey(videoId)) {
            callback.onDislikesFetched(DISLIKE_CACHE.get(videoId));
            return;
        }

        EXECUTOR.execute(() -> {
            Log.d(TAG, "Fetching RYD dislikes for: " + videoId);
            final String url = "https://returnyoutubedislikeapi.com/votes?videoId=" + videoId;

            final Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = CLIENT.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    final ResponseBody body = response.body();
                    if (body != null) {
                        final String jsonStr = body.string();
                        final JSONObject obj = new JSONObject(jsonStr);
                        final long dislikes = obj.optLong("dislikes", -1);
                        if (dislikes >= 0) {
                            DISLIKE_CACHE.put(videoId, dislikes);
                            callback.onDislikesFetched(dislikes);
                            Log.d(TAG, "Fetched dislikes: " + dislikes);
                            return;
                        }
                    }
                }
            } catch (final IOException e) {
                Log.e(TAG, "Failed to fetch dislikes", e);
            } catch (final Exception e) {
                Log.e(TAG, "Error parsing dislikes json", e);
            }
            callback.onDislikesFetched(-1);
        });
    }
}
