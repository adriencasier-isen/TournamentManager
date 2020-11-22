package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.CommandHandler;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.console.commands.match.*;

public class MatchCmd implements ICommand {
    final CommandHandler cmdHandler;

    public MatchCmd() {
        // Registre des sous-commandes disponible depuis la commande "match"
        ICommand[] commandRegister = {
                new RemoveCmd(),
                new AddCmd(),
                new EndCmd(),
                new ListCmd(),
                new SetOvertimeScoreCmd(),
                new SetScoreCmd()
        };
        this.cmdHandler = new CommandHandler(commandRegister);
    }

    @Override
    public String getHelpMessage() {
        return "Permet de gérer/afficher les matchs de la competition selectionné.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie que l'utilisateur a bien selectionné une compétition
        if (Main.selectedCompetition != null) {
            // On vérifie qu'il y a plus d'un argument
            if (cmdContext.args.length > 0) {
                // On prépare le travail du CommandHandler en créant un contexte de sous-commande avec pour préfix "match "
                String subCmd = cmdContext.originalCommand.replace(String.format("%s ", cmdContext.parsedCommandName), "");
                this.cmdHandler.handleMessageWithPrefix(subCmd, cmdContext.parsedCommandName);
            } else {
                System.out.println("Un ou plusieurs arguments sont attendu.\nUtilisez la commande comme ceci: \"team <arg> [...]\"");
            }
        } else {
            System.out.println("Vous n'avez selectionné aucune compétition.");
        }
    }

    @Override
    public String getCommandName() {
        return "match";
    }
}
