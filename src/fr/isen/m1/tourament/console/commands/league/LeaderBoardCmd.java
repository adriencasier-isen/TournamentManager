package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class LeaderBoardCmd implements ICommand{
    @Override
    public String getHelpMessage(){
        return("Permet d'ajouter un tableau avec les scores des équipes et leur classement ");
    }
    @Override
    public void run(CommandContext cmdContext){
        //TODO: Traiter les données des matches et générer le leaderboard
        // Systeme de pagination ?
    }
    @Override
    public String getCommandName(){
        return("Voici le classement");
    }
}
