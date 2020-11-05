package fr.isen.m1.tourament.models;

import fr.isen.m1.tourament.enums.EStageType;

public class Match {
    private final String _sport;
    private final EStageType _stage;
    private String _winner = null;
    private final Score[] _scores = new Score[2];
    private boolean _isPostponed = false;
    private boolean _isEnded = false;

    public Match(String sport, EStageType stageType) {
        _sport = sport;
        _stage = stageType;
    }

    public String get_sport() {
        return _sport;
    }

    public boolean get_isPostponed() {
        return _isPostponed;
    }

    public boolean get_isEnded() {
        return _isEnded;
    }

    public EStageType get_stage() {
        return _stage;
    }

    public String get_winner() {
        return _winner;
    }

    public void set_winner(String _winner) {
        this._winner = _winner;
    }

    public void setScores(int scoreHome, int scoreExt) {
        _scores[0].score = scoreHome;
        _scores[1].score = scoreExt;
    }

    public void setOvertimeScore(int scoreHome, int scoreExt) {
        _scores[0].overtimeScore = scoreHome;
        _scores[1].overtimeScore = scoreExt;
    }

    public Score[] get_scores() {
        return _scores;
    }
}
