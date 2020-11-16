package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class HelloWorldCmd implements ICommand {

    public final String commandName = "helloworld";

    @Override
    public String getHelpMessage() {
        return "Say HelloWorld to you :)";
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void run(CommandContext cmdContext) {
        System.out.println("Hello World");
    }
}
