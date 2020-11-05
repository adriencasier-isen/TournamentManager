package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;

public class League extends Competition {
    public League(String name, int confrontationCount, EStageType stageType, String edition, String location) {
        super(name, confrontationCount, stageType, edition, location);
        list.add(this);
    }

    public void Finalize() {
        list.remove(this);
    }
}
