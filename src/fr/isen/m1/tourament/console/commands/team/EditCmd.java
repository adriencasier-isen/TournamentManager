package fr.isen.m1.tourament.console.commands.team;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

import java.util.Optional;

public class EditCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Permet d'éditer les informations d'une équipe.\n" +
                "S'utilise ainsi: \"team edit <tag:String> <sport:String> <tag/name> <valeur:String>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie qu'il y a bien 4 arguments
        if (cmdContext.args.length == 4) {
            String[] args = cmdContext.args;
            // On cherche l'équipe à éditer
            Optional<Team> team = Team.teamList.stream()
                    .filter(t -> t.get_tag().equals(args[0].toUpperCase())
                            && t.get_sport().equals(args[1].toLowerCase()))
                    .findFirst();
            if (team.isPresent()) {
                // Si trouvé, on edite une valeur en fonction du 3ème argument
                switch (args[2].toLowerCase()) {
                    case "tag" -> team.get().set_tag(args[3].toUpperCase());
                    case "name" -> team.get().set_name(args[3]);
                    default -> System.out.printf("L'argument %s n'a pas été reconnu.\n", args[2]);
                }
                System.out.printf("L'équipe [%s] a été modifié.\n", args[0]);
            } else {
                System.out.println("Aucune équipe n'a été trouvé.");
            }
        } else {
            System.out.println("Cette commande attend exactement 4 arguments");
        }
    }

    @Override
    public String getCommandName() {
        return "edit";
    }
}
