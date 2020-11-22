package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;

public class Tournament extends Competition {
    public Tournament(String name, int confrontationCount, String edition, String location, String sport) {
        super(name, confrontationCount, EStageType.ELIMINATION, edition, location, sport);
        League.list.add(this);
    }

    @Override
    public void generateMatchList() {

    }
}
