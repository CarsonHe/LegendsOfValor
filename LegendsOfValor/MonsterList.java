import java.util.ArrayList;

/**
 * all the monsters in the game
 * @author
 */
public class MonsterList {
    private final ArrayList<Monster> monsters;

    public MonsterList() {
        monsters = new ArrayList<>();

        monsters.add(new Dragon("Desghidorrah", 3, 300, 400, 35));
        monsters.add(new Dragon("Chrysophylax", 2, 200, 500, 20));
        monsters.add(new Dragon("BunsenBurner", 4, 400, 500, 45));
        monsters.add(new Dragon("Natsunomeryu", 1, 100, 200, 10));
        monsters.add(new Dragon("TheScaleless", 7, 700, 600, 75));
        monsters.add(new Dragon("Kas-Ethelinh", 5, 600, 500, 60));
        monsters.add(new Dragon("Alexstraszan", 10, 1000, 9000, 55));
        monsters.add(new Dragon("Phaarthurnax", 6, 600, 700, 60));
        monsters.add(new Dragon("D-Maleficent", 9, 900, 950, 85));
        monsters.add(new Dragon("TheWeatherbe", 8, 800, 900, 80));
        monsters.add(new Dragon("Igneel", 6, 600, 400, 60));
        monsters.add(new Dragon("BlueEyesWhite", 9, 900, 600, 75));

        monsters.add(new Exoskeleton("Cyrrollalee", 7, 700, 800, 75));
        monsters.add(new Exoskeleton("Brandobaris", 3, 350, 450, 30));
        monsters.add(new Exoskeleton("BigBad-Wolf", 1, 150, 250, 15));
        monsters.add(new Exoskeleton("WickedWitch", 2, 250, 350, 25));
        monsters.add(new Exoskeleton("Aasterinian", 4, 400, 500, 45));
        monsters.add(new Exoskeleton("Chronepsish", 6, 650, 750, 60));
        monsters.add(new Exoskeleton("Kiaransalee", 8, 850, 950, 85));
        monsters.add(new Exoskeleton("St-Shargaas", 5, 550, 650, 55));
        monsters.add(new Exoskeleton("Merrshaullk", 10, 1000, 900, 55));
        monsters.add(new Exoskeleton("St-Yeenoghu", 9, 950, 850, 90));
        monsters.add(new Exoskeleton("DocOck", 6, 600, 600, 55));
        monsters.add(new Exoskeleton("Exodia", 10, 1000, 1000, 50));

        monsters.add(new Spirit("Andrealphus", 2, 600, 500, 40));
        monsters.add(new Spirit("Blinky", 1, 450, 350, 35));
        monsters.add(new Spirit("Andromalius", 3, 550, 450, 25));
        monsters.add(new Spirit("Chiang-shih", 4, 700, 600, 40));
        monsters.add(new Spirit("FallenAngel", 5, 800, 700, 50));
        monsters.add(new Spirit("Ereshkigall", 6, 950, 450, 35));
        monsters.add(new Spirit("Melchiresas", 7, 350, 150, 75));
        monsters.add(new Spirit("Jormunngand", 8, 600, 900, 20));
        monsters.add(new Spirit("Rakkshasass", 9, 550, 600, 35));
        monsters.add(new Spirit("Taltecuhtli", 10, 300, 200, 50));
        monsters.add(new Spirit("Casper", 1, 100, 100, 50));
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
