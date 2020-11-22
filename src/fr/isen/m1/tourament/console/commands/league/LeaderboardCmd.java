package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class LeaderboardCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return ("Permet d'afficher le tableau du classement des équipes par score et par matchs joué.");
    }

    @Override
    public void run(CommandContext cmdContext) {
        //TODO: Traiter les données des matches et générer le leaderboard
        // Systeme de pagination ?

    }
    @Override
    public String getCommandName(){
        return ("leaderboard");
    }
}
