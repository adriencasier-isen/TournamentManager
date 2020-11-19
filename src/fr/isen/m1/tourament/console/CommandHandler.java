package fr.isen.m1.tourament.console;

import fr.isen.m1.tourament.console.commands.HelpCmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandHandler {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandHandler(ICommand[] commandRegister) {
        this.commands.addAll(Arrays.asList(commandRegister));
        this.commands.add(new HelpCmd(this.commands));
    }

    public void handleMessage(String msg) {
        CommandContext cmdContext = new CommandContext(msg, Optional.empty());
        Optional<ICommand> matchedCmd = this.commands.stream()
                .filter(cmd -> cmd.getCommandName().equals(cmdContext.parsedCommandName)).findFirst();
        if (matchedCmd.isPresent()) {
            matchedCmd.get().run(cmdContext);
        } else {
            System.out.println("Cette commande n'existe pas.");
        }
    }

    public void handleMessageWithPrefix(String msg, String prefix) {
        CommandContext cmdContext = new CommandContext(msg, Optional.ofNullable(prefix));
        Optional<ICommand> matchedCmd = this.commands.stream()
                .filter(cmd -> cmd.getCommandName().equals(cmdContext.parsedCommandName)).findFirst();
        if (matchedCmd.isPresent()) {
            matchedCmd.get().run(cmdContext);
        } else {
            System.out.println("Cette commande n'existe pas.");
        }
    }
}
