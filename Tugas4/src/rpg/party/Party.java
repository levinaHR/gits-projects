package rpg.party;

import java.util.ArrayList;
import java.util.List;

public class Party {
    List<Character> party = new ArrayList<>();

    public void viewMember() {
        for (Character c : party) {
            System.out.printf("%-12s", "- " + c.getName());
            System.out.println("<" + c.getClass().getSimpleName() + ">");
        }
    }

    public Character findMember(String name) {
        for (Character c : party) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }

        return null;
    }

    public List<Character> getParty() {
        return party;
    }

    public void setParty(List<Character> party) {
        this.party = party;
    }
}
