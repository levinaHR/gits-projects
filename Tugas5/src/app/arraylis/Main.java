package app.arraylis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> consumables = new ArrayList<>();
        consumables.add("Potion"); consumables.add("Ether");
        consumables.add("Elixir"); consumables.add("Phoenix Down");

        List<Integer> dmgHistory = new ArrayList<>();
        dmgHistory.add(31); dmgHistory.add(25); dmgHistory.add(38);
        dmgHistory.add(12); dmgHistory.add(46);

        System.out.println("--- Damage History ----");
        for (Integer damage : dmgHistory) {
            System.out.println("- Dealed " + damage + " damage");
        }

        while (consumables.size() > 0) {
            System.out.println("\n--- Inventory ----");
            for (String item : consumables) {
                System.out.println("- " + item);
            }

            System.out.println("\nWhich item you want to use?");
            System.out.print("=> ");

            String input = in.nextLine().toLowerCase();
            boolean match = consumables.stream().anyMatch(input::equalsIgnoreCase);
            if (match) {
                for (String item : consumables) {
                    if (item.equalsIgnoreCase(input)) {
                        System.out.println("Used " + item + ".");
                        consumables.remove(item);
                        break;
                    }
                }

            } else {
                System.out.println("You don't have " + input + " in your inventory.");
            }

        }

        System.out.println("You don't have anything in your inventory.");
    }
}
