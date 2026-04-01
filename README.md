# Weather App (Android - Java)

Kullanıcının girdiği şehre göre gerçek zamanlı hava durumu verilerini sunan, şık ve işlevsel bir Android uygulamasıdır.

## ✨ Özellikler
- **Hava Durumu Verileri:** [WeatherAPI](https://www.weatherapi.com/) üzerinden güncel sıcaklık, nem, rüzgar hızı ve UV indeksi bilgilerini çeker.
- **Görsel Bildirimler:** Hava durumuna uygun ikonlar Picasso kütüphanesi ile dinamik olarak yüklenir.
- **Kullanıcı Yönetimi:** SQLite veritabanı kullanılarak basit bir Login ve Register sistemi içerir.
- **Hata Yönetimi:** İnternet bağlantısı veya hatalı şehir girişleri için kullanıcı bilgilendirmeleri mevcuttur.

## 🛠 Kullanılan Teknolojiler
- **Dil:** Java
- **Networking:** Volley (JSON işlemleri için)
- **Görüntüleme:** Picasso (URL üzerinden resim yükleme)
- **Veritabanı:** SQLite

## 🚀 Kurulum
1. Bu depoyu klonlayın: `git clone [DEPO_LINKINIZ]`
2. `MainActivity.java` içerisindeki `apiKey` değişkenine kendi API anahtarınızı ekleyin.
3. Projeyi Android Studio ile derleyin.