package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.List;
import java.util.stream.Collectors;

public class ListCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Affiche la liste des leagues existante.\n" +
                "S'utilise ainsi: \"league list [archived/all]\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        List<Competition> listCompetitions;
        String text;
        if (cmdContext.args.length == 0) {
            listCompetitions = League.list.stream().filter(l -> !l.isArchived()).collect(Collectors.toList());
            text = "des leagues";
            displayList(listCompetitions, text);
        } else if (cmdContext.args.length == 1) {
            switch (cmdContext.args[0]) {
                case "archived" -> {
                    listCompetitions = League.list.stream().filter(l -> l.isArchived()).collect(Collectors.toList());
                    text = "des leagues archivé";
                    displayList(listCompetitions, text);
                }
                case "all" -> {
                    listCompetitions = League.list;
                    text = "de toute les leagues";
                    displayList(listCompetitions, text);
                }
                default -> System.out.printf("L'argument utilisé n'est pas une option existante: %s.\n", cmdContext.args[0]);
            }
        } else {
            System.out.println("Cette commande attend 0 à 1 argument.");
        }

    }

    @Override
    public String getCommandName() {
        return "list";
    }

    private void displayList(List<Competition> listCompetitions, String text) {
        if (!listCompetitions.isEmpty()) {
            System.out.printf("Liste %s existantes:\n" +
                    " - %s\n", text, listCompetitions.stream()
                    .map(l -> l.getName() + " - " + l.getEdition())
                    .collect(Collectors.joining("\n - "))
            );
        } else {
            System.out.println("Aucune league n'a été trouvé.");
        }
    }
}
