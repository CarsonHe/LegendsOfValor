/**
 * hero Paladin extends class Hero
 */
public class Paladin extends Hero {
    public Paladin(String name, int level, int mana, int strength, int dexterity, int agility, int money, int exp) {
        super(name, level, mana, strength, dexterity, agility, money, exp);
        levelUpHandler = new PaladinLevelUp(this);
    }
}
