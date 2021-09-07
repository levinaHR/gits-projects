package rpg.party;

public class BlackMage extends Character {
    BlackMage() {
        weapon = "Staff";
        hp = 23;
        mp = 30;
        str = 10;
        intl = 27;
    }

    BlackMage(String name) {
        this();
        this.name = name;
    }

    public void blkMag() {
        int damage = intl*6;
        System.out.print("[BLACK MAGIC] ");
        System.out.println(name + " casted Flare and deals " + damage + " damage.");
        System.out.println();
    }

    public void skill() {
        blkMag();
    }
}
