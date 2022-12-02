/**
 * Class cell that contains the attributes of cell
 */
public class Cell {
    //left marker
    protected String leftMarker;
    //right marker
    protected String rightMarker;
    //cell type
    protected String cellType;
    //check if has monsters
    protected boolean hasMonsters;
    //check if has heroes
    protected boolean hasHeroes;
    //top and bottom bound
    private StringBuilder topAndBottom;
    //middle
    private StringBuilder middle;

    public Cell() {
    }

    public Cell(String leftMarker, String rightMarker, String cellType) {
        this.leftMarker = leftMarker;
        this.rightMarker = rightMarker;
        this.cellType = cellType;

        hasMonsters = false;
        hasHeroes = false;

        this.topAndBottom = new StringBuilder();

        this.setTopAndBottom();
        this.setMiddle();
    }

    public StringBuilder getTopAndBottom() {
        return topAndBottom;
    }

    public StringBuilder getMiddle() {
        return middle;
    }

    public void setTopAndBottom() {
        topAndBottom.append(cellType).append(" ").append("-").append(" ").append(cellType).append(" ").
                append("-").append(" ").append(cellType);
    }

    public void setMiddle() {
        this.middle = new StringBuilder();
        middle.append("| ").append(leftMarker).append(" ").append(rightMarker).append(" |");
    }

    public void setHasMonsters(boolean hasMonsters) {
        this.hasMonsters = hasMonsters;
    }

    public void setHasHeroes(boolean hasHeroes) {
        this.hasHeroes = hasHeroes;
    }

    public String getLeftMarker() {
        return leftMarker;
    }

    public void setLeftMarker(String leftMarker) {
        this.leftMarker = leftMarker;
    }

    public String getRightMarker() {
        return rightMarker;
    }

    public void setRightMarker(String rightMarker) {
        this.rightMarker = rightMarker;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public boolean isHasMonsters() {
        return hasMonsters;
    }

    public boolean isHasHeroes() {
        return hasHeroes;
    }

    public void setTopAndBottom(StringBuilder topAndBottom) {
        this.topAndBottom = topAndBottom;
    }

    public void setMiddle(StringBuilder middle) {
        this.middle = middle;
    }
}
