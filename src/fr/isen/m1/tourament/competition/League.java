package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;

public class League extends Competition {
    public League(String name, int confrontationCount, String edition, String location) {
        super(name, confrontationCount, EStageType.LEAGUE, edition, location);
        League.list.add(this);
    }

    public League(League copy) {
        super(copy);
        League.list.add(this);
    }
}
