package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.Optional;

public class ArchiveCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Permet d'archiver une league. Attention, cette action est irreversible.\n" +
                "L'archivage permet d'exclure la league des filtres par defaut.\n" +
                "S'utilise ainsi: \"league archive <nom:String> <edition:String>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie que la commande contient 2 arguments
        if (cmdContext.args.length == 2) {
            String[] args = cmdContext.args;
            //On recherche la compétition avec les arguments comme critère de recherche
            Optional<Competition> competition = League.list.stream().filter(l -> l.getName().equals(args[0]) && l.getEdition().equals(args[1]))
                    .findFirst();
            if (competition.isPresent()) {
                //Si la compétition a été trouvé
                Competition league = competition.get();
                if (!league.isArchived()) {
                    //On archive la league si elle ne l'est pas
                    league.archive();
                    System.out.printf("La league %s édition %s est désormais archivé.\n", args[0], args[1]);
                } else
                    System.out.printf("La league %s édition %s est déjà archivé.\n", args[0], args[1]);
            } else {
                System.out.println("Aucune league n'a été trouvé.");
            }
        } else if (Main.selectedCompetition != null && Main.selectedCompetition.getClass().equals(League.class)) {
            // Si aucun arguments ne sont donné MAIS que l'utilisateur a sélectionné une compétition au préalable
            // Alors on archive la compétition sélectionné si elle ne l'est pas
            if (!Main.selectedCompetition.isArchived()) {
                Main.selectedCompetition.archive();
                System.out.printf("La league %s édition %s a été archivé.\n", Main.selectedCompetition.getName(), Main.selectedCompetition.getEdition());
            } else {
                System.out.printf("La league %s édition %s est déjà archivé.\n", Main.selectedCompetition.getName(), Main.selectedCompetition.getEdition());
            }
        } else {
            System.out.println("Cette commande attend exactement 2 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "archive";
    }
}
