package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.CommandHandler;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.console.commands.team.AddCmd;
import fr.isen.m1.tourament.console.commands.team.EditCmd;
import fr.isen.m1.tourament.console.commands.team.ListCmd;
import fr.isen.m1.tourament.console.commands.team.RemoveCmd;

public class TeamCmd implements ICommand {
    final CommandHandler cmdHandler;

    public TeamCmd() {
        ICommand[] commandRegister = {
                new AddCmd(),
                new ListCmd(),
                new EditCmd(),
                new RemoveCmd()
        };
        this.cmdHandler = new CommandHandler(commandRegister);
    }

    @Override
    public String getHelpMessage() {
        return "Permet de gérer/afficher les équipes inscritent dans la compétition sélectionné.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length > 0) {
            String subCmd = cmdContext.originalCommand.replace(String.format("%s ", cmdContext.parsedCommandName), "");
            this.cmdHandler.handleMessageWithPrefix(subCmd, cmdContext.parsedCommandName);
        } else {
            System.out.println("Un ou plusieurs arguments sont attendu.\nUtilisez la commande comme ceci: \"team <arg> [...]\"");
        }
    }

    @Override
    public String getCommandName() {
        return "team";
    }
}
