package fr.isen.m1.tourament.models;

import fr.isen.m1.tourament.enums.EStageType;

public class Match {
    private final String _sport;
    private final Long _id;
    private final EStageType _stage;
    private String _winner = null;
    private final Score[] _scores = new Score[2];
    private boolean _isPostponed = false;
    private boolean _isEnded = false;
    private static Long count = 0L;

    public Match(String sport, EStageType stageType) {
        Match.count++;
        _sport = sport;
        this._id = Match.count;
        _stage = stageType;
    }

    public String get_sport() {
        return _sport;
    }

    public boolean get_isPostponed() {
        return _isPostponed;
    }

    public void set_isPostponed(boolean value) {
        _isPostponed = value;
    }

    public boolean get_isEnded() {
        return _isEnded;
    }

    public void end() {
        _isEnded = true;
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

    public Long get_id() {
        return _id;
    }

    public Long getCount() {
        return Match.count;
    }

    public void setCount(Long value) {
        Match.count = value;
    }
}
