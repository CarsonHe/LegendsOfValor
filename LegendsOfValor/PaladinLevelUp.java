/**
 * level up for Paladin
 */
public class PaladinLevelUp extends AbstractLevelUp {

    public PaladinLevelUp(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void levelUp() {
        if (!canLevelUp()) {
            return;
        }
        super.levelUpCommon();
        hero.setStrength(hero.getStrength() * 1.1);
        hero.setAgility(hero.getAgility() * 1.05);
        hero.setDexterity(hero.getDexterity() * 1.1);
    }
}
