/**
 * class Monster extends class Role
 */
public class Monster extends Role {
    protected double damage;
    protected double defenseStat;
    protected double dodgeChance;
    private String monsterMarker;

    public Monster(String name, int level, double damage, double defenseStat, double dodgeChance) {
        super(name, level);

        this.damage = damage;
        this.defenseStat = defenseStat;
        this.setDodgeChance(dodgeChance);
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance * 0.01;
    }

    /**
     * check if monster is dead
     * @return
     */
    public boolean isDead() {
        return getHp() <= 0;
    }


    public void attack(Hero hero, ValorMap map) {
        String heroName = hero.getName();
        double heroDodgeChance = hero.getDodgeChance();
        double rand = Math.random();
        Cell[][] m = map.getMap();
        if (m[hero.getRow()][hero.getCol()] instanceof CaveCell) {
            heroDodgeChance *= 1.1;
        }
        double totalDamage = damage;
        if (rand <= heroDodgeChance) {
            System.out.println("hero " + getMonsterMarker() + ":" + heroName + " had dodged");
            return;
        }
        if (hero.getArmor() != null) {
            totalDamage -= hero.getArmor().getDamageReduction();
            if (totalDamage > 0) {
                System.out.println(heroName + " 's armor reduced ' " + hero.getArmor().getDamageReduction() + " damages!");
            } else {
                System.out.println(heroName + " 's armor reduced all the damages'!");
                return;
            }
        }
        hero.setHp(hero.getHp() - totalDamage);
        //cause damage
        System.out.println(this.getMonsterMarker() + " [ " + this.getName() + " ] " + "attack " + hero.getHeroMarker() + " [ " + hero.getName() + " ] caused damage "+totalDamage);
        processHeroStatus(hero);
    }

    /**
     * check hero status
     * @param hero
     */
    public void processHeroStatus(Hero hero) {
        String info = "Hero " + hero.getName();

        if (!hero.hasDead()) {
            System.out.println(info+" current hp: "+hero.getHp());
        } else {
            System.out.println(info+" is dead");
        }
    }


    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDefenseStat() {
        return defenseStat;
    }

    public void setDefenseStat(double defenseStat) {
        this.defenseStat = defenseStat;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public String getMonsterMarker() {
        return monsterMarker;
    }

    public void setMonsterMarker(String monsterMarker) {
        this.monsterMarker = monsterMarker;
    }
}