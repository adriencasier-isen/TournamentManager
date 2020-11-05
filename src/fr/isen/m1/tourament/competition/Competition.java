package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.models.Team;
import fr.isen.m1.tourament.enums.EStageType;
import fr.isen.m1.tourament.models.Match;

import java.util.ArrayList;
import java.util.List;

public class Competition {
    public static List<Competition> list = new ArrayList<>();

    private final String name;
    private final int confrontationCount;
    private final EStageType stageType;
    private final String edition;
    private final String location;
    private final Long id;
    private static Long count = 0L;

    public List<Team> teamlist = new ArrayList<>();
    public List<Match> matchList = new ArrayList<>();

    public Competition(String name, int confrontationCount, EStageType stageType, String edition, String location) {
        Competition.count ++;
        this.id = Competition.count;
        this.name = name;
        this.confrontationCount = confrontationCount;
        this.stageType = stageType;
        this.edition = edition;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getConfrontationCount() {
        return confrontationCount;
    }

    public String getEdition() {
        return edition;
    }

    public String getLocation() {
        return location;
    }

    public EStageType getStageType() {
        return stageType;
    }

    public Long getId() {
        return id;
    }

    public Long getCount() {
        return Competition.count;
    }

    public void setCount(Long value) {
        Competition.count = value;
    }
}
