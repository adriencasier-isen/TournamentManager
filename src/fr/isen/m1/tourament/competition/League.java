package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;
import fr.isen.m1.tourament.models.Match;
import fr.isen.m1.tourament.models.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class League extends Competition {
    private final int winReward;
    private final int drawReward;
    private final int loseReward;
    public List<LeaderboardEntry> leaderboard = new ArrayList<>();

    public League(String name, int confrontationCount, String edition, String location, String sport, int winReward, int drawReward, int loseReward) {
        super(name, confrontationCount, EStageType.LEAGUE, edition, location, sport.toLowerCase());
        this.winReward = winReward;
        this.drawReward = drawReward;
        this.loseReward = loseReward;
        League.list.add(this);
    }

    public League(League copy) {
        super(copy);
        this.winReward = copy.winReward;
        this.drawReward = copy.drawReward;
        this.loseReward = copy.loseReward;
        League.list.add(this);
    }

    public void computeLeaderboard() {
        leaderboard.clear();
        for (Team t : Team.teamList) {
            int score = 0;
            int matchPlayed = 0;
            for (Match m : matchList) {
                if (m.get_isEnded()) {
                    if (m.get_scores()[0].team.equals(t) || m.get_scores()[1].team.equals(t)) {
                        if (m.get_winner() == null) score += drawReward;
                        else if (m.get_winner().equals(t)) score += winReward;
                        else score += loseReward;
                        matchPlayed++;
                    }
                }
            }
            leaderboard.add(new LeaderboardEntry(t, score, matchPlayed));
        }
        leaderboard.sort((o1, o2) -> Integer.compare(o2.score, o1.score));
    }

    @Override
    public void generateMatchList() {
        List<Match> tmpMatchlist = new ArrayList<>();
        matchList.clear();
        teamlist.forEach(tA -> {
            List<Team> opponents = teamlist.stream()
                    .filter(tB -> tA != tB && tmpMatchlist.stream()
                            .noneMatch(m -> (m.get_scores()[0].team.equals(tA) && m.get_scores()[1].team.equals(tB))
                                    || (m.get_scores()[0].team.equals(tB) && m.get_scores()[1].team.equals(tA))
                            )
                    ).collect(Collectors.toList());
            opponents.forEach(tB -> tmpMatchlist.add(
                    new Match(getSport(), getStageType(), tA, tB)
                    )
            );
        });
        List<Match> generatedMatchlist = new ArrayList<>(new ArrayList<>(tmpMatchlist));
        for (int i = 1; i < getConfrontationCount(); i++) {
            int finalI = i;
            generatedMatchlist.addAll(new ArrayList<>(tmpMatchlist.stream().map(m -> {
                Match match = new Match(m);
                if (finalI % 2 != 0) match.invertTeam();
                return match;
            }).collect(Collectors.toList())));
        }
        addMatchs(generatedMatchlist);
    }

    public static class LeaderboardEntry implements Serializable {
        public Team team;
        public int score;
        public int matchPlayed;

        public LeaderboardEntry(Team team, int score, int matchPlayed) {
            this.team = team;
            this.score = score;
            this.matchPlayed = matchPlayed;
        }
    }
}
