import java.util.ArrayList;

/**
 * market class store all the items
 */
public class Market {
    private final ArrayList<Weapon> weapons;
    private final ArrayList<Armor> armors;
    private final ArrayList<Potion> potions;
    private final ArrayList<Spell> spells;
    private final ArrayList<Item> items;

    public Market() {
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        potions = new ArrayList<>();
        spells = new ArrayList<>();
        items = new ArrayList<>();

        weapons.add(new Weapon("Sword", 500, 1, 800, 1));
        weapons.add(new Weapon("Bow", 300, 2, 500, 2));
        weapons.add(new Weapon("Scythe", 1000, 6, 1100, 2));
        weapons.add(new Weapon("Axe", 550, 5, 850, 1));
        weapons.add(new Weapon("TSwords", 1400, 8, 1600, 2));
        weapons.add(new Weapon("Dagger", 200, 1, 250, 1));


        armors.add(new Armor("Platinum_Shield", 150, 1, 200));
        armors.add(new Armor("Breastplate", 350, 3, 600));
        armors.add(new Armor("Full_Body_Armor", 1000, 8, 1100));
        armors.add(new Armor("Wizard_Shield", 1200, 10, 1500));
        armors.add(new Armor("Guardian_Angel", 1000, 10, 1000));

        potions.add(new Potion("Healing_Potion", 250, 1, 100, "Health"));
        potions.add(new Potion("Strength_Potion", 200, 1, 75, "Strength"));
        potions.add(new Potion("Magic_Potion", 350, 2, 100, "Mana"));
        potions.add(new Potion("Luck_Elixir", 500, 4, 65, "Agility"));
        potions.add(new Potion("Mermaid_Tears", 850, 5, 100, "Health|Mana|Strength|Agility"));
        potions.add(new Potion("Ambrosia", 1000, 8, 150, "All Health|Mana|Strength|Dexterity|Defense|Agility"));

        spells.add(new IceSpell("Snow_Cannon", 500, 2, 650, 250));
        spells.add(new IceSpell("Ice_Blade", 250, 1, 450, 100));
        spells.add(new IceSpell("Frost_Blizzard", 750, 5, 850, 350));
        spells.add(new IceSpell("Arctic_Storm", 700, 6, 800, 300));

        spells.add(new FireSpell("Flame_Tornado", 700, 4, 850, 300));
        spells.add(new FireSpell("Breath_of_Fire", 350, 1, 450, 100));
        spells.add(new FireSpell("Heat_Wave", 450, 2, 600, 150));
        spells.add(new FireSpell("Lava_Comet", 800, 7, 1000, 550));
        spells.add(new FireSpell("Hell_Storm", 600, 3, 950, 600));

        spells.add(new LightningSpell("Lightning_Dagger", 400, 1, 500, 150));
        spells.add(new LightningSpell("Thunder_Blast", 750, 4, 950, 400));
        spells.add(new LightningSpell("Electric_Arrows", 550, 5, 650, 200));
        spells.add(new LightningSpell("Spark_Needles", 500, 2, 600, 200));

        items.addAll(weapons);
        items.addAll(armors);
        items.addAll(potions);
        items.addAll(spells);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
        print all the items
     */
    public void printMarket() {
        System.out.println("welcome to the market, you can buy or sell here");
        // 分割线
        String splitLine = "------------------------------------------------------------------------------------------";

        System.out.println("Weapons List");
        System.out.println("name                   price           level required           base damage          required hands");
        System.out.println(ScannerUtil.splitLine);
        for (int i = 0; i < weapons.size(); ++i) {
            String name = (i + 1) + ". " + weapons.get(i).getName();
            int price = weapons.get(i).getCost();
            int level = weapons.get(i).getRequiredLevel();
            int damage = weapons.get(i).getBaseDamage();
            int hands = weapons.get(i).getRequiredHands();

            System.out.printf("%-22s %-14d %-14d %-17d %-5d", name, price, level, damage, hands);
            System.out.println();
        }
        System.out.println(ScannerUtil.splitLine);


        System.out.println("Armors List");
        System.out.println("name                      price         level required             damage reduced");
        System.out.println(ScannerUtil.splitLine);

        for (int i = 0; i < armors.size(); ++i) {
            String name = (i + 1) + weapons.size() + ". " + armors.get(i).getName();
            int price = armors.get(i).getCost();
            int level = armors.get(i).getRequiredLevel();
            int damageReduction = armors.get(i).getDamageReduction();

            System.out.printf("%-24s %-14d %-15d %-14d", name, price, level, damageReduction);
            System.out.println();
        }
        System.out.println(ScannerUtil.splitLine);

        System.out.println("Potions List");
        System.out.println("name                     price          level required            attribute gain           affect attribute");
        System.out.println(ScannerUtil.splitLine);

        for (int i = 0; i < potions.size(); ++i) {
            String name = (i + 1) + weapons.size() + armors.size() + ". " + potions.get(i).getName();
            int price = potions.get(i).getCost();
            int level = potions.get(i).getRequiredLevel();
            int increaseAttr = potions.get(i).getIncreasedAttr();
            String affectedAttr = potions.get(i).getAffectedAttr();

            System.out.printf("%-24s %-14d %-16d %-15d %-9s", name, price, level, increaseAttr, affectedAttr);
            System.out.println();
        }
        System.out.println(ScannerUtil.splitLine);

        System.out.println("Spells List");
        System.out.println("name                    price         level required          base damage        mana cost      type");
        System.out.println(ScannerUtil.splitLine);

        for (int i = 0; i < spells.size(); ++i) {
            String name = (i + 1) + weapons.size() + armors.size() + potions.size() + ". " + spells.get(i).getName();
            int price = spells.get(i).getCost();
            int level = spells.get(i).getRequiredLevel();
            int damage = spells.get(i).getBaseDamage();
            int manaCost = spells.get(i).getManaCost();
            String type;


            if (spells.get(i) instanceof IceSpell) {
                type = "Ice";
            } else if (spells.get(i) instanceof FireSpell) {
                type = "Fire";
            } else {
                type ="Lightning";
            }

            System.out.printf("%-24s %-14d %-13d %-14d %-9d %-16s", name, price, level, damage, manaCost, type);
            System.out.println();
        }
        System.out.println(ScannerUtil.splitLine);
    }
}
