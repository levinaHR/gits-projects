package rpg.party;

public class WhiteMage extends Character {
    WhiteMage() {
        weapon = "Flute";
        hp = 18;
        mp = 32;
        str = 9;
        intl = 28;
    }

    WhiteMage(String name) {
        this();
        this.name = name;
    }

    public void cure() {
        System.out.print("[CURE] ");
        System.out.println(name + " casted Cure and healed the party for 80 HP.");
        System.out.println();
    }

    public void skill() {
        cure();
    }
}
