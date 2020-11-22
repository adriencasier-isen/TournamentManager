package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

public class LeaderboardCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return ("Permet d'afficher le tableau du classement des équipes par score et par matchs joué.");
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie que l'utilisateur a bien sélectionné une competition de type league
        if (Main.selectedCompetition != null) {
            if (Main.selectedCompetition.getClass().equals(League.class)) {
                String display = "";
                int ladder = 1;
                // On calcul les points des équipes en fonction de leurs résultats de leur matchs fini
                ((League) Main.selectedCompetition).computeLeaderboard();
                for (League.LeaderboardEntry entry : ((League) Main.selectedCompetition).leaderboard) {
                    Team t = entry.team;
                    int s = entry.score;
                    int mp = entry.matchPlayed;
                    // Concaténation de chaque lignes du tableau d'affichage du classement
                    display = display.concat("#" + ladder + "\t" + t.get_name() + " [" + t.get_tag() + "] (" + mp + ") - " + s + "\n");
                    ladder++;
                }
                // Affichage du classement
                String competitionText = Main.selectedCompetition.getName()
                        + " édition " + Main.selectedCompetition.getEdition();
                System.out.printf("Leaderbord %s :\n%s\n", competitionText, display);
            } else {
                System.out.println("La compétition selectionné ne correspond pas au type LEAGUE.");
            }
        } else {
            System.out.println("Vous n'avez pas selectionné de compétition.");
        }
    }

    @Override
    public String getCommandName() {
        return ("leaderboard");
    }
}
