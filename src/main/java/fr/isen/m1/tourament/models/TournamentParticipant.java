package main.java.fr.isen.m1.tourament.models;

import main.java.fr.isen.m1.tourament.enums.EStageType;

public class TournamentParticipant {
    private final Team _team;
    private boolean _eliminated = false;
    private final EStageType _stage;

    public TournamentParticipant(Team team, EStageType stage) {
        _team = team;
        _stage = stage;
    }

    public Team get_team() {
        return _team;
    }

    public boolean isEliminated() {
        return _eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this._eliminated = eliminated;
    }

    public EStageType get_stage() {
        return _stage;
    }
}
