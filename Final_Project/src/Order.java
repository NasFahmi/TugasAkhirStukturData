// file ini berisi logic yang nanti dipakai
import java.util.*;
import java.time.*;


public class Order {
    // initial variabel yang semua memiliki visibilitas private
    private String nama_barang;
    private String nama_Pengekspor;
    private String nama_Penerima;
    private String diskripsi_barang;
    private int jumlah_barang;
    private int harga_barang;
    private int berat_barang;
    private String negara_asal;
    private String negara_tujuan;
    private String tracking_number;
    private Boolean isBeacukai;
    private double beacukai;
    private double ongkir;

    public String generateNumber() {
        // function unutk membuat tracking number
        Random randomint = new Random();
        int random = randomint.nextInt(100);
        LocalDate date = LocalDate.now();

        String[] negara = new String[2];
        negara = singkatanNegara();
        String negaraAsal = negara[0];
        String negaraTujuan = negara[1];

        int hari = date.getDayOfMonth();
        int bulan = date.getMonthValue();
        int tahun = date.getYear();

        return (negaraAsal + hari + bulan + tahun + random + negaraTujuan).toUpperCase();
    }

    public String[] singkatanNegara() {
        // function untuk mendapatkan 3 huruf awal dari negara yang diinputkan 
        String[] ArrsingkatanNegara = new String[2];
        String getNegaraAsal = getNegara_asal();
        String getNegaraTujuan = getNegara_tujuan();
        String singakatanNegaraAsal = getNegaraAsal.substring(0, 3);
        String singakatanNegaraTujuan = getNegaraTujuan.substring(0, 3);
        ArrsingkatanNegara[0] = singakatanNegaraAsal;
        ArrsingkatanNegara[1] = singakatanNegaraTujuan;
        return ArrsingkatanNegara;
    }

    public double hitungOngkir() {
        // function untuk menghitung ongkir
        double berat = this.berat_barang / 10;// 10%
        double harga = this.harga_barang * 0.075;// 7.5%
        return berat + harga;
    }

    public double hitungBeacukai() {

        // function unguk menghitung beacukai
        double pabean = (this.harga_barang + this.ongkir);
        double BM = pabean * 0.075;// 7.5%
        double PPN = (pabean + BM) / 10;
        return PPN + BM;

    }

    public void printAll() {
        // function untuk mengprint semua detail dari variabel(untuk saat ini hanay digunakan untuk testing)
        System.out.println("==========Detail Barang==========");
        System.out.println("=================================");
        System.out.println("Nama : " + this.nama_barang);
        System.out.println("Nama Pengekspor : " + this.nama_Pengekspor);
        System.out.println("Nama Penerima : " + this.nama_Penerima);
        System.out.println("Diskirpsi : " + this.diskripsi_barang);
        System.out.println("Tracking Number : " + this.tracking_number);
        System.out.println("Harga : " + this.harga_barang);
        System.out.println("Jumlah Barang : " + this.jumlah_barang);
        System.out.println("Berat Barang : " + this.berat_barang);
        System.out.println("Negara Asal :" + this.negara_asal);
        System.out.println("Negara Tujuan : " + this.negara_tujuan);
        System.out.println("Ongkir : " + this.ongkir);
        System.out.println("Terkena beacukai ? " + (this.isBeacukai ? "Ya" : "Tidak"));
        System.out.println("beacukai : " + String.format("%.2f", this.beacukai));
        System.out.println("=================================");
    }

    // getter
    public int getBerat_barang() {
        return berat_barang;
    }

    public String getDiskripsi_barang() {
        return diskripsi_barang;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public int getJumlah_barang() {
        return jumlah_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getNegara_asal() {
        return negara_asal;
    }

    public String getNegara_tujuan() {
        return negara_tujuan;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public double getOngkir() {
        return ongkir;
    }
    public double getBeacukai() {
        return beacukai;
    }
    public Boolean getIsBeacukai() {
        return isBeacukai;
    }
    // setter
    public void setNama_Penerima(String nama_Penerima) {
        this.nama_Penerima = nama_Penerima;
    }

    public void setNama_Pengekspor(String nama_Pengekspor) {
        this.nama_Pengekspor = nama_Pengekspor;
    }

    public void setBerat_barang(int berat_barang) {
        // setter berat jika berat kurang dari 0 maka set 0
        if (berat_barang <= 0) {
            this.berat_barang = 0;
        } else if (berat_barang >= 1) {
            this.berat_barang = berat_barang;
        }
    }

    public void setDiskripsi_barang(String diskripsi_barang) {
        this.diskripsi_barang = diskripsi_barang;
    }

    public void setHarga_barang(int harga_barang) {
        // setter harga jika harga kurang dari 0 maka set 0, dan disini nominal dari kurs dolar konstan 15000
        // jika harga lebih dari 4 dolar maka beackuai true jika tidak maka beacukai false
        if (harga_barang <= 0) {
            this.harga_barang = 0;
        } else if (harga_barang >= 1) {
            if (harga_barang >= (4 * 15000)) {
                isBeacukai = true;
                this.harga_barang = harga_barang;
                this.beacukai = hitungBeacukai();
            } else {
                isBeacukai = false;
                this.harga_barang = harga_barang;
            }
        }
    }

    public void setJumlah_barang(int jumlah_barang) {
        // setter jika jumlah barang kurang dari 0 maka set 0
        if (jumlah_barang <= 0) {
            this.jumlah_barang = 0;
        } else if (jumlah_barang >= 1) {
            this.jumlah_barang = jumlah_barang;
        }
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public void setNegara_asal(String negara_asal) {
        this.negara_asal = negara_asal;
    }

    public void setNegara_tujuan(String negara_tujuan) {
        this.negara_tujuan = negara_tujuan;
    }

    public void setTracking_number() {
        this.tracking_number = generateNumber();
    }

    public void setOngkir() {
        this.ongkir = hitungOngkir();
    }

    public String getNama_Penerima() {
        return nama_Penerima;
    }

    public String getNama_Pengekspor() {
        return nama_Pengekspor;
    }

    public static void main(String[] args) {
        // void main hanya digunakan untuk testing
        Date date = new Date();
        Order order = new Order();
        order.setNama_barang("Biji Kopi");
        order.setNama_Pengekspor("PT JAYA ABADI");
        order.setNama_Penerima("Food Delicious Corp.");
        order.setHarga_barang(100000000);
        order.setBerat_barang(200);
        order.setOngkir();
        order.setJumlah_barang(2);
        order.setDiskripsi_barang("Biji Kopi Pilihan");
        order.setNegara_asal("Indonesia");
        order.setNegara_tujuan("Amerika");
        order.setTracking_number();

        order.printAll();
    }
}