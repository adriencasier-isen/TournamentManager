package fr.isen.m1.tourament.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {
    private final UUID id;
    private String _name;
    private String _tag;
    private String _country;
    private String _sport;

    public static List<Team> teamList = new ArrayList<>();

    public Team(String name, String tag, String country, String sport) {
        id = UUID.randomUUID();
        this._name = name;
        this._tag = tag;
        this._country = country;
        this._sport = sport;
    }

    public Team(UUID id, String name, String tag, String country, String sport) {
        this.id = id;
        this._name = name;
        this._tag = tag;
        this._country = country;
        this._sport = sport;
    }

    public Team(Team team) {
        id = team.id;
        _name = team._name;
        _tag = team._tag;
        _country = team._country;
        _sport = team._sport;
    }

    public UUID getId() {
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
