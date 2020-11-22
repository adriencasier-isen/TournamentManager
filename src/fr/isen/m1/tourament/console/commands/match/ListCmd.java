package fr.isen.m1.tourament.console.commands.match;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.stream.Collectors;

public class ListCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Affiche la liste des matchs de la league selectionné.\n" +
                "S'utilise ainsi: \"match list\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie qu'il y a bien aucun argument
        if (cmdContext.args.length == 0) {
            // On vérifie qu'il y a des matchs à afficher
            if (!Main.selectedCompetition.matchList.isEmpty()) {
                // On affiche les matches sous forme de tableau
                String competitionText = Main.selectedCompetition.getName()
                        + " édition " + Main.selectedCompetition.getEdition();
                System.out.printf("Liste des matchs de %s :\n" +
                                "%s\n", competitionText,
                        Main.selectedCompetition.matchList.stream()
                                .map(m -> "#" + m.get_id() + "\t| " + m.get_match_score())
                                .collect(Collectors.joining("\n")));
            } else {
                System.out.println("Aucun match n'a été enregistré dans la base de données.");
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
