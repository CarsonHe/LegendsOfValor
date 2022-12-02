import java.util.ArrayList;

/**
 * class hero for all heroes extends role class
 *
 */
public class Hero extends Role {
    //mana
    private double mana;
    //strength
    protected double strength;
    //dexterity
    protected double dexterity;
    //agility
    protected double agility;
    //dodgeChange
    protected double dodgeChance;
    //money
    private double money;
    //experience
    protected int exp;
    //experience after fight
    protected int expBonus;
    //start point
    private int startingCol;
    //hero marker
    private String heroMarker;

    //items list
    protected ArrayList<Item> items;
    //weapons list
    protected ArrayList<Weapon> weapons;
    //armors list
    protected ArrayList<Armor> armors;
    //potions list
    protected ArrayList<Potion> potions;
    //spells list
    protected ArrayList<Spell> spells;
    //hero level up handler
    protected HeroLevelUpHandler levelUpHandler;
    //current weapon
    protected Weapon weapon;
    //current armor
    protected Armor armor;


    public Hero(String name, int level, double mana, int strength, int dexterity, int agility, double money, int exp) {
        super(name, level);
        this.setMana(mana);
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.setDodgeChance();
        this.setMoney(money);
        this.exp = exp;

        expBonus = 0;
        setStartingCol(0);

        items = new ArrayList<>();
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        potions = new ArrayList<>();
        spells = new ArrayList<>();

        weapon = null;
        armor = null;
    }


    public double getMoney() {
        return money;
    }

    public void setDodgeChance() {
        dodgeChance = this.agility * 0.001;
    }

    public void setExpBonus(int bonus) {
        this.expBonus += bonus;
        this.exp += bonus;
    }

    public void setStartingCol(int startingCol) {
        this.startingCol = startingCol;
    }

    /*
        check if hero has dead
    */
    public boolean hasDead() {
        return getHp() <= 0;
    }

    /*
        check if the hero can level up, max is 10
     */
    public boolean checkLevelUp() {
        return levelUpHandler.canLevelUp() && getLevel() <= 9;
    }

    //level up
    public void performLevelUp() {
        if (!checkLevelUp()) {
            return;
        }
        levelUpHandler.levelUp();
        System.out.println("Hero:  " + getName() + " level up!");
        System.out.println(getName() + "current level:  " + getLevel());


    }

    /*
        hero can buy items
     */
    public boolean buy(Item item) {
        if (!item.isAffordable(this)) {
            System.out.println("sorry, you dont have enough money or your level doesn't meet the required level");
            return false; // fail to buy
        }

        System.out.println("purchase successfully!");
        setMoney(getMoney() - item.getCost());
        items.add(item);
        if (item instanceof Weapon) {
            weapons.add((Weapon) item);
        } else if (item instanceof Armor) {
            armors.add((Armor) item);
        } else if (item instanceof Potion) {
            potions.add((Potion) item);
        } else if (item instanceof Spell) {
            spells.add((Spell) item);
        }
        return true;
    }

    /**
     * print inventory
     */
    public void printInventory() {
        System.out.println(" inventory of "+getName());

        System.out.println("name                  price          required level           type");
        System.out.println(ScannerUtil.splitLine);

        for (int i = 0; i < items.size(); ++i) {
            String name = (i + 1) + ". " + items.get(i).getName();
            int price = items.get(i).getCost();
            int level = items.get(i).getRequiredLevel();
            String type = "";

            if (items.get(i) instanceof Weapon) {
                type = "Weapon";
            } else if (items.get(i) instanceof Armor) {
                type = "Armor";
            } else if (items.get(i) instanceof Potion) {
                type = "Potion";
            } else if (items.get(i) instanceof IceSpell) {
                type = "Ice Spell";
            } else if (items.get(i) instanceof FireSpell) {
                type = "Fire Spell";
            } else if (items.get(i) instanceof LightningSpell) {
                type = "Lightning Spell";
            }

            System.out.printf("%-22s %-14d %-12d %-8s", name, price, level, type);
            System.out.println();
        }
        System.out.println(ScannerUtil.splitLine);
    }

    /*
        hero can sell items
     */
    public void sell(int propIndex) {
        Item temp = items.get(propIndex - 1);
        System.out.println("sold successfully!");
        //sell with half price
        setMoney(getMoney() + temp.getCost() * 0.5);
        items.remove(propIndex - 1);

        if (temp instanceof Weapon) {
            weapons.remove(temp);
        } else if (temp instanceof Armor) {
            armors.remove(temp);
        } else if (temp instanceof Potion) {
            potions.remove(temp);
        } else if (temp instanceof Spell) {
            spells.remove(temp);
        }
    }


    /*
       attack
     */
    public void attack(Monster monster, ValorMap map) {
        double monsterDodgeChance = monster.getDodgeChance();
        double strength = this.strength;
        Cell[][] m = map.getMap();
        // KoulouCell enhance 10% strength of any hero
        if (m[getRow()][getCol()] instanceof KoulouCell) {
            strength *= 1.1;
        }
        double totalDamage = weapon == null ? strength * 0.5 : weapon.damage(strength);
        //check if the monster can dodge this attack
        double rand = Math.random();
        if (rand <= monsterDodgeChance) {
            System.out.println("The monster dodge your attack!");
            return;
        }
        //does not dodge
        monster.setDefenseStat(monster.getDefenseStat() - totalDamage);
        //cause damage
        System.out.println(((Hero) this).getHeroMarker() + " [ " + this.getName() + " ] " + "attack " + ((Monster) monster).getMonsterMarker() + " [ " + monster.getName() + " ] causes damage: "+totalDamage);
        processMonsterStatus(monster);
    }

    /*
        hero can select weapon from weapon inventory
     */
    public Weapon chooseWeapon() {
        if (weapons.isEmpty()) {
            System.out.println("you weapon inventory is empty!");
            return null;
        }
        // hero print their own weapon inventory
        printWeaponInventory();
        System.out.println("please select a weapon: ");
        int choice = ScannerUtil.getInt(1, weapons.size());
        items.remove(weapons.get(choice - 1));
        return weapons.remove(choice - 1);
    }

    /*
       hero print their own weapon inventory
     */
    public void printWeaponInventory() {
        System.out.println(" Weapon inventory: " + getName() + " ");
        System.out.println("Name                  Base Damage");
        System.out.println(ScannerUtil.splitLine);
        int index = 1;
        for (Item item : items) {
            //print all the weapon
            if (item instanceof Weapon) {
                String name = index + ". " + item.getName();
                int damage = ((Weapon) item).getBaseDamage();
                ++index;

                System.out.printf("%-24s %-5d", name, damage);
                System.out.println();
            }
        }
        System.out.println(ScannerUtil.splitLine);
    }

    /**
     * check monster's data after a round
     * @param monster
     */
    public void processMonsterStatus(Monster monster) {
        String monsterName = monster.getName();
        if (monster.getDefenseStat() >= 0) {
            System.out.println("the monster dodge your damage");
        } else {
            monster.setHp(monster.getHp() + monster.getDefenseStat());
            monster.setDefenseStat(0);
            String info = "Monster " + monsterName;
            if (monster.getHp() > 0) {
                System.out.println(info+" HP:"+monster.getHp());
            } else {
                System.out.println(info+" dead");
            }
        }
    }

    /*
        use spell
     */
    public boolean castASpell(Monster monster, ValorMap map) {
        double monsterDodgeChance = monster.getDodgeChance();
        double dexterity = this.dexterity;
        // choose a spell from spell inventory
        Spell s = chooseSpell();
        Cell[][] m = map.getMap();
        // BushCell enhance 10% dexterity of any hero
        if (m[getRow()][getCol()] instanceof BushCell) {
            dexterity *= 1.1;
        }
        // return false if fail to choose spell
        if (s == null) {
            return false;
        }
        if (getMana() < s.getManaCost()) {
            System.out.println("you dont have enough mana!");
            return false;
        } else {
            setMana(getMana() - s.getManaCost());
        }
        //calculate total damage from hero to monster
        double totalDamage = s.damage(dexterity);
        //check if the monster can dodge the spell
        double rand = Math.random();
        if (rand <= monsterDodgeChance) {
            System.out.println(" The monster dodge your spell!");
            return true;
        }
        //monster fail to dodge spell
        monster.setDefenseStat(monster.getDefenseStat() - totalDamage);
        //lower 10% of ability according to the type of spell
        if (s instanceof IceSpell) {
            monster.setDamage(monster.getDamage() * 0.9);
        } else if (s instanceof FireSpell) {
            monster.setDefenseStat(monster.getDefenseStat() * 0.9);
        } else if (s instanceof LightningSpell) {
            monster.setDodgeChance(monster.getDodgeChance() * 0.9);
        }
        //damage caused
        System.out.println(this.getHeroMarker() + " [ " + this.getName() + " ] " + " use spell to " + monster.getMonsterMarker() + " [ " + monster.getName() + " ] causes damage: "+totalDamage);
        // check status for monster
        processMonsterStatus(monster);
        return true;
    }


    /**
     *  hero can choose spell from their own spell inventory
     * @return
     */
    public Spell chooseSpell() {
        if (spells.isEmpty()) {
            System.out.println("you dont have spell right now!");
            return null;
        }

        printSpellInventory();
        System.out.println("please select a spell: ");
        int choice = ScannerUtil.getInt(1, spells.size());
        return spells.get(choice - 1);
    }

    /**
     * hero can print spell from their own spell inventory
     */
    public void printSpellInventory() {
        System.out.println(" spell inventory: " + getName() + " ");
        System.out.println("Name                  Base Damage     Mana Cost");
        System.out.println(ScannerUtil.splitLine);
        int index = 1;
        for (Item item : items) {
            if (item instanceof Spell) {
                String name = index + ". " + item.getName();
                int damage = ((Spell) item).getBaseDamage();
                int mana = ((Spell) item).getManaCost();
                ++index;

                System.out.printf("%-24s %-15d %-5d", name, damage, mana);
                System.out.println();
            }
        }
        System.out.println(ScannerUtil.splitLine);
    }

    /*
       use a potion
     */
    public boolean useAPotion() {
        if (potions.isEmpty()) {
            System.out.println("you dont have potion");
            return false;
        }
        printPotionInventory();
        System.out.println("choose the potion you want to use:");
        Potion potion = potions.get(ScannerUtil.getInt(1, potions.size()));
        String[] split = potion.getAffectedAttr().split("\\|");
        for (String s : split) {
            if (s.charAt(0) == 'S') {
                strength += potion.getIncreasedAttr();
            } else if (s.charAt(0) == 'D') {
                dexterity += potion.getIncreasedAttr();
            } else if (s.charAt(0) == 'A') {
                agility += potion.getIncreasedAttr();
            }
        }
        System.out.println("you use potion:"+potion.getName());
        potions.remove(potion);
        items.remove(potion);
        return true;
    }

    /*
       print potion inventory
     */
    public void printPotionInventory() {
        System.out.println("Inventory of " +getName()+" : ");
        System.out.println("Name                  Increased Amount    Affected Attr");
        int index = 1;
        for (Item item : items) {
            if (item instanceof Potion) {
                String name = index + ". " + item.getName();
                int amount = ((Potion) item).getIncreasedAttr();
                String affectedAttr = ((Potion) item).getAffectedAttr();
                ++index;
                System.out.printf("%-28s %-14d %-18s", name, amount, affectedAttr);
                System.out.println();
            }
        }
        System.out.println(ScannerUtil.splitLine);
    }

    /*
        change an armor
     */
    public boolean changeAnArmor() {
        Armor temp = chooseArmor();
        if (temp == null) {
            return false;
        }
        if (armor != null) {
            items.add(armor);
            armors.add(armor);
        }
        armor = temp;
        return true;
    }

    /*
        select armor
     */
    public Armor chooseArmor() {
        if (armors.isEmpty()) {
            System.out.println("you dont have armor right now!");
            return null;
        }
        printArmorInventory();
        System.out.println("choose an armor from inventory: ");
        return armors.remove(ScannerUtil.getInt(1, armors.size()) - 1);
    }

    /*
        print armor inventory
     */
    public void printArmorInventory() {
        System.out.println(" armor inventory: " + getName() + " ");
        System.out.println("Name                    Damage Reduction");
        System.out.println(ScannerUtil.splitLine);
        int index = 1;
        for (Item item : items) {
            if (item instanceof Armor) {
                String name = index + ". " + item.getName();
                int reduction = ((Armor) item).getDamageReduction();
                ++index;
                System.out.printf("%-28s %-5d", name, reduction);
                System.out.println();
            }
        }
        System.out.println(ScannerUtil.splitLine);
    }

    /*
        change weapon
     */
    public boolean changeAWeapon() {
        Weapon temp = chooseWeapon();
        if (temp == null) {
            return false;
        }
        if (weapon != null) {
            items.add(weapon);
            weapons.add(weapon);
        }
        weapon = temp;
        return true;
    }

    /*
        hero return back to nexus
     */
    public void backToNexus(ValorMap m) {
        // set left market of current cell to “ ”
        m.getMap()[getRow()][getCol()].setLeftMarker("  ");
        m.getMap()[getRow()][getCol()].setMiddle();
        // update new row and col for hero
        setRow(m.getSize() - 1);
        setCol(getStartingCol());
        // set left marker of new cell to current hero's marker
        m.getMap()[getRow()][getCol()].setLeftMarker(getHeroMarker());
        m.getMap()[getRow()][getCol()].setMiddle();
    }

    /*
      teleport to another lane
     */
    public boolean teleport(ValorMap m, ArrayList<Monster> monsters) {
        System.out.println(" which cell you want to teleport to ? ");
        System.out.println("enter the row you want to teleport: ");
        int newRow = ScannerUtil.getInt(0, m.getSize());
        System.out.println("enter the col you want to teleport: ");
        int newCol = ScannerUtil.getInt(0, m.getSize());
        if (isInaccessibleCell(m, newRow, newCol)) {
            System.out.println("you cant teleport to inaccessible cell!");
        } else if (isSameLane(newCol)) {
            System.out.println("you cant teleport to same lane!");
        } else if (!isValidCell(m, newRow, newCol)) {
            System.out.println("you cant teleport to this cell because it's not explored yet!");
        } else if (isToken(m, newRow, newCol)) {
            System.out.println("you cant teleport to this cell because it's already token!");
        } else if (isValidCell(m, newRow, newCol) && isBehindMonsters(m, newRow, newCol, monsters)) {
            System.out.println("you cant teleport to the cell that behind monster!");
        } else {
            System.out.println(" teleport successfully! ");
            m.getMap()[getRow()][getCol()].setLeftMarker("  ");
            m.getMap()[getRow()][getCol()].setMiddle();
            m.getMap()[getRow()][getCol()].setHasHeroes(false);
            setRow(newRow);
            setCol(newCol);
            m.getMap()[getRow()][getCol()].setLeftMarker(getHeroMarker());
            m.getMap()[getRow()][getCol()].setMiddle();
            m.getMap()[getRow()][getCol()].setHasHeroes(true);
            return true;
        }
        return false;
    }

    /*
        check if the cell is inaccessible cell
     */
    private boolean isInaccessibleCell(ValorMap m, int newRow, int newCol) {
        return m.getMap()[newRow][newCol] instanceof InaccessibleCell;
    }

    /*
        check if the new cell is in the same lane as current cell
     */
    private boolean isSameLane(int newCol) {
        return Math.abs(newCol - getCol()) <= 1;
    }

    /*
        check if the cell is explored yet
     */
    private boolean isValidCell(ValorMap m, int newRow, int newCol) {
        int lane = calculateLane(m, newRow, newCol);

        return newRow >= m.getMaxExploredLevels()[lane];
    }

    /*
        check if the cell is token
     */
    private boolean isToken(ValorMap map, int newRow, int newCol) {
        return !map.getMap()[newRow][newCol].getLeftMarker().equals("  ");
    }

    private int calculateLane(ValorMap map, int newRow, int newCol) {
        int lane;
        if (newCol <= 1) {
            lane = 0;
        } else if (map.getSize() - newCol <= 2) {
            lane = 2;
        } else {
            lane = 1;
        }
        return lane;
    }

    /*
        check if the cell is behind monsters
     */
    private boolean isBehindMonsters(ValorMap map, int newRow, int newCol, ArrayList<Monster> monsters) {
        int lane = calculateLane(map, newRow, newCol);
        for (Monster m : monsters) {
            int monsterLane = calculateLane(map, m.getRow(), m.getCol());

            if (lane == monsterLane) {
                if (newRow < m.getRow()) {
                    return true;
                }
            }
        }

        return false;
    }

    public double getMana() {
        return mana;
    }

    public double getStrength() {
        return strength;
    }

    public double getDexterity() {
        return dexterity;
    }

    public double getAgility() {
        return agility;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public int getExp() {
        return exp;
    }

    public int getExpBonus() {
        return expBonus;
    }

    public int getStartingCol() {
        return startingCol;
    }

    public String getHeroMarker() {
        return heroMarker;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public HeroLevelUpHandler getLevelUpHandler() {
        return levelUpHandler;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHeroMarker(String heroMarker) {
        this.heroMarker = heroMarker;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setArmors(ArrayList<Armor> armors) {
        this.armors = armors;
    }

    public void setPotions(ArrayList<Potion> potions) {
        this.potions = potions;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public void setLevelUpHandler(HeroLevelUpHandler levelUpHandler) {
        this.levelUpHandler = levelUpHandler;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}