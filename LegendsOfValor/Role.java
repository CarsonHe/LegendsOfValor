/**
 * class role
 */
public class Role {
    private String name;
    private int level;
    //row
    private int row;
    //col
    private int col;
    private double hp;

    public Role() {}

    public Role(String name, int level) {
        this.setName(name);
        this.setLevel(level);
        setRow(0);
        setCol(0);
        this.setHp(100.0 * level);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setHp() {
        this.setHp(100.0 * this.getLevel());
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getHp() {
        return hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
}
