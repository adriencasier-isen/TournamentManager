package fr.isen.m1.tourament.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
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
        this._tag = tag.toUpperCase();
        this._country = country;
        this._sport = sport.toLowerCase();
        teamList.add(this);
    }

    public Team(Team copy) {
        Team.count++;
        id = Team.count;
        _name = copy._name;
        _tag = copy._tag;
        _country = copy._country;
        _sport = copy._sport;
        teamList.add(this);
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
