import java.util.Random;

/**
 * map
 */
public class ValorMap {
    private final int size;
    private final Cell[][] map;
    private final int[] maxExploredLevels = new int[3];

    public ValorMap(int size) {
        this.size = size;
        this.map = new Cell[size][size];

        setMaxExploredLevels();
        mapInitialization();
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getMap() {
        return map;
    }

    public int[] getMaxExploredLevels() {
        return maxExploredLevels;
    }

    public void setMaxExploredLevels() {
        for (int i = 0; i < 3; ++i) {
            maxExploredLevels[i] = size - 1;
        }
    }


    public void updateExploredLevel(int lane) {
        if (lane < 0 || lane >= maxExploredLevels.length) {
            return;
        }
        --maxExploredLevels[lane];
    }

    /**
     * initialize map
     */
    private void mapInitialization() {
        Random random = new Random();
        int heroIndex = 1, monsterIndex = 1;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (3 * j + 2 < size) {
                    map[i][3 * j + 2] = new InaccessibleCell();
                }

                String heroMarker = "H" + heroIndex;
                String monsterMarker = "M" + monsterIndex;
                if (i == 0 && map[i][j] == null) {
                    if (j % 3 == 0) {
                        map[i][j] = new MonsterNexusCell("  ", monsterMarker);
                        map[i][j].setHasMonsters(true);
                        ++monsterIndex;
                    } else {
                        map[i][j] = new MonsterNexusCell("  ", "  ");
                    }
                }

                if (i == size - 1 && map[i][j] == null) {
                    if (j % 3 == 0) {
                        map[i][j] = new HeroNexusCell(heroMarker, "  ");
                        map[i][j].setHasHeroes(true);
                        ++heroIndex;
                    } else {
                        map[i][j] = new HeroNexusCell("  ", "  ");
                    }
                }

                int temp = random.nextInt(10) + 1;

                if (map[i][j] == null) {
                    if (1 <= temp && temp <= 4) {
                        map[i][j] = new PlainCell("  ", "  ");
                    } else if (5 <= temp && temp <= 6) {
                        map[i][j] = new BushCell("  ", "  ");
                    } else if (7 <= temp && temp <= 8) {
                        map[i][j] = new CaveCell("  ", "  ");
                    } else if (9 <= temp && temp <= 10) {
                        map[i][j] = new KoulouCell("  ", "  ");
                    }
                }
            }
        }
    }

    /**
     * print map
     */
    public void printMap() {
        System.out.println("                                                  map information");
        System.out.println(" cell information ：");
        System.out.println("N-Nexus: home of heroes and monsters" );
        System.out.println("I-Impassible: cell that is inaccessible  ");
        System.out.println("C-Cave: increase 10% of agility of any hero in it");
        System.out.println("B-Bush: increase 10% of dexterity of any hero in it");
        System.out.println("K-Koulou: increase 10% of strength of any hero in it");
        System.out.println();
        StringBuilder sb = new StringBuilder(" Col  ");
        for (int i = 0; i < map.length; ++i) {
            sb.append("     ").append(i).append("      ");
        }
        System.out.println(sb);
        for (int i = 0; i < map.length; ++i) {
            for (int j = 1; j <= 3; ++j) {

                if (j == 2) {
                    System.out.print("  " + i + "    ");
                } else {
                    if (i == 0 && j == 1) {
                        System.out.print(" Row   ");
                    }else {
                        System.out.print("       ");
                    }

                }
                Cell[] cells = map[i];
                for (Cell cell : cells) {
                    if (j != 2) {
                        System.out.print(cell.getTopAndBottom() + "   ");
                    } else {
                        System.out.print(cell.getMiddle() + "   ");
                    }
                }

                System.out.println();
            }

            System.out.println();
        }
    }
}
