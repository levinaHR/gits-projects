package rpg.party;

public class Summoner extends Character {
    Summoner() {
        weapon = "Rod";
        hp = 20;
        mp = 25;
        str = 12;
        intl = 30;
    }

    Summoner(String name) {
        this();
        this.name = name;
    }

    public void summon() {
        System.out.print("[SUMMON] ");
        System.out.println(name + " summoned Bahamut and deals a whooping 800 damage!!");
        System.out.println();
    }

    public void skill() {
        summon();
    }
}
