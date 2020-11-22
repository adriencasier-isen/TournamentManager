package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.CommandHandler;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.console.commands.match.*;

public class MatchCmd implements ICommand {
    final CommandHandler cmdHandler;

    public MatchCmd() {
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
        if (Main.selectedCompetition != null) {
            if (cmdContext.args.length > 0) {
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
