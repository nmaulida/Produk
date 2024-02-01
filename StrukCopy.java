import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StrukCopy{
    public List<Produk> produkList = new ArrayList<>();

    public void readProduk(String path) {
        List<Produk> listProduk = new ArrayList<>();
        try {
            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);
            String[] line;

            while (myReader.hasNextLine()) {
                String baca = myReader.nextLine();
                line = baca.split(",");
                Produk p = new Produk();
                p.kode = line[0];
                p.nama = line[1];
                p.harga = Double.parseDouble(line[2]);
                listProduk.add(p);
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.produkList = listProduk;
    }

    public static void main(String[] args) {

        // 1. Baca File Produk.txt
        StrukCopy struk = new StrukCopy();
        struk.readProduk("Produk.txt");

        // 2. Tampilkan daftar produk
        System.out.println("Daftar Produk:");
        for (Produk x : struk.produkList) {
            System.out.println(x.kode + " " + x.nama + " " + x.harga);
        }

        // 3. Lakukan Transaksi
        Scanner sc = new Scanner(System.in);
        List<Transaksi> daftarBarang = new ArrayList<>();

        boolean tambahBarang = true;
        while (tambahBarang) {
            System.out.print("Masukkan Kode barang: ");
            String kodeBarang = sc.next();

            // cari produk yg di inputkan ke daftar produk
            Produk cariProduk = null;
            for (Produk p : struk.produkList) {
                if (p.kode.equals(kodeBarang)) {
                    cariProduk = p;
                    break;
                }
            }

            if (cariProduk == null) {
                System.out.println("Produk dengan kode " + kodeBarang + " tidak ditemukan.");
            } else {
                System.out.print("Masukkan jumlah barang yang ingin dibeli: ");
                int jumlahBarang = sc.nextInt();

                //buat list transaksi dan memasukkan dalam list
                daftarBarang.add(new Transaksi(cariProduk, jumlahBarang));

                System.out.print("Tambah barang lain? (y/n): ");
                String jawaban = sc.next();
                tambahBarang = jawaban.equalsIgnoreCase("y");
            }
        }

        // 4. Print Struk
        double totalBelanja = 0.0;
        System.out.println("Struk : ");
        for (Transaksi transaksi : daftarBarang) {
            System.out.println(transaksi.produk.nama);
            System.out.println(transaksi.produk.harga);
            System.out.println(transaksi.kuantiti);
            System.out.println(transaksi.subtotal());                    
            totalBelanja += transaksi.subtotal();
        }
        System.out.printf("Total Transaksi: ", totalBelanja);
    }
}
