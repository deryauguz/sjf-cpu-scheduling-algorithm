package _23010310075_Esma_Uguz;
import java.io.*;
import java.util.*;

class Proses {
    String isim;
    int gelisZamani;
    int burstZamani;
    int baslangicZamani;
    int bitisZamani;
    int beklemeZamani;
 
    public Proses(String isim, int gelisZamani, int burstZamani) {
        this.isim = isim;
        this.gelisZamani = gelisZamani;
        this.burstZamani = burstZamani;
    }
}
 
class SJFZamanLayici {
 
    private List<Proses> prosesler;
 
    public SJFZamanLayici(List<Proses> prosesler) {
        this.prosesler = prosesler;
    }
 
    private void kuyruguGuncelle(List<Proses> sira, boolean[] tamamlandi, int zaman, String haricIsim) {
        for (int i = 0; i < prosesler.size(); i++) {
            Proses p = prosesler.get(i);
            if (!tamamlandi[i] && p.gelisZamani == zaman && !sira.contains(p)) {
                if (haricIsim == null || !p.isim.equals(haricIsim)) {
                    sira.add(p);
                    System.out.println(p.isim + " prosesi " + zaman + ". Saniyede kuyruğa girdi.");
                }
            }
        }
    }
 
    public void simulasyonuCalistir() {
        int zaman = 0;
        int tamamlanan = 0;
        int n = prosesler.size();
        boolean[] tamamlandi = new boolean[n];
        List<Proses> sira = new ArrayList<>();
 
        System.out.println("SJF (non-preemptive) Simülasyonu:");
 
        while (tamamlanan < n) {
 
            kuyruguGuncelle(sira, tamamlandi, zaman, null);
 
            if (!sira.isEmpty()) {
            	sira.sort(Comparator.comparingInt((Proses p) -> p.burstZamani)
            	         .thenComparingInt(p -> p.gelisZamani));
                Proses secilen = sira.remove(0);
                secilen.baslangicZamani = zaman;
 
                System.out.println(secilen.isim + " prosesi " + zaman + ". Saniyede çalışmaya başladı.");
 
                for (int t = 1; t <= secilen.burstZamani; t++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    zaman++;
 
                    System.out.println(secilen.isim + " prosesi toplamda " + t + " saniye çalıştı.");
                    kuyruguGuncelle(sira, tamamlandi, zaman, secilen.isim);
                }
 
                secilen.bitisZamani = zaman;
                secilen.beklemeZamani = secilen.baslangicZamani - secilen.gelisZamani;
 
                for (int i = 0; i < n; i++) {
                    if (prosesler.get(i).isim.equals(secilen.isim)) {
                        tamamlandi[i] = true;
                        break;
                    }
                }
                tamamlanan++;
 
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zaman++;
                kuyruguGuncelle(sira, tamamlandi, zaman, null);
            }
        }
 
        StringBuilder sb = new StringBuilder("Bekleme Zamanları: ");
        double toplam = 0;
        for (int i = 0; i < n; i++) {
            Proses p = prosesler.get(i);
            toplam += p.beklemeZamani;
            sb.append(p.isim).append(" ").append(p.beklemeZamani).append(" saniye");
            if (i < n - 1) sb.append(", ");
        }
        System.out.println(sb.toString());
 
        double ortalama = toplam / n;
        String ortalamaStr = String.format(Locale.forLanguageTag("tr"), "%.2f", ortalama).replace(".", ",");
        System.out.println("Ortalama Bekleme Süresi: " + ortalamaStr + " Saniye");
    }
}
 
class DosyaOkuyucu {
 
    public static List<Proses> oku(String dosyaAdi) throws IOException {
        List<Proses> prosesler = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            satir = satir.trim();
            if (satir.isEmpty()) continue;
            String[] parcalar = satir.split("\\s+");
            if (parcalar.length >= 3) {
                prosesler.add(new Proses(parcalar[0],
                        Integer.parseInt(parcalar[1]),
                        Integer.parseInt(parcalar[2])));
            }
        }
        br.close();
        return prosesler;
    }
}

public class _23010310075_Esma_Uguz {

    public static void main(String[] args) {
        String dosyaAdi = "prosesler.txt";
        try {
            List<Proses> prosesler = DosyaOkuyucu.oku(dosyaAdi);
            System.out.println("prosesler.txt dosyası okundu.");
            SJFZamanLayici zamanLayici = new SJFZamanLayici(prosesler);
            zamanLayici.simulasyonuCalistir();
        } catch (FileNotFoundException e) {
            System.out.println("Hata: '" + dosyaAdi + "' dosyası bulunamadı.");
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Dosya formatı hatalı: " + e.getMessage());
        }
    }
}