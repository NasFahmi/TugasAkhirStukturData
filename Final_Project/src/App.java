import java.util.*;

public class App {
    // ! static variabel untuk membuat flag app, queue, dan arraylist
    public static List<Order> orderList = new ArrayList<Order>(100);
    public static Queue queue = new Queue(100);
    public static boolean flag = true;

    
    public static void main(String[] args) throws Exception {
        while (flag) {
            LandingPage();
        }
    }

    public static void LandingPage() {
        // disini ada menu yang nanti jika memilih 1 maka akan eksekusi function lihat barang
        // 2 untuk masuk ke fucntion input baran g
        // 3 untuk masuk ke admin 
        // 4 untuk melihat daftar barang menggunakan queue
        Scanner sc = new Scanner(System.in);
        System.out.println("Selamat Data Aplikasi Ekspor Impor");
        System.out.println("=================================");
        System.out.println("1. Lihat barang menggunakn Tracking Number");
        System.out.println("2. ingin Ekspor barang?");
        System.out.println("3. Admin");
        System.out.println("4. Daftar Barang");
        int pilihan = sc.nextInt();

        if (pilihan == 1) {
            System.out.println("Masuk Menu Lihat Barang");
            Order orderFound = lihatBarang();
            if (orderFound != null) {
                System.out.println("Barang ditemukan");
                System.out.println("=================================");
                System.out.println("Tracking Number : " + orderFound.getTracking_number());
                System.out.println("Nama Barang : " + orderFound.getNama_barang());
                System.out.println("Harga Barang : " + orderFound.getHarga_barang());
                System.out.println("Pengirim : " + orderFound.getNama_Pengekspor());
                System.out.println("Penerima : " + orderFound.getNama_Penerima());
                System.out.println("=================================");
            } else if (orderFound == null) {
                System.out.println("Barang Tidak Ditemukan");
            }

        } else if (pilihan == 2) {
            EksporMenu();
        } else if (pilihan == 3) {
            AdminMenu();
        } else if (pilihan == 4) {
            daftarBarang();
        }  else {
            System.out.println("Pilihan Tidak Valid");
        }
    }

    public static void EksporMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masuk Menu Ekspor");
        System.out.println("=========================");
        inputBarang();

    }

    public static void AdminMenu() {
        Scanner sc = new Scanner(System.in);
        boolean flagimporMenu = true;
        while (flagimporMenu) {
            System.out.println("Masuk menu Admin");
            System.out.println("=========================");
            System.out.println("1. Lihat Antrian Ekspor");
            System.out.println("2. Approve Antrian Ekspor");
            System.out.println("3. Back to Main Menu");
            int pilihan = sc.nextInt();
            if (pilihan == 1) {
                // lihatBarang();
                queue.printQueue();
            } else if (pilihan == 2) {
                // pengecekan jika ada queue pertama/queue tidak kosong maka eksekusi for 

                System.out.println("Menampilkan Queue Pertama");
                String frontelement = queue.peek();
                if (frontelement != null) {
                    System.out.println("Front Queue adalah : " + frontelement);

                    for (int i = 0; i < orderList.size(); i++) {
                        // jika for dengan length == panjang array, jika frontelement yang isinya tracking number == order.tracking number 
                        // maka dapatkan detail baraang dari orders
                        Order orders = orderList.get(i);
                        if (frontelement.equals(orders.getTracking_number())) {
                            System.out.println("==========Detail Barang==========");
                            System.out.println("=================================");
                            System.out.println("Nama : " + orders.getNama_barang());
                            System.out.println("Nama Pengekspor : " + orders.getNama_Pengekspor());
                            System.out.println("Nama Penerima : " + orders.getNama_Penerima());
                            System.out.println("Diskirpsi : " + orders.getDiskripsi_barang());
                            System.out.println("Tracking Number : " + orders.getTracking_number());
                            System.out.println("Harga : " + orders.getHarga_barang());
                            System.out.println("Jumlah Barang : " + orders.getJumlah_barang());
                            System.out.println("Berat Barang : " + orders.getBerat_barang());
                            System.out.println("Negara Asal :" + orders.getNegara_asal());
                            System.out.println("Negara Tujuan : " + orders.getNegara_tujuan());
                            System.out.println("Ongkir : " + orders.getOngkir());
                            System.out.println("Terkena beacukai ? " + (orders.getIsBeacukai() ? "Ya" : "Tidak"));
                            System.out.println("beacukai : " + String.format("%.2f", orders.getBeacukai()));
                            System.out.println("=================================");
                        }
                        System.out.println("Apakah Admin Yakin Untuk Approve Transaksi ini?");
                        String pilih = sc.next();
                        // nanti ada user input yakin untuk approve transaksi, jika ya maka akan dequeue dan hilangkan element dari arraylist tadi dan break 
                        if (pilih.equals("Y") || pilih.equals("y")) {
                            System.out.println("Transaksi sudah disetujui");
                            queue.dequeue();
                            orderList.remove(i);
                            break;
                        } else if (pilih.equals("N") || pilih.equals("n")) {
                            flagimporMenu = true;
                            break;
                        }
                        
                    }
                }else {
                    System.out.println("Queue Kosong");
                }
            } else if (pilihan == 3) {
                LandingPage();
            }
        }
    }

    // daftar barang mengprint semua queue yang masuk
    public static void daftarBarang() {
        System.out.println("=========================");
        System.out.println("Masuk menu daftar Barang yang sudah diinputkan");
        System.out.println("=========================");
        queue.printQueue();

    }

    //function input data yang nanti datanya akan disimpan di array list dan untuk tracking number akan di simpan di queue
    public static void inputBarang() {
        Scanner sc = new Scanner(System.in);
        Order order = new Order();

        System.out.print("1. Nama Perusahaan Pengekspor : ");
        String namaPengekspor = sc.next();
        order.setNama_Pengekspor(namaPengekspor);

        System.out.print("2. Nama Perusahaan Penerima :");
        String namaPenerima = sc.next();
        order.setNama_Penerima(namaPenerima);

        System.out.print("3. Nama Barang : ");
        String namaBarang = sc.next();
        order.setNama_barang(namaBarang);

        System.out.print("4. Diskripsi Barang :");
        String diskripsiBarang = sc.next();
        order.setDiskripsi_barang(diskripsiBarang);

        System.out.print("5. Harga Barang : ");
        int hargaBarang = sc.nextInt();
        order.setHarga_barang(hargaBarang);

        System.out.print("6. Jumlah Barang : ");
        int jumlahBarang = sc.nextInt();
        order.setJumlah_barang(jumlahBarang);

        System.out.print("7. Berat barang : ");
        int beratBarang = sc.nextInt();
        order.setBerat_barang(beratBarang);

        System.out.print("8. Negara Asal : ");
        String negaraAsal = sc.next();
        order.setNegara_asal(negaraAsal);

        System.out.print("9. Negara Tujuan : ");
        String negaraTujuan = sc.next();
        order.setNegara_tujuan(negaraTujuan);

        order.setOngkir();
        order.setTracking_number();
        System.out.println("Detail Produk Berhasil Diinputkan");
        order.printAll();
        System.out.println("=========================");
        orderList.add(order);
        queue.enqueue(order.getTracking_number());

    }

    // function ini digunakan untuk mendapatakn object yang tesimpang di arraylist berdasarkan tracking number yang sudah dibuat dan mereturn object
    public static Order lihatBarang() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan Tracking Number : ");
        String trackingNumber = sc.next();
        // ! searching
        for (int i = 0; i < orderList.size(); i++) {
            Order orders = orderList.get(i);
            if (trackingNumber.equals(orders.getTracking_number())) {
                return orders;
            }
        }
        return null;
    }

}
