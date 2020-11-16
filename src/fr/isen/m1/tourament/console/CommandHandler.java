package fr.isen.m1.tourament.console;

import fr.isen.m1.tourament.console.commands.HelloWorldCmd;
import fr.isen.m1.tourament.console.commands.HelpCmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandHandler {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandHandler() {
        ICommand[] commandClasses = {
                new HelloWorldCmd(),
        };

        this.commands.addAll(Arrays.asList(commandClasses));
        this.commands.add(new HelpCmd(this.commands));
    }

    public void handleMessage(String msg) {
        CommandContext cmdContext = new CommandContext(msg);
        Optional<ICommand> matchedCmd = this.commands.stream()
                .filter(cmd -> cmd.getCommandName().equals(cmdContext.parsedCommandName)).findFirst();
        if (matchedCmd.isPresent()) {
            matchedCmd.get().run(cmdContext);
        } else {
            System.out.println("La commande n'a pas ete reconnue.");
        }
    }
}
