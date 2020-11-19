package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.CommandHandler;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.console.commands.HelloWorldCmd;

public class MatchCmd implements ICommand {
    final CommandHandler cmdHandler;

    public MatchCmd() {
        ICommand[] commandRegister = {
                new HelloWorldCmd()
        };
        this.cmdHandler = new CommandHandler(commandRegister);
    }

    @Override
    public String getHelpMessage() {
        return "Permet de gérer/afficher les matchs d'une league.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length > 0) {
            String subCmd = cmdContext.originalCommand.replace(String.format("%s ", cmdContext.parsedCommandName), "");
            this.cmdHandler.handleMessageWithPrefix(subCmd, cmdContext.prefix + " " + cmdContext.parsedCommandName);
        } else {
            System.out.println("Un ou plusieurs arguments sont attendu.\nUtilisez la commande comme ceci: \"league match <arg> [...]\"");
        }
    }

    @Override
    public String getCommandName() {
        return "match";
    }
}
