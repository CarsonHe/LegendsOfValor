/**
 * class potion extends item
 */
public class Potion extends Item{
    private final int increasedAttr;
    private final String affectedAttr;

    public Potion(String name, int cost, int requiredLevel, int increasedAttr, String affectedAttr) {
        super(name, cost, requiredLevel);
        this.increasedAttr = increasedAttr;
        this.affectedAttr = affectedAttr;
    }

    // get increased attribute
    public int getIncreasedAttr() {
        return increasedAttr;
    }

    // get affected attribute
    public String getAffectedAttr() {
        return affectedAttr;
    }
}
