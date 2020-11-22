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
        //cmdContext.args
    }
    @Override
    public String getCommandName(){
        return("Voici le classement");
    }
}
