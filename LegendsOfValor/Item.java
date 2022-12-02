/**
 * Item class implements TradeInterface to make sure item can be brought or sold
 */
public class Item implements TradeInterface {
    protected String name;
    protected int cost;
    protected int requiredLevel;

    public Item() {}

    public Item(String name, int cost, int requiredLevel) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }


    /**
     * check if the item is affordable
     * @param hero
     * @return
     */
    @Override
    public boolean isAffordable(Hero hero) {
        return hero.getLevel() >= requiredLevel && hero.getMoney() >= cost;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
}
