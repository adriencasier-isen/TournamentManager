package fr.isen.m1.tourament.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private static Long count = 0L;
    private String _name;
    private String _tag;
    private final Long id;
    private final String _country;
    private final String _sport;

    public static List<Team> teamList = new ArrayList<>();

    public Team(String name, String tag, String country, String sport) {
        Team.count++;
        id = Team.count;
        this._name = name;
        this._tag = tag;
        this._country = country;
        this._sport = sport;
        teamList.add(this);
    }

    public Team(Team team) {
        id = team.id;
        _name = team._name;
        _tag = team._tag;
        _country = team._country;
        _sport = team._sport;
    }

    public Long getId() {
        return id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_tag() {
        return _tag;
    }

    public void set_tag(String _tag) {
        this._tag = _tag;
    }

    public String get_country() {
        return _country;
    }

    public String get_sport() {
        return _sport;
    }
}
