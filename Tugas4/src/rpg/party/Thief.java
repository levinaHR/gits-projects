package rpg.party;

public class Thief extends Character {
    Thief() {
        weapon = "Dagger";
        hp = 27;
        mp = 20;
        str = 16;
        intl = 12;
    }

    Thief(String name) {
        this();
        this.name = name;
    }

    public void steal() {
        boolean chance = Math.random() > 0.5;

        System.out.print("[STEAL] ");
        if (chance)
            System.out.println(name + " successfully stole an item.");
        else
            System.out.println(name + " failed to steal anything.");

        System.out.println();
    }

    public void skill() {
        steal();
    }
}
