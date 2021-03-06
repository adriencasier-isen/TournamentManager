package fr.isen.m1.tourament.competition;

import fr.isen.m1.tourament.enums.EStageType;
import fr.isen.m1.tourament.models.Match;
import fr.isen.m1.tourament.models.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Competition implements java.io.Serializable {
    public static List<Competition> list = new ArrayList<>();

    private final String name;
    private final int confrontationCount;
    private final EStageType stageType;
    private final String edition;
    private final String location;
    private final Long id;
    private final String sport;
    private boolean archived = false;
    private static Long count = 0L;

    public List<Team> teamlist = new ArrayList<>();
    public List<Match> matchList = new ArrayList<>();

    public Competition(String name, int confrontationCount, EStageType stageType, String edition, String location, String sport) {
        Competition.count++;
        this.id = Competition.count;
        this.name = name;
        this.confrontationCount = confrontationCount;
        this.stageType = stageType;
        this.edition = edition;
        this.location = location;
        this.sport = sport;
    }

    public Competition(Competition copy) {
        Competition.count++;
        this.id = Competition.count;
        this.name = copy.name;
        this.confrontationCount = copy.confrontationCount;
        this.stageType = copy.stageType;
        this.edition = copy.edition;
        this.location = copy.location;
        this.archived = copy.archived;
        this.teamlist = copy.teamlist;
        this.matchList = copy.matchList;
        this.sport = copy.sport;
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

    public void addTeam(Team team) {
        teamlist.add(team);
    }

    public void addTeams(Collection<? extends Team> teams) {
        teamlist.addAll(teams);
    }

    public Team getTeam(Long id) {
        return teamlist.stream()
                .filter(team -> team.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addMatchs(Collection<? extends Match> matchs) {
        matchList.addAll(matchs);
    }

    public void addMatch(Match match) {
        matchList.add(match);
    }

    public void addNewMatch(Team teamA, Team teamB) {
        matchList.add(new Match(getSport(), stageType, teamA, teamB));
    }

    public Match getMatch(Long id) {
        return matchList.stream()
                .filter(match -> match.get_id().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * generateMatchList(): Génère la liste des matchs de la compétition
     */
    public void generateMatchList() {
        // Doit être @Override chez les enfants
    }

    public boolean isArchived() {
        return archived;
    }

    public void archive() {
        archived = true;
    }

    public String getSport() {
        return sport;
    }
}
