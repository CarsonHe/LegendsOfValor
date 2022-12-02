/**
 * class spell extend class item and implements DamageInterface
 */
public class Spell extends Item implements DamageInterFace {
    private int baseDamage;
    private int manaCost;

    public Spell() {}

    public Spell(String name, int cost, int requiredLevel, int baseDamage, int manaCost) {
        super(name, cost, requiredLevel);
        this.baseDamage = baseDamage;
        this.manaCost = manaCost;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getManaCost() {
        return manaCost;
    }

    @Override
    public double damage(double dexterity) {
        return baseDamage + (dexterity / 10000.0) * baseDamage;
    }
}
