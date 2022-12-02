/**
 * class sorcerer extends class Hero
 */
public class Sorcerer extends Hero{
    public Sorcerer(String name, int level, int mana, int strength, int dexterity, int agility, int money, int exp) {
        super(name, level, mana, strength, dexterity, agility, money, exp);
        levelUpHandler = new SorcererLevelUp(this);
    }
}
