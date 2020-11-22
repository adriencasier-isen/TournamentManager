package fr.isen.m1.tourament.models;

import java.io.Serializable;

public class Score implements Serializable {
    public final Team team;
    public int score = 0;
    public int overtimeScore = 0;

    public Score(Team team) {
        this.team = team;
    }

    public Score(Score copy) {
        team = copy.team;
        score = copy.score;
        overtimeScore = copy.overtimeScore;
    }
}
