package fr.isen.m1.tourament.console.commands.match;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Match;

import java.util.Optional;

public class RemoveCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Retirer un match de la liste des matchs de la compétition selectionné." +
                "S'utilise ainsi: \"match remove <match_id:Number>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie qu'il y a bien 1 argument
        if (cmdContext.args.length == 1) {
            String[] args = cmdContext.args;
            // On recherche le match à supprimer par rapport à son ID
            Optional<Match> match = Main.selectedCompetition.matchList.stream()
                    .filter(m -> m.get_id().equals(Long.parseLong(args[0]))).findFirst();
            if (match.isPresent()) {
                // Si trouvé, on supprime le match
                Main.selectedCompetition.matchList.remove(match.get());
                System.out.printf("Le match #%s a été supprimé.\n", args[0]);
            } else {
                System.out.printf("Aucun match n'a été trouvé avec l'ID %s.\n", args[0]);
            }
        } else {
            System.out.println("Cette commande attend exactement 1 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "remove";
    }
}
