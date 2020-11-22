package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class GenerateMatchListCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Permet de générer la liste des matchs de la league selectionné.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (Main.selectedCompetition.teamlist.size() > 1) {
            Main.selectedCompetition.generateMatchList();
        } else {
            System.out.println("Il n'y a pas assez d'équipes inscrite dans cette league pour générer la liste des matchs.");
        }
    }

    @Override
    public String getCommandName() {
        return "generate";
    }
}
