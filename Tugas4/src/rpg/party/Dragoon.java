package rpg.party;

public class Dragoon extends Character {
    Dragoon() {
        weapon = "Lance";
        hp = 24;
        mp = 14;
        str = 26;
        intl = 10;
    }

    Dragoon(String name) {
        this();
        this.name = name;
    }

    public void jump() {
        int damage = str*6;
        System.out.print("[JUMP] ");
        System.out.println(name + " leaped to the sky and descended at a high speed, dealing " + damage + " damage.");
        System.out.println();
    }

    public void skill() {
        jump();
    }
}
