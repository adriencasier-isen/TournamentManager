package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;
import fr.isen.m1.tourament.models.Match;
import fr.isen.m1.tourament.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class League extends Competition {
    public League(String name, int confrontationCount, String edition, String location, String sport) {
        super(name, confrontationCount, EStageType.LEAGUE, edition, location, sport.toLowerCase());
        League.list.add(this);
    }

    public League(League copy) {
        super(copy);
        League.list.add(this);
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
}
