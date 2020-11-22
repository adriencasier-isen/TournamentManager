package fr.isen.m1.tourament.console;

import fr.isen.m1.tourament.console.commands.HelpCmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandHandler {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandHandler(ICommand[] commandRegister) {
        // Registre des commandes
        this.commands.addAll(Arrays.asList(commandRegister));
        this.commands.add(new HelpCmd(this.commands));
    }

    public void handleMessage(String msg) {
        // On exploite l'interface ICommande afin d'executer la fonction run() par la commande trouvé
        // grace au context de commande
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
        // On exploite l'interface ICommande afin d'executer la fonction run() par la commande trouvé
        // grace au context de commande. Ici, on a la gestion du prefix, requis par les sous-commandes
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
