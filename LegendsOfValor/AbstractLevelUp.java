/**
 * Abstract class of Level up
 */
public abstract class AbstractLevelUp implements HeroLevelUpHandler {
    protected Hero hero;

    public boolean canLevelUp() {
        return (hero.getExpBonus() >= hero.getLevel() * 10);
    }


    public void levelUpCommon() {
        hero.setHp(100.0 * hero.getLevel());
        hero.setMana(hero.getMana() * 1.1);
        hero.setLevel(hero.getLevel() + 1);
        hero.setExp(hero.getExp() + hero.getExpBonus());
        hero.setExpBonus(0);
    }


}
