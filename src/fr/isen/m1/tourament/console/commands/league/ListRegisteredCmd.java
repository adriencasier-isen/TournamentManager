package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.stream.Collectors;

public class ListRegisteredCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Affiche la liste des participants de la league selectionné.\n" +
                "S'utilise ainsi: \"league teams\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie que l'utilisateur a bien sélectionné une competition de type league
        if (Main.selectedCompetition != null) {
            if (Main.selectedCompetition.getClass().equals(League.class)) {
                // On vérifie qu'il n'y ai pas d'arguments
                if (cmdContext.args.length == 0) {
                    // Vérification d'existe d'équipe enregistré
                    if (!Main.selectedCompetition.teamlist.isEmpty()) {
                        // Affichage des équipes enregistré
                        System.out.printf("Liste des équipes inscrites :\n" +
                                        "%s\n",
                                Main.selectedCompetition.teamlist.stream()
                                        .map(t -> " - " + t.get_name() + " [" + t.get_tag() + "]")
                                        .collect(Collectors.joining("\n")));
                    } else {
                        System.out.println("Aucun match n'a été enregistré dans la base de données.");
                    }
                } else {
                    System.out.println("Cette commande attend exactement 0 argument.");
                }
            } else {
                System.out.println("La compétition selectionné ne correspond pas au type LEAGUE.");
            }
        } else {
            System.out.println("Vous n'avez pas selectionné de compétition.");
        }
    }

    @Override
    public String getCommandName() {
        return "teams";
    }
}
