package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;

public class Tournament extends Competition {
    public Tournament(String name, int confrontationCount, String edition, String location) {
        super(name, confrontationCount, EStageType.ELIMINATION, edition, location);
        League.list.add(this);
    }
}
