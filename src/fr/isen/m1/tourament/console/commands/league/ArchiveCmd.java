package fr.isen.m1.tourament.console.commands.league;

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
        if (cmdContext.args.length == 2) {
            String[] args = cmdContext.args;
            Optional<Competition> competition = League.list.stream().filter(l -> l.getName().equals(args[0]) && l.getEdition().equals(args[1]))
                    .findFirst();
            if (competition.isPresent()) {
                Competition league = competition.get();
                if (!league.isArchived()) {
                    league.archive();
                    System.out.printf("La league %s édition %s est désormais archivé.\n", args[0], args[1]);
                } else
                    System.out.printf("La league %s édition %s est déjà archivé.\n", args[0], args[1]);
            } else {
                System.out.println("Aucune league n'a été trouvé.");
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
