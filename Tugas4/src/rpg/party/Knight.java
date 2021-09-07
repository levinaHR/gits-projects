package rpg.party;

public class Knight extends Character{
    Knight() {
        weapon = "Sword";
        hp = 32;
        mp = 17;
        str = 24;
        intl = 9;
    }

    Knight(String name) {
        this();
        this.name = name;
    }

    public void swdArt() {
        int damage = str*6;
        System.out.print("[SWORD ART] ");
        System.out.println(name + " unleashed Stock Break and deals " + damage + " damage.");
        System.out.println();
    }

    public void skill() {
        swdArt();
    }
}
