/**
 * level up interface for hero
 */
public interface HeroLevelUpHandler {
    /**
     * check if can level up
     * @return
     */
    boolean canLevelUp();

    /**
     * level up
     */
    void levelUp();
}
