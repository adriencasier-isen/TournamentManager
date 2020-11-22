package fr.isen.m1.tourament.console.commands.team;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

import java.util.stream.Collectors;

public class ListCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Affiche la liste des equipes enregistré dans la base de données.\n" +
                "S'utilise ainsi: \"team list\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie qu'il n'y a pas d'argument
        if (cmdContext.args.length == 0) {
            // On vérifique qu'il existe des équipes
            if (!Team.teamList.isEmpty()) {
                // On affique les équipes
                System.out.printf("Liste des equipes:\n" +
                        " - %s\n", Team.teamList.stream()
                        .map(t -> t.get_name() + " - " + t.get_tag() + " - " + t.get_sport())
                        .collect(Collectors.joining("\n - ")));
            } else {
                System.out.println("Aucune équipes n'ont été enregistré dans la base de données.");
            }
        } else {
            System.out.println("Cette commande attend exactement 0 arguments.");
        }
    }

    @Override
    public String getCommandName() {
        return "list";
    }
}
