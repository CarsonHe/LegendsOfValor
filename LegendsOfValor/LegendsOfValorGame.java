import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;




public class LegendsOfValorGame extends Game {
    // hero list
    private ArrayList<Hero> team;
    // monsters list
    private final ArrayList<Monster> monsters;
    // dead heroes list
    private final ArrayList<Hero> deadHeroes;
    // reset hero after battle
    private final ArrayList<Hero> resetHeroes;
    //map
    private final ValorMap valorMap;
    // market
    private final Market market;
    // number of rounds
    private int numOfRounds;
    // squard size of monster
    private int monsterSquadSize;
    // check if hero wins
    private boolean heroReachesMonsterNexus;
    // check if monster wins
    private boolean monsterReachesHeroNexus;

    public LegendsOfValorGame() {
        team = new ArrayList<>();
        monsters = new ArrayList<>();
        deadHeroes = new ArrayList<>();
        resetHeroes = new ArrayList<>();
        valorMap = new ValorMap(8);
        market = new Market();
        numOfRounds = 0;
        monsterSquadSize = 0;
        heroReachesMonsterNexus = false;
        monsterReachesHeroNexus = false;
    }


    @Override
    public void play() {
        System.out.println(" Welcome to Legends of Valor! ");
        System.out.println("In this game you can form a team as 3 heroes to fight the monster.");
        System.out.println("initial cell is your nexus where you can buy or sell items.");
        System.out.println("you can return to nexus whenever you want.");
        System.out.println("you can not access to all inaccessible cell!");
        System.out.println();
        /*
         * operation
         */
        System.out.println("-------------------operation--------------------------");
        System.out.println("W/w:up; A/a:left; S/s:down; D/d:right");
        System.out.println("Q/q:quit; I/i:hero's information; O/o:change weapon;");
        System.out.println("P/p:change armor; U/u:use potion; F/f:attack;");
        System.out.println("C/c:use spell; T/t:teleport; B/b:back to Nexus;");
        System.out.println("V/v:market; M/m:show inventory;");
        System.out.println("----------------------------------------------------");
        formHeroTeam();
        printTeamMembers();
        formResetHeroes();
        initializeMonsters();
        printMonsters();

        while (true) {
            if (numOfRounds == 8) {
                System.out.println("8 rounds away, there are 3 new monsters coming!");
                initializeMonsters();
                printMonsters();

                numOfRounds = 0;
            }
            valorMap.printMap();
            for (int i = 0; i < team.size(); ++i) {
                //get hero's action
                String action = getAction(i);
                if (!isValidAction(action, i) || !performAction(action, i)) {
                    --i;
                    continue;
                }
                if (action.equalsIgnoreCase("V")){
                    --i;
                }

            }
            if (hasGameFinished()) {
                quit();
                return;
            }
            valorMap.printMap();
            System.out.println("monster move down or attack hero if hero is in range.");

            for (int i = 0; i < monsters.size(); ++i) {
                if (shouldMonsterAttack(i)) {
                    monsterAttack(i);
                } else if (!hasMonsterOneStepAhead(i)) {
                    monsterMove(i);
                }
                hasGameFinished();
            }

            numOfRounds++;

            if (!hasGameFinished() && !deadHeroes.isEmpty()) {
                respawn();
                sortHeroTeam();
            }
        }

    }


    /**
     * select 3 heroes
     */
    private void formHeroTeam() {
        HeroTeam temp = new HeroTeam();
        HeroList.printHeroList();
        for (int i = 1; i <= 3; ++i) {
            System.out.println("select hero " + i + " :");
            int choice = ScannerUtil.getInt(1, HeroList.getList().size());
            temp.addMembers(HeroList.getList().get(choice - 1));
            team = temp.getTeam();
            team.get(i - 1).setRow(valorMap.getSize() - 1);
            team.get(i - 1).setCol(3 * (i - 1));
            team.get(i - 1).setStartingCol(3 * (i - 1));
            team.get(i - 1).setHeroMarker(valorMap.getMap()[valorMap.getSize() - 1][3 * (i - 1)].getLeftMarker());
        }
    }


    /**
     * print team member
     */
    public void printTeamMembers() {
        if (team.isEmpty()) {
            return;
        }
        System.out.println("hero List");
        String name;
        double hp, strength, dexterity, agility, money, mana;
        int level, exp;
        System.out.println("name                       HP     level    mana      strength          dexterity         agility       money      exp");
        System.out.println(ScannerUtil.splitLine);

        for (Hero hero : team) {
            name = valorMap.getMap()[hero.getRow()][hero.getCol()].getLeftMarker() + ". " + hero.getName();
            hp = hero.getHp();
            level = hero.getLevel();
            mana = hero.getMana();
            strength = hero.getStrength();
            dexterity = hero.getDexterity();
            agility = hero.getAgility();
            money = hero.getMoney();
            exp = hero.getExp();

            System.out.printf("%-25s %-8.0f %-6d %-9.0f %-12.0f %-11.0f %-9.0f %-8.0f %-5d", name, hp, level, mana, strength, dexterity, agility, money, exp);
            System.out.println();
        }
        System.out.println(ScannerUtil.splitLine);
    }

    /**
     * initialized Hero
     */
    private void formResetHeroes() {
        for (int i = 0; i < team.size(); ++i) {

            Hero hero = team.get(i);

            resetHeroes.add(new Hero(hero.getName(), hero.getLevel(), hero.getMana(), (int) hero.getStrength(), (int) hero.getDexterity(), (int) hero.getAgility(), hero.getMoney(), hero.getExp()));

            resetHeroes.get(i).setRow(hero.getRow());
            resetHeroes.get(i).setCol(hero.getCol());
            resetHeroes.get(i).setStartingCol(hero.getStartingCol());
            resetHeroes.get(i).setHeroMarker(hero.getHeroMarker());
        }
    }


    // initialize monsters
    private void initializeMonsters() {
        System.out.println("ready to fight!");

        // monster with same level
        List<Monster> sameLevelMonsters = new ArrayList<>();
        Random seed = new Random();
        for (int i = 0; i < team.size(); ++i) {
            List<Monster> monsterList = new MonsterList().getMonsters();
            for (Monster monster : monsterList) {
                if (team.get(i).getLevel() == monster.getLevel()) {
                    sameLevelMonsters.add(monster);
                }
            }
            // add a monster in random to monster list
            int monsterIndex = seed.nextInt(sameLevelMonsters.size());
            monsters.add(sameLevelMonsters.get(monsterIndex));
            ++monsterSquadSize;
            int size = monsters.size();
            // get monster's coordinates
            monsters.get(size - 1).setRow(0);
            monsters.get(size - 1).setCol(3 * i);
            if (numOfRounds == 8) {
                //if monsters created after 8 rounds, set right marker to M
                valorMap.getMap()[0][3 * i].setRightMarker("M" + monsterSquadSize);
                valorMap.getMap()[0][3 * i].setMiddle();
            }
            monsters.get(size - 1).setMonsterMarker(valorMap.getMap()[0][3 * i].getRightMarker());
            valorMap.getMap()[monsters.get(size - 1).getRow()][monsters.get(size - 1).getCol()].setHasMonsters(true);
            sameLevelMonsters.clear();
        }
    }

    /**
     * print monsters' data
     */
    private void printMonsters() {
        if (monsters.isEmpty()) {
            return;
        }
        System.out.println(" Monsters List: ");

        String name;
        double hp, damage, defenseStat, dodgeChance;
        int level;
        System.out.println("name                       HP    level      damage        defense         dodge chance");
        System.out.println(ScannerUtil.splitLine);
        for (Monster monster : monsters) {
            // 打印所有怪物及其统计数据
            name = valorMap.getMap()[monster.getRow()][monster.getCol()].getRightMarker() + ". " + monster.getName();
            hp = monster.getHp();
            level = monster.getLevel();
            damage = monster.getDamage();
            defenseStat = monster.getDefenseStat();
            dodgeChance = monster.getDodgeChance();

            System.out.printf("%-25s %-8.0f %-7d %-9.0f %-11.0f %-6.2f", name, hp, level, damage, defenseStat, dodgeChance);
            System.out.println();
        }
        System.out.println(ScannerUtil.splitLine);
    }

    /*
     get hero's action
     */
    private String getAction(int heroIndex) {

        System.out.println("H" + (heroIndex + 1) + ",what action will you take:");
        /*
         * operation
         */
        System.out.println("-------------------operation--------------------------");
        System.out.println("W/w:up; A/a:left; S/s:down; D/d:right");
        System.out.println("Q/q:quit; I/i:hero's information; O/o:change weapon;");
        System.out.println("P/p:change armor; U/u:use potion; F/f:attack;");
        System.out.println("C/c:use spell; T/t:teleport; B/b:back to Nexus;");
        System.out.println("V/v:market; M/m:show inventory;");
        System.out.println("please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]: ");
        String action;

        while (true) {
            //enter action
            action = ScannerUtil.getString();
            if (!action.equalsIgnoreCase("W") && !action.equalsIgnoreCase("A") && !action.equalsIgnoreCase("S") && !action.equalsIgnoreCase("D") && !action.equalsIgnoreCase("Q") && !action.equalsIgnoreCase("I") && !action.equalsIgnoreCase("O") && !action.equalsIgnoreCase("P") && !action.equalsIgnoreCase("F") && !action.equalsIgnoreCase("C") && !action.equalsIgnoreCase("T") && !action.equalsIgnoreCase("B") && !action.equalsIgnoreCase("V") && !action.equalsIgnoreCase("M") && !action.equalsIgnoreCase("U")) {
                // invalid input
                System.out.println("invalid input！please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]: ");
                continue;
            }

            //如果玩家输入i/i或m/m，打印相关统计数据并继续循环
            if (action.equalsIgnoreCase("I")) {
                printTeamMembers();
                System.out.println("please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]: ");
            } else if (action.equalsIgnoreCase("M")) {
                team.get(heroIndex).printInventory();
                System.out.println("please enter: [w/a/s/d/q/i/o/p/f/c/t/b/v/m/u]: ");
            } else {
                break;
            }
        }
        return action;
    }

    /*
        check if is valid action.
     */
    private boolean isValidAction(String action, int heroIndex) {
        Hero h = team.get(heroIndex);
        Cell[][] m = valorMap.getMap();
        if (action.equalsIgnoreCase("Q")) {
            return true;
        }
        if ((action.equalsIgnoreCase("A") && (h.getCol() - 1 < 0))
                || (action.equalsIgnoreCase("D") && (h.getCol() + 1 >= valorMap.getSize()))
                || (action.equalsIgnoreCase("S") && (h.getRow() + 1 >= valorMap.getSize()))
                || (action.equalsIgnoreCase("W") && (h.getRow() - 1 < 0))) {
            System.out.println("out of bound!");
            return false;
        }
        if (action.equalsIgnoreCase("A") && (m[h.getRow()][h.getCol() - 1] instanceof InaccessibleCell)
                || action.equalsIgnoreCase("D") && (m[h.getRow()][h.getCol() + 1] instanceof InaccessibleCell)
                || action.equalsIgnoreCase("S") && (m[h.getRow() + 1][h.getCol()] instanceof InaccessibleCell)
                || action.equalsIgnoreCase("W") && (m[h.getRow() - 1][h.getCol()] instanceof InaccessibleCell))
        {
            System.out.println("you cant access to inaccessible cell!");
            return false;
        }
        if (action.equalsIgnoreCase("W")) {
            if ((m[h.getRow()][h.getCol()].isHasMonsters())
                    || (h.getCol() - 1 >= 0
                    && m[h.getRow()][h.getCol() - 1].isHasMonsters()) || (h.getCol() + 1 < valorMap.getSize()
                    && m[h.getRow()][h.getCol() + 1].isHasMonsters())) {
                System.out.println("monster does not die and you cant move");
                return false;
            }
        }
        if (action.equalsIgnoreCase("A")
                && (!m[h.getRow()][h.getCol() - 1].getLeftMarker().equals("  ")) || action.equalsIgnoreCase("D")
                && (!m[h.getRow()][h.getCol() + 1].getLeftMarker().equals("  ")) || action.equalsIgnoreCase("S")
                && (!m[h.getRow() + 1][h.getCol()].getLeftMarker().equals("  ")) || action.equalsIgnoreCase("W")
                && (!m[h.getRow() - 1][h.getCol()].getLeftMarker().equals("  "))) {
            System.out.println("you cant move to the cell that is token");
            return false;
        }
        if (action.equalsIgnoreCase("F") || action.equalsIgnoreCase("C")) {
            int size = valorMap.getSize();
            if ((h.getCol() + 1 < size
                    && m[h.getRow()][h.getCol() + 1].isHasMonsters()) || (h.getCol() - 1 >= 0
                    && m[h.getRow()][h.getCol() - 1].isHasMonsters()) || (h.getRow() - 1 >= 0
                    && m[h.getRow() - 1][h.getCol()].isHasMonsters()) || (h.getRow() - 1 >= 0
                    && h.getCol() + 1 < size
                    && m[h.getRow() - 1][h.getCol() + 1].isHasMonsters()) || (h.getRow() - 1 >= 0
                    && h.getCol() - 1 >= 0
                    && m[h.getRow() - 1][h.getCol() - 1].isHasMonsters()) || (m[h.getRow()][h.getCol()].isHasMonsters())) {
                //return true if neighbor cell has monster
                return true;
            } else {
                // if no monster in neighbor or current cell, cant attack or use spell
                System.out.println("there is no monster and you cant attack");
                return false;
            }
        }

        if (action.equalsIgnoreCase("V") && (!(valorMap.getMap()[h.getRow()][h.getCol()] instanceof HeroNexusCell))) {
            //hero cant access to market if not in nexus
            System.out.println("you are not in the nexus and you cant go to market");
            return false;
        }
        return true;
    }



    /*
       perform action
     */
    private boolean performAction(String action, int heroIndex) {
        if (action.equalsIgnoreCase("Q")) {
            //quit
            quit();
        }
        if (action.equalsIgnoreCase("V")) {
            // market
            return  visitMarket(heroIndex);
        }
        if (action.equalsIgnoreCase("O")) {
            // change weapon
            return team.get(heroIndex).changeAWeapon();
        }
        if (action.equalsIgnoreCase("P")) {
            // change armor
            return team.get(heroIndex).changeAnArmor();
        }
        if (action.equalsIgnoreCase("U")) {
            // use potion
            return team.get(heroIndex).useAPotion();
        }
        if (action.equalsIgnoreCase("B")) {
            // return to nexus
            team.get(heroIndex).backToNexus(valorMap);
        }
        if (action.equalsIgnoreCase("T")) {
            //teleport
            return team.get(heroIndex).teleport(valorMap, monsters);
        }
        if (action.equalsIgnoreCase("W") || action.equalsIgnoreCase("A")
                || action.equalsIgnoreCase("S") || action.equalsIgnoreCase("D")) {
            //move
            heroMove(action, heroIndex);
        }
        if (action.equalsIgnoreCase("F")) {
            // attack
            Monster target = assignAMonster(heroIndex);
            team.get(heroIndex).attack(target, valorMap);
            processTargetStatus(target);
            printMonsters();
        }
        if (action.equalsIgnoreCase("C")) {
            Monster m = assignAMonster(heroIndex);
            //use spell
            boolean hasSucceeded = team.get(heroIndex).castASpell(m, valorMap);
            processTargetStatus(m);
            printMonsters();
            return hasSucceeded;
        }
        return true;
    }

    /**
     * access to market
     */
    private boolean visitMarket(int heroIndex) {
        System.out.println(" welcome to the market!  ");
        System.out.println("1. buy     2. sell     3. finish and end");
        System.out.println("please select:");
        int choice = ScannerUtil.getInt(1, 3);
        if (choice == 1) {
            market.printMarket();
            if (team.get(heroIndex).buy(chooseAPropFromMarket())) {
                team.get(heroIndex).printInventory();
            }
        } else if (choice == 2) {
            team.get(heroIndex).printInventory();
            int propIndex = chooseAPropFromInventory(team.get(heroIndex));
            if (propIndex != Integer.MIN_VALUE) {
                team.get(heroIndex).sell(propIndex);
                team.get(heroIndex).printInventory();
            }
        }
        return true;
    }

    /*
        choose item from market
     */
    private Item chooseAPropFromMarket() {
        int i = ScannerUtil.getInt(1, market.getItems().size());
        return market.getItems().get(i - 1);
    }

    /**
     * hero select item from inventory
     */
    private int chooseAPropFromInventory(Hero hero) {
        if (hero.getItems().isEmpty()) {
            System.out.println("your inventory is empty so you cant sell!");
            return Integer.MIN_VALUE;
        }
        return ScannerUtil.getInt(1, market.getItems().size());
    }

    /**
     * hero move
     */
    private void heroMove(String action, int heroIndex) {
        Hero h = team.get(heroIndex);
        Cell[][] m = valorMap.getMap();
        m[h.getRow()][h.getCol()].setLeftMarker("  ");
        m[h.getRow()][h.getCol()].setMiddle();
        m[h.getRow()][h.getCol()].setHasHeroes(false);
        if (action.equalsIgnoreCase("W")) {
            h.setRow(h.getRow() - 1);
            int lane;

            if (h.getCol() <= 1) {
                lane = 0;
            } else if (valorMap.getSize() - h.getCol() <= 2) {
                lane = 2;
            } else {
                lane = 1;
            }
            valorMap.updateExploredLevel(lane);
        } else if (action.equalsIgnoreCase("A")) {
            h.setCol(h.getCol() - 1);
        } else if (action.equalsIgnoreCase("S")) {
            h.setRow(h.getRow() + 1);
        } else if (action.equalsIgnoreCase("D")) {
            h.setCol(h.getCol() + 1);
        }
        m[h.getRow()][h.getCol()].setLeftMarker(h.getHeroMarker());
        m[h.getRow()][h.getCol()].setMiddle();
        m[h.getRow()][h.getCol()].setHasHeroes(true);
        if (h.getRow() == 0) {
            heroReachesMonsterNexus = true;
        }
    }

    /*
        attack monster with the lowest hp
     */
    private Monster assignAMonster(int heroIndex) {
        System.out.println("attack monster with lowest hp!");
        Hero h = team.get(heroIndex);
        Monster res = null;
        double minHp = Double.MAX_VALUE;
        for (Monster temp : monsters) {
            double distanceSquare = Math.pow(h.getRow() - temp.getRow(), 2) + Math.pow(h.getCol() - temp.getCol(), 2);
            if (distanceSquare <= 2 && temp.getHp() < minHp) {
                minHp = temp.getHp();
                res = temp;
            }
        }
        return res;
    }

    /**
     * hero move to hero's nexus
     */
    private void monsterMove(int monsterIndex) {
        Monster monster = monsters.get(monsterIndex);
        Cell[][] m = valorMap.getMap();
        System.out.println("monster " + monster.getMonsterMarker() + ":" + monster.getName() + " move forward");
        //set right marker of current cell to " "
        m[monster.getRow()][monster.getCol()].setRightMarker("  ");
        m[monster.getRow()][monster.getCol()].setMiddle();
        //monster is leaving it so the cell does not have monster
        m[monster.getRow()][monster.getCol()].setHasMonsters(false);
        monster.setRow(monster.getRow() + 1); // Go down (i.e. towards the hero nexus)
        //right marker of new cell to M1,M2,M3
        m[monster.getRow()][monster.getCol()].setRightMarker(monster.getMonsterMarker());
        m[monster.getRow()][monster.getCol()].setMiddle();
        //monster in the new cell
        m[monster.getRow()][monster.getCol()].setHasMonsters(true);
        // if monster reaches to hero's nexus
        if (monster.getRow() == valorMap.getSize() - 1) {
            monsterReachesHeroNexus = true;
        }
    }

    /**
     * check if the monster should attack in the round
     */
    private boolean shouldMonsterAttack(int monsterIndex) {
        Monster monster = monsters.get(monsterIndex);
        Cell[][] m = valorMap.getMap();
        int size = valorMap.getSize();
        if (monster.isDead()) {
            return false;
        }
        if (monster.getRow() + 1 == size - 1) {
            // one cell away from hero's cell
            if (!((monster.getCol() + 1 < size && m[monster.getRow()][monster.getCol() + 1].isHasHeroes())
                    || (monster.getCol() - 1 >= 0 && m[monster.getRow()][monster.getCol() - 1].isHasHeroes())
                    || (m[monster.getRow()][monster.getCol()].isHasHeroes()))) {
                //if left or right side of monster does not have hero in it
                //monster move forward instead of attack
                return false;
            }
        }
        //check if  there is hero in neighbor
        boolean flag = (monster.getCol() + 1 < size && m[monster.getRow()][monster.getCol() + 1].isHasHeroes()) || // right side

                (monster.getCol() - 1 >= 0 && m[monster.getRow()][monster.getCol() - 1].isHasHeroes()) || // left side

                (monster.getRow() + 1 < size && m[monster.getRow() + 1][monster.getCol()].isHasHeroes()) || // down

                (monster.getRow() + 1 < size && monster.getCol() + 1 < size &&
                        m[monster.getRow() + 1][monster.getCol() + 1].isHasHeroes()) || // down right

                (monster.getRow() + 1 < size && monster.getCol() - 1 >= 0 &&
                        m[monster.getRow() + 1][monster.getCol() - 1].isHasHeroes()) || // down left

                (m[monster.getRow()][monster.getCol()].isHasHeroes()); // monster

        return flag;
    }

    /*
       check if monster is one cell ahead of another monster
       if true current monster cant move forward since one cell cant have two monsters
     */
    private boolean hasMonsterOneStepAhead(int monsterIndex) {
        Monster monster = monsters.get(monsterIndex);
        Cell[][] m = valorMap.getMap();
        int size = valorMap.getSize();
        return (monster.getRow() + 1 < size) && !m[monster.getRow() + 1][monster.getCol()].getRightMarker().equals("  ");
    }

    /**
     * monster attack
     */
    private void monsterAttack(int monsterIndex) {
        Monster m = monsters.get(monsterIndex);
        Hero target = assignAHero(monsterIndex); // 让目标英雄攻击
        if(target==null){
            if (!hasMonsterOneStepAhead(monsterIndex)) {
                monsterMove(monsterIndex);
            }
        }else {
            System.out.println("monster " + m.getMonsterMarker() + ":" + m.getName() + " attack " + target.getHeroMarker() + ": " + target.getName() + " ");
            m.attack(target, valorMap); //attack target hero
            processTargetStatus(target);}
        printTeamMembers();
    }

    /**
     * there might be more than one hero near the monster
     *      * monster attack the hero with lowest hp
     */
    private Hero assignAHero(int monsterIndex) {
        //assign lowest hero nearby to the monster
        Monster m = monsters.get(monsterIndex);
        Hero res = null;
        double minHp = Double.MAX_VALUE;
        for (Hero h : team) {
            //square distance between hero and monster
            double distanceSquare = Math.pow(h.getRow() - m.getRow(), 2) + Math.pow(h.getCol() - m.getCol(), 2);
            if (distanceSquare <= 2 && h.getHp() < minHp) {
                minHp = h.getHp();
                res = h;
            }
        }

        return res;
    }

    /*
       process current role's status after battle
     */
    private void processTargetStatus(Role role) {
        if (role instanceof Hero) {
            Hero h = (Hero) role;
            if (h.hasDead()) {
                //hero dies
                deadHeroes.add(h);
                team.remove(h);
                valorMap.getMap()[h.getRow()][h.getCol()].setLeftMarker("  ");
                valorMap.getMap()[h.getRow()][h.getCol()].setMiddle();
                valorMap.getMap()[h.getRow()][h.getCol()].setHasHeroes(false);
            } else {
                //hero get money and exp if they survive from the battle
                h.setMoney(h.getMoney() + 100 * h.getLevel());
                h.setExpBonus(5 * h.getLevel());
                //level up
                h.performLevelUp();
                // regain hp and mana
                h.setHp(h.getHp() * 1.1);
                h.setMana(h.getMana() * 1.1);
            }
        }

        if (role instanceof Monster) {
            Monster m = (Monster) role;
            if (m.isDead()) {
                // remove dead monster
                monsters.remove(m);
                valorMap.getMap()[m.getRow()][m.getCol()].setRightMarker("  ");
                valorMap.getMap()[m.getRow()][m.getCol()].setMiddle();
                valorMap.getMap()[m.getRow()][m.getCol()].setHasMonsters(false);
            }
        }
    }

    /*
       hero respawn
     */
    private void respawn() {
        Iterator<Hero> iterator = deadHeroes.iterator();
        while (iterator.hasNext()) {
            Hero hero = iterator.next();
            for (Hero resetHero : resetHeroes) {
                // half hp and half mana
                if (hero.getName().equalsIgnoreCase(resetHero.getName())) {
                    hero.setHp(resetHero.getHp() * 0.5);
                    hero.setMana(resetHero.getMana() * 0.5);
                    break;
                }
            }
            //set initial coordinates to it
            hero.setRow(valorMap.getSize() - 1);
            hero.setCol(hero.getStartingCol());
            team.add(hero);
            iterator.remove(); // remove hero from deadHeroes list
            // set the left marker of cell to hero's symbol
            valorMap.getMap()[hero.getRow()][hero.getCol()].setLeftMarker(hero.getHeroMarker());
            valorMap.getMap()[hero.getRow()][hero.getCol()].setMiddle();
            // hero is in the cell
            valorMap.getMap()[hero.getRow()][hero.getStartingCol()].setHasHeroes(true);
        }
    }

    /*
        sort hero team in ascending order
     */
    private void sortHeroTeam() {
        team.sort((h1, h2) -> {
            int index1 = Character.getNumericValue(h1.getHeroMarker().charAt(1));
            int index2 = Character.getNumericValue(h2.getHeroMarker().charAt(1));
            return index1 - index2;
        });
    }

    /**
     * check if game is finished
     */
    public boolean hasGameFinished() {
        if (team.isEmpty()) {
            System.out.println("all the hero dies, monster wins!");
            System.out.println("Do you want to play another game! Please enter y/n to choose");
            ScannerUtil.getYesOrNo();
            return true;
        } else if (heroReachesMonsterNexus) {

            valorMap.printMap();
            System.out.println("hero first reaches monster's nexus！Hero wins!");
            System.out.println("Do you want to play another game! Please enter y/n to choose");
            ScannerUtil.getYesOrNo();
            return true;
        } else if (monsterReachesHeroNexus) {

            valorMap.printMap();
            System.out.println("monster first reaches hero's nexus！Monster wins!");
            System.out.println("Do you want to play another game! Please enter y/n to choose");
            ScannerUtil.getYesOrNo();
            return true;
        } else {
            //game keep going
            return false;
        }
    }

    /*
       quit game
     */
    @Override
    public void quit() {
        System.out.println("Bye");
        System.exit(0);
    }
}