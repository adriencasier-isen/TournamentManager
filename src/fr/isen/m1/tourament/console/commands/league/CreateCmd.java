package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class CreateCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Creer une competition de type league.\n" +
                "S'utilise ainsi: \"league create <nom:String> <edition:String> <zone_geo:String> <nb_confrontations:Integer>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 4) {
            String[] args = cmdContext.args;
            boolean existing = League.list.stream().anyMatch(l -> l.getName().equals(args[0]) && l.getEdition().equals(args[1]));
            if (!existing) {
                try {
                    new League(args[0], Integer.parseInt(args[3]), args[1], args[2]);
                    System.out.printf("La league %s edition %s a ete cree avec succes.\n", args[0], args[1]);
                } catch (Exception e) {
                    System.out.printf("Une erreur est survenue lors de la creation de la league %s edition %s.\n", args[0], args[1]);
                }
            } else {
                System.out.println("Cette league existe déjà.");
            }
        } else {
            System.out.println("La commande entree demande exactement 4 arguments.");
        }
    }

    @Override
    public String getCommandName() {
        return "create";
    }
}
