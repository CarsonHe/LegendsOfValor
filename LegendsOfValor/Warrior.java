/**
 * class warrior extends class Hero
 */
public class Warrior extends Hero{
    public Warrior(String name, int level, int mana, int strength, int dexterity, int agility, int money, int exp) {
        super(name, level, mana, strength, dexterity, agility, money, exp);

        levelUpHandler = new WarriorLevelUp(this);
    }
}
