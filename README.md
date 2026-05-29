<p align="center">
  <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" width="150" alt="TürkPipe Logo">
</p>

<h1 align="center"><b>TürkPipe</b></h1>
<h4 align="center">Android için Reklamsız, Bağımsız ve Gelişmiş YouTube İstemcisi</h4>

<p align="center">
  <a href="https://github.com/tr-turkpipe/TurkPipe/releases"><img src="https://img.shields.io/github/release/tr-turkpipe/TurkPipe.svg?color=blue&style=flat-square" alt="GitHub Release"></a>
  <a href="https://www.gnu.org/licenses/gpl-3.0"><img src="https://img.shields.io/badge/License-GPL%20v3-blue.svg?style=flat-square" alt="License"></a>
  <a href="https://android.com"><img src="https://img.shields.io/badge/Platform-Android%206.0+-brightgreen.svg?style=flat-square" alt="Platform"></a>
</p>

<p align="center">
  <b>TürkPipe</b>, resmi YouTube uygulamasının tüm kısıtlamalarından ve sinir bozucu reklamlarından arındırılmış, arka planda oynatma, indirme ve gelişmiş oynatıcı jestleri sunan bağımsız, açık kaynaklı bir Android istemcisidir.
</p>

---

## ✨ TürkPipe Özellikleri

### 🛡️ 1. Gelişmiş Reklamsızlık ve SponsorBlock
* **Otomatik Sponsor Atlama:** Videoların içindeki sponsorlu reklam alanlarını, giriş (intro) ve bitiş (outro) animasyonlarını, etkileşim hatırlatıcılarını ("abone olun" vb.) milisaniyeler içinde algılar ve otomatik olarak atlar.
* **Kategori Filtreleri:** Ayarlar menüsünden hangi SponsorBlock kategorilerinin otomatik atlanacağını, hangilerinin manuel geçileceğini kişiselleştirebilirsiniz.

### 👎 2. Return YouTube Dislike (RYD) Entegrasyonu
* **Beğenmeme Sayıları Geri Geldi!** YouTube'un kaldırdığı beğenmeme (dislike) istatistiklerini Return YouTube Dislike API'si yardımıyla video detay sayfalarına anlık ve doğru olarak geri yükler.

### 🖤 3. Saf AMOLED Derin Siyah Tema
* Göz yormayan saf derin siyah zemin (`#000000`) ve fütüristik parlayan elektrik mavisi vurgularla donatılmış sade ve şık arayüz tasarımı.

### 👆 4. Ekran Kaydırma Jestleri (Swipe Gestures)
* MX Player veya VLC Player konforunda tek parmakla kontrol:
  * Ekranın **sol** tarafını yukarı/kaydırarak **parlaklığı**,
  * Ekranın **sağ** tarafını yukarı/aşağı kaydırarak **ses seviyesini** değiştirebilirsiniz.

### 🍿 5. Kaliteli ve Kısıtlamasız Oynatım
* **1080p Varsayılan Çözünürlük:** Tüm videolar ilk saniyeden itibaren otomatik olarak 1080p kalitesinde başlar.
* **ANDROID_VR İstemci Emülasyonu:** YouTube'un getirdiği yavaşlatma (SABR throttling) ve poToken engellerini aşmak için Oculus VR cihazı taklidi yapar. Canlı yayınlar ve videolar takılmadan, en yüksek hızda akar.

### 🔒 6. Tam Bağımsızlık ve Gizlilik
* Orijinal NewPipe güncelleme sunucuları tamamen devre dışı bırakılmıştır. Uygulamanız asla üzerine orijinal NewPipe sürümü yazılarak bozulmaz.
* Google Play Hizmetleri gerektirmez; Huawei, Xiaomi veya özel ROM yüklü cihazlarda da sorunsuz çalışır. Verilerinizi asla sunuculara göndermez, gizliliğinizi ciddiye alır.

---

## 📸 Ekran Görüntüleri
*(Yakında eklenecek)*

---

## 🛠️ Kurulum ve Güncelleme

1. **GitHub Releases** sayfasına gidin.
2. En güncel sürümün altında yer alan `.apk` dosyasını (örneğin `TurkPipe-v1.0.0.apk`) cihazınıza indirin.
3. Cihazınızda "Bilinmeyen Kaynaklardan Uygulama Yükleme" iznini aktif ederek APK'yı kurun.
4. **Veri Göçü (Opsiyonel):** Eski uygulamanızdan verilerinizi kurtarmak için `Ayarlar > Yedekle ve Geri Yükle > Veritabanını Dışa Aktar` yapıp TürkPipe uygulamasında içe aktarabilirsiniz.

---

## 💻 Geliştiriciler İçin Derleme (Build)

Projeyi kendi ortamınızda sıfırdan derlemek için:

1. **Gereksinimler:** JDK 21 ve Android SDK yüklü olmalıdır.
2. Projeyi bilgisayarınıza klonlayın veya indirin.
3. Proje dizininde terminali açarak şu komutu çalıştırın:
   ```powershell
   $env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"
   .\gradlew assembleDebug
   ```
4. Derlenen APK dosyasını `app/build/outputs/apk/debug/` klasörü altında bulabilirsiniz.

---

## 📜 Lisans

[![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)](https://www.gnu.org/licenses/gpl-3.0.en.html)  

TürkPipe özgür bir yazılımdır (Free Software). GNU Genel Kamu Lisansı (GPLv3) koşulları altında dağıtılır, geliştirilir ve paylaşılır. Orijinal NewPipe ekibine ve tüm açık kaynak geliştiricilerine teşekkür ederiz.
