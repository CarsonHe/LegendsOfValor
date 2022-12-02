/**
 * class weapon
 * @author
 */
public class Weapon extends Item implements DamageInterFace {
    private final int baseDamage;
    private final int requiredHands;

    public Weapon(String name, int cost, int requiredLevel, int baseDamage, int requiredHands) {
        super(name, cost, requiredLevel);
        this.baseDamage = baseDamage;
        this.requiredHands = requiredHands;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getRequiredHands() {
        return requiredHands;
    }


    @Override
    public double damage(double strength) {
        return (strength + baseDamage) * 0.5;
    }
}
