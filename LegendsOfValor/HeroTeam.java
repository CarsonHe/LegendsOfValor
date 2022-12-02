import java.util.ArrayList;

/**
 * hero team class
 */
public class HeroTeam {
    private final ArrayList<Hero> team;

    public HeroTeam() {
        team = new ArrayList<>();
    }

    public ArrayList<Hero> getTeam() {
        return team;
    }

    public void addMembers(Hero h) {
        // 最大团队规模为3人
        if (team.size() == 3) { return; }
        team.add(h);
    }
}
