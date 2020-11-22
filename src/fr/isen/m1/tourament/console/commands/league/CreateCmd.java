package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class CreateCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Creer une competition de type league.\n" +
                "S'utilise ainsi: \"league create <nom:String> <edition:String> <sport:String> <zone_geo:String> " +
                "<nb_confrontations:Integer> <points_par_victoire:Integer> " +
                "<points_par_match_nul:Integer> <points_par_defaite:Integer>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie que la commande contient 8 arguments
        if (cmdContext.args.length == 8) {
            String[] args = cmdContext.args;
            // On vérifie qu'il n'existe pas de league avec le même nom et la même édition
            boolean existing = League.list.stream().anyMatch(l -> l.getName().equals(args[0]) && l.getEdition().equals(args[1]));
            if (!existing) {
                try {
                    // On créer une league avec les paramètres donné en argument
                    new League(args[0], Integer.parseInt(args[4]), args[1], args[3], args[2],
                            Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
                    System.out.printf("La league %s edition %s a ete cree avec succes.\n", args[0], args[1]);
                } catch (Exception e) {
                    System.out.printf("Une erreur est survenue lors de la creation de la league %s edition %s.\n", args[0], args[1]);
                }
            } else {
                System.out.println("Cette league existe déjà.");
            }
        } else {
            System.out.println("La commande entree demande exactement 8 arguments.");
        }
    }

    @Override
    public String getCommandName() {
        return "create";
    }
}
