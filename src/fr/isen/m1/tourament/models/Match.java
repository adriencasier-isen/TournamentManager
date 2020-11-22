package fr.isen.m1.tourament.models;

import fr.isen.m1.tourament.enums.EStageType;

import java.io.Serializable;

public class Match implements Serializable {
    private final String _sport;
    private final Long _id;
    private final EStageType _stage;
    private Team _winner = null;
    private final Score[] _scores = new Score[2];
    private boolean _isPostponed = false;
    private boolean _isEnded = false;
    private static Long count = 0L;

    public Match(String sport, EStageType stageType, Team teamHome, Team teamExt) {
        Match.count++;
        _sport = sport;
        this._id = Match.count;
        _stage = stageType;
        _scores[0] = new Score(teamHome);
        _scores[1] = new Score(teamExt);
    }

    public Match(Match copy) {
        Match.count++;
        _sport = copy._sport;
        this._id = Match.count;
        _stage = copy._stage;
        _scores[0] = new Score(copy._scores[0].team);
        _scores[1] = new Score(copy._scores[1].team);
        _isEnded = copy._isEnded;
        _isPostponed = copy._isPostponed;
        _winner = copy._winner;
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
        computeWinner();
    }

    public EStageType get_stage() {
        return _stage;
    }

    public Team get_winner() {
        return _winner;
    }

    public void computeWinner() {
        // Ici, on determine qui est le gagnant du match en effectuant de multiple comparaison
        if (_scores[0].score < _scores[1].score) set_winner(1);
        else if (_scores[0].score > _scores[1].score) set_winner(0);
        else if (_scores[0].overtimeScore < _scores[1].overtimeScore) set_winner(1);
        else if (_scores[0].overtimeScore > _scores[1].overtimeScore) set_winner(0);
        else _winner = null;
    }

    public void set_winner(int index) {
        this._winner = _scores[index].team;
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

    public String get_match_score() {
        // Ici on prépare l'affichage du score d'un match
        String score_team_A = String.format("%s %d(%d)", _scores[0].team.get_name(), _scores[0].score, _scores[0].overtimeScore);
        String score_team_B = String.format("(%d)%s %s", _scores[1].overtimeScore, _scores[1].score, _scores[1].team.get_name());
        return String.format("%s - %s", score_team_A, score_team_B);
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

    public void invertTeam() {
        // Ici on inverse les équipe "Domicile" et "Visiteur"
        Score tmp = new Score(this._scores[0]);
        this._scores[0] = new Score(this._scores[1]);
        this._scores[1] = new Score(tmp);
    }
}
