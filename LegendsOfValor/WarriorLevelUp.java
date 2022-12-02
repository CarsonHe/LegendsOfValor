/**
 * level up for warrior
 */
public class WarriorLevelUp extends AbstractLevelUp {

    public WarriorLevelUp(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void levelUp() {
        if (!canLevelUp()) {
            return;
        }
        super.levelUpCommon();
        hero.setStrength(hero.getStrength() * 1.1);
        hero.setAgility(hero.getAgility() * 1.1);
        hero.setDexterity(hero.getDexterity() * 1.05);
    }
}
