package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;

public class League extends Competition {
    public League(String name, int confrontationCount, String edition, String location, String sport) {
        super(name, confrontationCount, EStageType.LEAGUE, edition, location, sport.toLowerCase());
        League.list.add(this);
    }

    public League(League copy) {
        super(copy);
        League.list.add(this);
    }
}
