package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class GenerateMatchListCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Permet de générer la liste des matchs de la league selectionné.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (Main.selectedCompetition != null) {
            if (Main.selectedCompetition.getClass().equals(League.class)) {
                if (Main.selectedCompetition.teamlist.size() > 1) {
                    Main.selectedCompetition.generateMatchList();
                    System.out.printf("Tout les matchs ont été généré avec %s confrontation(s) par rencontre.\n", Main.selectedCompetition.getConfrontationCount());
                } else {
                    System.out.println("Il n'y a pas assez d'équipes inscrite dans cette league pour générer la liste des matchs.");
                }
            } else {
                System.out.println("La compétition selectionné ne correspond pas au type LEAGUE.");
            }
        } else {
            System.out.println("Vous n'avez pas selectionné de compétition.");
        }
    }

    @Override
    public String getCommandName() {
        return "generate";
    }
}
