package rpg.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Character> party1 = new ArrayList<>();
        Thief zidane = new Thief("Zidane");
        Knight steiner = new Knight("Steiner");
        Summoner garnet = new Summoner("Garnet");
        party1.add(zidane); party1.add(steiner); party1.add(garnet);

        List<Character> party2 = new ArrayList<>();
        BlackMage vivi = new BlackMage("Vivi");
        Dragoon freya = new Dragoon("Freya");
        WhiteMage eiko = new WhiteMage("Eiko");
        party2.add(vivi); party2.add(freya); party2.add(eiko);

        Party mainParty = new Party();
        mainParty.setParty(party1);

        while (true) {
            System.out.println("Party member: ");
            mainParty.viewMember();

            System.out.println("\nCommands:");
            System.out.println("[STATS]     [ATTACK]     [SKILL]     [SWITCH PARTY]");
            System.out.print("=> ");

            String command = in.nextLine().toLowerCase();
            String name; Character character;

            switch (command) {
                case "stats":
                    System.out.println("\nPick character you want to see their stats.");
                    System.out.print("=> ");

                    name = in.nextLine().toLowerCase();
                    character = mainParty.findMember(name);
                    if (character != null) {
                        character.info();

                    } else {
                        System.out.println(name + " is not in the party.\n");
                    }
                    break;

                case "attack":
                    System.out.println("\nWho will attack?");
                    System.out.print("=> ");

                    name = in.nextLine().toLowerCase();
                    character = mainParty.findMember(name);
                    if (character != null) {
                        character.attack();

                    } else {
                        System.out.println(name + " is not in the party.\n");
                    }
                    break;

                case "skill":
                    System.out.println("\nWho will use skill?");
                    System.out.print("=> ");

                    name = in.nextLine().toLowerCase();
                    character = mainParty.findMember(name);
                    if (character != null) {
                        character.skill();

                    } else {
                        System.out.println(name + " is not in the party.\n");
                    }
                    break;

                case "switch party":
                    System.out.println("\nSwitching party...");
                    if (mainParty.getParty() == party1)
                        mainParty.setParty(party2);
                    else
                        mainParty.setParty(party1);

                    System.out.println("Party switched.\n");
                    break;

                default: System.out.println("Command not recognized.\n");
            }
        }
    }
}
