package fr.isen.m1.tourament.console.commands.match;

import java.util.Optional;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

public class CreatMatchCmd implements ICommand{
    @Override
    public String getHelpMessage() {
        return "Nouveau Match";
    }
    @Override
    public void run(CommandContext cmdContext){
        String[] args = cmdContext.args;
        String [][] caracMatch;
        Optional<Team> teams = Team.teamList.stream()
        .filter(t -> t.get_tag().equals(args[0])).findFirst();
        .map(t ->{
            String :: t.get_tag();
            caracMatch[0][0]= t.get_tag();
            return caracMatch;
            }).findFirst();

    }
}
