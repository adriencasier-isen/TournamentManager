package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

import java.util.Optional;

import fr.isen.m1.tourament.Main;

public class RegisterTeamCmd implements ICommand {
    @Override
    public String getHelpMessage(){
        return("Permet d'ajouter une équipe à une league");
    }
    @Override
    public void run(CommandContext cmdContext){
        if (cmdContext.args.length == 3) {
            String[] args = cmdContext.args;
            Optional<Team> team = Team.teamList.stream()
                    .filter(t -> t.get_name().equals(args[0])
                            && t.get_tag().equals(args[1])
                            && t.get_sport().equals("Football"))
                    .findFirst();
                    if (team.isPresent()){
                        Main.selectedCompetition.teamlist.add(team.get());
                    }
                    else {
                        System.out.println("Aucune équipe n'a été trouvé.");
                    }
                } 
                else {
                    System.out.println("Cette commande attend exactement 3 arguments");
                }
    }
    @Override
    public String getCommandName(){
        return("Equipe ajouté");
    }
}
