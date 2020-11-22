package fr.isen.m1.tourament.console.commands.team;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

public class AddCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Permet d'ajouter une équipe à la base de données.\n" +
                "S'utilise ainsi: \"team add <nom:String> <tag:String> <pays:String> <sport:String>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 4) {
            String[] args = cmdContext.args;
            boolean alreadyExist = Team.teamList.stream()
                    .anyMatch(t -> t.get_name().equals(args[0]) && t.get_tag().equals(args[1].toUpperCase())
                            && t.get_sport().equals(args[3]));
            if (!alreadyExist) {
                new Team(args[0], args[1], args[2], args[3]);
                System.out.printf("L'équipe %s [%s] a été crée.\n", args[0], args[1]);
            } else {
                System.out.println("Cette équipe existe déjà.");
            }
        } else {
            System.out.println("Cette commande attend exactement 4 arguments.");
        }
    }

    @Override
    public String getCommandName() {
        return "add";
    }
}
