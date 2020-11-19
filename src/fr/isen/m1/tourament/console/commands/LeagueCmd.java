package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.CommandHandler;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.console.commands.league.ArchiveCmd;
import fr.isen.m1.tourament.console.commands.league.CreateCmd;
import fr.isen.m1.tourament.console.commands.league.DeleteCmd;
import fr.isen.m1.tourament.console.commands.league.ListCmd;

public class LeagueCmd implements ICommand {
    final CommandHandler cmdHandler;

    public LeagueCmd() {
        ICommand[] commandRegister = {
                new CreateCmd(),
                new ListCmd(),
                new ArchiveCmd(),
                new DeleteCmd()
        };
        this.cmdHandler = new CommandHandler(commandRegister);
    }

    @Override
    public String getHelpMessage() {
        return "Cette commande vous permet de gérer une ou plusieurs leagues.\nPour afficher la liste des commandes disponible, entrez \"league help\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length > 0) {
            String subCmd = cmdContext.originalCommand.replace(String.format("%s ", cmdContext.parsedCommandName), "");
            this.cmdHandler.handleMessageWithPrefix(subCmd, cmdContext.parsedCommandName);
        } else {
            System.out.println("Un ou plusieurs arguments sont attendu.\nUtilisez la commande comme ceci: \"league <arg> [...]\"");
        }
    }

    @Override
    public String getCommandName() {
        return "league";
    }
}
