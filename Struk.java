import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Struk{
    public static List<Produk> produk =  new ArrayList<Produk>();
    public static HashMap<String, Produk> mapproduk = new HashMap<String, Produk>();

        public void readProduk(String path){
            List<Produk> listProduk = new ArrayList<Produk>();
            try {
                File myFile = new File("Produk.txt");
                Scanner myReader = new Scanner(myFile);
                String[] line = new String[2];
                
                while (myReader.hasNextLine()) {
                    String baca = myReader.nextLine();
                    line = baca.split(",");
                    Produk p = new Produk();
                    p.kode = line[0];
                    p.nama = line [1]; 
                    p.harga = Double.parseDouble(line[2]);
                    listProduk.add(p);
                }

            } 
            catch (FileNotFoundException e) {
                // TODO: handle exception
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            this.produk = listProduk;
        }

        public void readProdukMap(String path){
            List<Produk> listProduk = new ArrayList<Produk>();
            
            try {
                File myFile = new File("Produk.txt");
                Scanner myReader = new Scanner(myFile);
                String[] line = new String[2];
                
                while (myReader.hasNextLine()) {
                    String baca = myReader.nextLine();
                    line = baca.split(",");
                    Produk p = new Produk();
                    p.kode = line[0];
                    p.nama = line [1]; 
                    p.harga = Double.parseDouble(line[2]);
                    mapproduk.put(p.kode, p);
                }

            } 
            catch (FileNotFoundException e) {
                // TODO: handle exception
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            this.produk = listProduk;
        }

        public static Transaksi buatTransaksi(String kodeJumlah){
            Transaksi t = new Transaksi();
            String[] kodejumlah = kodeJumlah.split(" ");
            //t.produk = cariProdukByKode(kodejumlah[0]);
            t.produk = mapproduk.get(kodejumlah[0]);
            t.kuantiti = Integer.parseInt(kodejumlah[1]);
            return t;
        }

        private static Produk cariProdukByKode(String kode){
            for (Produk p : produk) {
                if (p.kode.equals(kode)) {
                    return p;
                }
            }
            return null;
        }

        public static double total = 0;

        public static void printStruk(List<Transaksi>listransaksi, double total, double bayar){
            for (Transaksi tran : listransaksi){
                System.out.println(tran.produk.nama+" "+ tran.kuantiti+" "+ tran.produk.harga+ " " + (tran.produk.harga*tran.kuantiti));
            }
            total = total;
            bayar = bayar;
            double kembalian = bayar - total;
            System.out.println("Total : " + total);
            System.out.println("Bayar : " + bayar);
            System.out.println("Kembalian : " + kembalian);
        }

    public static void main(String []args){ 
        
        // 1. Baca File Produk.txt
        Struk struk = new Struk();
        struk.readProdukMap("Produk.txt");        
        
        // 2. Tampilkan daftar produk
        for (Produk x:struk.produk){
            System.out.println(x.kode+" "+x.nama+" "+x.harga);
        }
        // 3. Lakukan Transaksi
        Scanner sc = new Scanner(System.in);
        
        ArrayList<Transaksi> listransaksi = new ArrayList<Transaksi>();
        System.out.println("Masukkan Kode Barang : ");
           // String kodeBarang = sc.next();

        String input = " ";        
        while (!input.equalsIgnoreCase("end")) {//ketika nilai variabel input tidak sama dengan kata "end", maka:
        input = sc.nextLine();
        if (!input.equalsIgnoreCase("end")){
            Transaksi t = buatTransaksi(input);
            total += t.produk.harga * t.kuantiti;
            listransaksi.add(t);
        }
        
    }
        
        System.out.println("Total = " + total);

        System.out.println("Masukkan Jumlah Pembayaran : ");
            double bayar = Double.parseDouble(sc.next());

        printStruk(listransaksi, total, bayar);
        
    }              
}



    


