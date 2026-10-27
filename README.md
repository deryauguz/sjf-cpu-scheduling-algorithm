SJF İşlemci Zamanlama Algoritması

1. Proje Hakkında

Bu proje, işletim sistemlerinde kullanılan işlemci zamanlama algoritmalarından biri olan SJF (Shortest Job First) algoritmasının kesintisiz olmayan (Non-Preemptive) sürümünü simüle etmek amacıyla Java programlama diliyle geliştirilmiştir.

Uygulama, proseslerin geliş zamanlarını ve çalışma sürelerini bir metin dosyasından okuyarak işlemciye hangi sırayla alınacaklarını belirler. Simülasyon sırasında proseslerin çalışma süreçleri takip edilir ve her proses için bekleme süresi ile ortalama bekleme süresi hesaplanır.

2. Projenin Amacı

Projenin temel amacı, SJF zamanlama algoritmasının çalışma mantığını uygulamalı olarak anlamak ve proseslerin işlemciye atanma sırasının bekleme süreleri üzerindeki etkisini incelemektir.

3. Algoritmanın Çalışma Mantığı

SJF algoritması, çalışmaya hazır durumdaki prosesler arasından çalışma süresi en kısa olanı seçer.

Bu projenin kullandığı kesintisiz olmayan SJF yaklaşımında, bir proses çalışmaya başladıktan sonra tamamlanana kadar işlemciyi kullanır. Çalışma sırasında daha kısa süreli yeni bir proses gelse bile mevcut proses kesilmez.

Algoritma şu adımlarla çalışır:

Proses bilgileri prosesler.txt dosyasından okunur.

Prosesler, geliş zamanlarına göre simülasyon kuyruğuna eklenir.

Hazır kuyruktaki prosesler, çalışma süreleri kısa olandan başlayacak şekilde sıralanır.

Seçilen proses tamamlanana kadar çalıştırılır.

Tamamlanan prosesin başlangıç, bitiş ve bekleme zamanları belirlenir.

Tüm prosesler tamamlandığında her prosesin bekleme süresi ve ortalama bekleme süresi ekrana yazdırılır.

Çalışma süreleri eşit olan proseslerde, geliş zamanı daha erken olan prosese öncelik verilir.

4. Özellikler

Metin dosyasından proses bilgilerinin okunması

Proseslerin geliş zamanlarının takip edilmesi

SJF Non-Preemptive algoritmasına göre proses seçimi

Proseslerin çalışma sürecinin saniye bazında simüle edilmesi

Proses başlangıç ve bitiş zamanlarının hesaplanması

Her proses için bekleme süresinin belirlenmesi

Ortalama bekleme süresinin hesaplanması

Dosya bulunamaması, okuma hataları ve hatalı sayısal değerler için hata yönetimi

5. Kullanılan Teknolojiler

Java:Uygulama mantığının ve simülasyonun geliştirilmesi

Java Collections Framework:Proses listesinin ve bekleme kuyruğunun yönetilmesi

Dosya Okuma İşlemleri:Proses bilgilerinin metin dosyasından alınması

Thread.sleep(): Proses çalışma sürelerinin zaman gecikmeleriyle simüle edilmesi

6. Girdi Dosyası Formatı

Uygulama, proses bilgilerini prosesler.txt adlı dosyadan okur. Her satırda bir prosese ait bilgiler bulunmalıdır.

Dosya formatı şu şekildedir:

ProsesAdi GelisZamani CalismaSuresi

Örnek prosesler.txt içeriği:

P1 0 5
P2 1 3
P3 2 1
P4 4 2

Her satırdaki alanlar şu anlamlara gelir:

Proses adı: Prosesi tanımlayan isim.

Geliş zamanı: Prosesin sisteme geldiği zaman.

Çalışma süresi (Burst Time): Prosesin işlemciye ihtiyaç duyduğu süre.

Dosyadaki değerler boşluk veya sekme karakterleriyle ayrılmalıdır.
