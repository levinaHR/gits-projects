package aplikasi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Perpus {
    List<Buku> bukuList = new ArrayList<>();

    public Buku findBuku(String judul) {
        for (Buku b : bukuList) {
            if (b.title.equalsIgnoreCase(judul)) {
                return b;
            }
        }

        return null;
    }

    public void addKomik(String judul) {
        Komik komik = new Komik();
        komik.title = judul;
        bukuList.add(komik);
    }

    public void addNovel(String judul) {
        Novel novel = new Novel();
        novel.title = judul;
        bukuList.add(novel);
    }

    public void viewCollection() {
        int counter = 0;
        System.out.println("Daftar koleksi buku:");
        for (Buku b : bukuList) {
            System.out.printf("%-20s", "- " + b.title);
            if (counter % 2 != 0) {
                System.out.println();
            }

            counter++;
        }
    }
}

class Buku {
    String title;

    public void viewInfo() {
        System.out.println(title + " merupakan sebuah buku.");
    }
}

class Komik extends Buku {
    @Override
    public void viewInfo() {
        System.out.println(title + " merupakan buku komik.");
    }
}

class Novel extends Buku {
    @Override
    public void viewInfo() {
        System.out.println(title + " merupakan buku novel.");
    }
}

public class Main {
    public static void main(String[] args) {
        Perpus perpus = new Perpus();
        perpus.addKomik("Detektif Conan");
        perpus.addKomik("Naruto");
        perpus.addKomik("Doraemon");
        perpus.addNovel("Harry Potter");
        perpus.addNovel("Sherlock Holmes");
        perpus.addNovel("Lord of the Ring");
        Scanner in = new Scanner(System.in);
        Buku buku;

        System.out.println("Selamat datang di perpustakaan!");
        while (true) {
            perpus.viewCollection();
            System.out.println("\nMasukkan judul buku untuk melihat info buku tersebut.");
            System.out.print("=> ");

            String input = in.nextLine();
            buku = perpus.findBuku(input);
            if (buku == null) {
                System.out.println("Buku tidak ditemukan.");
                continue;
            }

            buku.viewInfo();
            break;
        }
    }
}
