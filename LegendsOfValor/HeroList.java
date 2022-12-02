import java.util.ArrayList;

/**
 * all the hero in the game
 */
public class HeroList {
    private static ArrayList<Hero> list;

    public HeroList() {}

    private static void setHeroes() {
        list = new ArrayList<>();

        list.add(new Warrior("Gaerdal_Ironhand", 1, 100, 700, 500, 600, 1354, 7));
        list.add(new Warrior("Sehanine_Monnbow", 1, 600, 700, 800, 500, 2500, 8));
        list.add(new Warrior("Muamman_Duathall", 1, 300, 900, 500, 750, 2546, 6));
        list.add(new Warrior("Flandal_Steelskin", 1, 200, 750, 650, 700, 2500, 7));
        list.add(new Warrior("Undefeated_Yoj", 1, 400, 800, 400, 700, 2500, 7));
        list.add(new Warrior("Eunoia_Cyn", 1, 400, 700, 800, 600, 2500, 6));

        list.add(new Sorcerer("Rillifane_Rallathil", 1, 1300, 750, 450, 500, 2500, 9));
        list.add(new Sorcerer("Segojan_Earthcaller", 4, 900, 800, 500, 2500, 2500, 5));
        list.add(new Sorcerer("Reign_Havoc", 1, 800, 800, 800, 800, 2500, 8));
        list.add(new Sorcerer("Reverie_Ashels", 1, 900, 800, 700, 400, 2500, 7));
        list.add(new Sorcerer("Kalabar", 1, 800, 850, 400, 600, 2500, 6));
        list.add(new Sorcerer("Skye_Soar", 1, 1000, 700, 400, 500, 2500, 5));

        list.add(new Paladin("Parzival", 1, 300, 750, 650, 700, 2500, 9));
        list.add(new Paladin("Sehanine_Moonbow", 1, 300, 750, 700, 700, 2500, 9));
        list.add(new Paladin("Skoraeus_Stonebones", 1, 250, 650, 600, 350, 2500, 4));
        list.add(new Paladin("Garl_Glittergold", 1, 100, 600, 500, 400, 2500, 5));
        list.add(new Paladin("Amaryllis_Astra", 1, 500, 500, 500, 500, 2500, 5));
        list.add(new Paladin("Caliber_Heist", 1, 400, 400, 400, 400, 2500, 8));
    }

    public static ArrayList<Hero> getList() {
        return list;
    }


    /**
     * print hero list
     */
    public static void printHeroList() {
        setHeroes();
        String name;
        double hp, strength, dexterity, agility, money, mana;
        int level, exp;
        System.out.println("                                         <<Hero List>>                               ");
        String title = "name                       HP    level      mana         strength       dexterity      agility       money        exp";
        System.out.println("Warriors: ");
        System.out.println(title);
        System.out.println(ScannerUtil.splitLine);

        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) instanceof Sorcerer && list.get(i - 1) instanceof Warrior) {
                System.out.println("Sorcerers: ");
                System.out.println(title);
                System.out.println(ScannerUtil.splitLine);
            } else if (list.get(i) instanceof Paladin && list.get(i - 1) instanceof Sorcerer) {
                System.out.println("Paladins: ");
                System.out.println(title);
                System.out.println(ScannerUtil.splitLine);
            }

            name = (i + 1) + ". " + list.get(i).getName();
            hp = list.get(i).getHp();
            level = list.get(i).getLevel();
            mana = list.get(i).getMana();
            strength = list.get(i).strength;
            dexterity = list.get(i).dexterity;
            agility = list.get(i).agility;
            money = list.get(i).getMoney();
            exp = list.get(i).exp;

            System.out.printf("%-25s %-8.0f %-6d %-9.0f %-12.0f %-11.0f %-9.0f %-8.0f %-5d", name, hp, level, mana, strength,
                    dexterity, agility , money, exp);
            System.out.println();
            if ((i + 1 < list.size()) && (!list.get(i).getClass().getName().equals
                    (list.get(i + 1).getClass().getName()))) {
                System.out.println(ScannerUtil.splitLine);
            }
        }
        System.out.println(ScannerUtil.splitLine);
    }
}
