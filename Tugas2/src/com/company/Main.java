package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Map<String, Integer> menu = new HashMap<>();
        menu.put("Ayam goreng", 50000);
        menu.put("Lele goreng", 48000);
        menu.put("Sayur nangka", 25000);
        menu.put("Kikil", 27000);
        menu.put("Tempe orek", 21000);
        menu.put("Telur dadar", 35000);
        menu.put("Telur balado", 37000);
        menu.put("Perkedel", 24000);

        List<String> pesanan = new ArrayList<String>();
        int harga = 0;
        int counter = 0;

        System.out.println("Selamat datang di Warteg Nian!\nBerikut adalah menu kami:");
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            System.out.printf("%-20s", "- " + entry.getKey());
            if (counter % 2 != 0) {
                System.out.println();
            }
            counter++;
        }

        System.out.println("\nApakah anda ingin memesan?\n[ya/tidak]");
        Scanner in = new Scanner(System.in);

        try {
            String answer = in.nextLine();
            switch (answer.toLowerCase()) {
                case "ya":
                    System.out.println("\nMau pesan apa?\n[masukkan menu makanan yang ingin dibeli]");
                    String pesan = in.nextLine();
                    while (true) {
                        pesan = pesan.substring(0, 1).toUpperCase() + pesan.substring(1);
                        if (menu.containsKey(pesan)) {
                            pesanan.add(pesan);
                            harga += menu.get(pesan);

                            System.out.println("\nAda lagi atau sudah?\n[masukkan sudah jika ingin berhenti]");
                            pesan = in.nextLine();
                            if (pesan.equalsIgnoreCase("sudah")) {
                                System.out.println("\nBerikut adalah pesanan anda:");
                                List<String> pesananUnique = pesanan.stream().distinct().collect(Collectors.toList());
                                for (String s : pesananUnique) {
                                    System.out.println("- " + Collections.frequency(pesanan, s) + " " + s);
                                }
                                System.out.println(
                                        "Totalnya adalah Rp" + String.format("%,d", harga) +
                                        "\nTerima kasih karena telah berbelanja di Warung Tega Nian ^^");
                                break;
                            }

                        } else {
                            System.out.println("\nKami tidak menjual menu tersebut.\nMau pesan apa?\n[masukkan menu makanan yang ingin dibeli]");
                            pesan = in.nextLine();
                        }
                    }
                    break;

                case "tidak": System.out.println("\nTerima kasih dan datang kembali ya."); break;
                default: System.out.println("\nMaaf, saya tidak mengerti apa yang anda maksud.");
            }

        } catch (Exception e) {
            System.out.println("Maaf, saya tidak mengerti apa yang anda maksud.");
        }
    }
}
