package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HelpCmd implements ICommand {

    public final String commandName = "help";

    private final List<ICommand> commands;

    public HelpCmd(List<ICommand> cmdList) {
        this.commands = cmdList;
    }

    @Override
    public String getHelpMessage() {
        return "Cette commande vous permet d'afficher la page d'aide des autres commandes.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 0) {
            System.out.printf("Essayez \"%shelp <cmd>\" afin d'afficher la page d'aide d'une commande.\nListe des commandes:\n - %s\n",
                    cmdContext.prefix != null ? String.format("%s ", cmdContext.prefix) : "",
                    commands.stream().map(cmd -> cmd.getCommandName())
                            .collect(Collectors.joining("\n - ")));
        } else {
            Optional<ICommand> matchedCmd = commands.stream()
                    .filter(cmd -> cmd.getCommandName().equals(cmdContext.args[0])).findFirst();
            if (matchedCmd.isPresent()) {
                System.out.println(this.buildHelpMessageForCommand(matchedCmd.get(), cmdContext));
            } else {
                System.out.printf("La commande \"%s\" n'existe pas.\n", cmdContext.args[0]);
            }
        }
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    private String buildHelpMessageForCommand(ICommand cmd, CommandContext cmdContext) {
        return String.format("# %s \nDescriptiton:\n%s", cmd.getCommandName(), cmd.getHelpMessage());
    }
}
