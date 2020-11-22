package fr.isen.m1.tourament.console.commands.match;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Match;

import java.util.Optional;

public class EndCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Définir un match à terminé.\n" +
                "S'utilise ainsi: \"match end <match_id:Number>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie qu'il y a bien 1 argument
        if (cmdContext.args.length == 1) {
            String[] args = cmdContext.args;
            // On cherche le match que l'on souhaite définir comme terminé
            Optional<Match> match = Main.selectedCompetition.matchList.stream()
                    .filter(m -> m.get_id().equals(Long.parseLong(args[0]))).findFirst();
            if (match.isPresent()) {
                // Si on a trouvé le match, on le défini comme terminé si il ne l'est pas
                if (!match.get().get_isEnded()) {
                    match.get().end();
                    System.out.printf("Le match #%s est maintenant terminer.\n", args[0]);
                } else {
                    System.out.printf("Le match #%s est déjà terminé.\n", args[0]);
                }
            } else {
                System.out.printf("Aucun match n'a été trouvé avec l'ID %s.\n", args[0]);
            }
        } else {
            System.out.println("Cette commande attend exactement 1 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "end";
    }
}
