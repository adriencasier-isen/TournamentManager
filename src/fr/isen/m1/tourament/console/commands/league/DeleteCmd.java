package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.Optional;

public class DeleteCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Permet de supprimer une league. Attention, cette action est irreversible.\n" +
                "S'utilise ainsi: \"league delete <nom:String> <edition:String>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie que la commande contient 2 arguments
        if (cmdContext.args.length == 2) {
            String[] args = cmdContext.args;
            // On recherche la compétition à supprimer
            Optional<Competition> competition = League.list.stream()
                    .filter(l -> l.getName().equals(args[0]) && l.getEdition().equals(args[1])).findFirst();
            if (competition.isPresent()) {
                // Si trouvé, on la supprime et on supprime, si nécessaire, le contenu de la selection
                if (competition.get().equals(Main.selectedCompetition)) {
                    Main.selectedCompetition = null;
                }
                League.list.remove(competition.get());
                System.out.printf("La league %s édition %s a été supprimé.\n", args[0], args[1]);
            } else
                System.out.println("Cette league n'existe pas.");
        } else if (Main.selectedCompetition != null && Main.selectedCompetition.getClass() == League.class) {
            // Si aucun arguments ne sont donné MAIS que l'utilisateur a sélectionné une compétition au préalable
            // Alors on supprime la compétition sélectionné
            League.list.remove(Main.selectedCompetition);
            System.out.printf("La league %s édition %s a été supprimé.\n", Main.selectedCompetition.getName(), Main.selectedCompetition.getEdition());
            Main.selectedCompetition = null;
        } else {
            System.out.println("Cette commande attend exactement 2 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "delete";
    }
}
