package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.stream.Collectors;

public class ListCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Affiche la liste des leagues existante.\n" +
                "S'utilise ainsi: \"league list\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (League.list.size() != 0) {
            System.out.printf("Liste des leagues existantes:\n" +
                    " - %s\n", League.list.stream()
                    .map(l -> l.getName() + " - " + l.getEdition())
                    .collect(Collectors.joining("\n - "))
            );
        } else {
            System.out.println("Aucune league n'a ete cree.");
        }
    }

    @Override
    public String getCommandName() {
        return "list";
    }
}
