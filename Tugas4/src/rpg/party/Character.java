package rpg.party;

public class Character {
    String name, weapon;
    int hp, mp, str, intl;

    Character() {
        name = "Anonymous";
        weapon = "Fists";
        hp = 5;
        mp = 5;
        str = 10;
        intl = 7;
    }

    Character(String name) {
        this();
        this.name = name;
    }

    public void attack() {
        int damage = str*2 + str%3;
        System.out.println(name + " attacked with their " + weapon + " and deals " + damage + " damage.");
        System.out.println();
    }

    public void skill() {
        System.out.println(name + " hasn't learn any skill yet.");
        System.out.println();
    }

    public void info() {
        System.out.println("--- CHARACTER STATS ---");
        System.out.println("Name: " + name);
        System.out.println("Class: " + this.getClass().getSimpleName());
        System.out.println("HP: " + hp);
        System.out.println("MP: " + mp);
        System.out.println("Strength: " + str);
        System.out.println("Intelligence: " + intl);
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getIntl() {
        return intl;
    }

    public void setIntl(int intl) {
        this.intl = intl;
    }
}
