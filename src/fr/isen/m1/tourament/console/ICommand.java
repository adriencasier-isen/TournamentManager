package fr.isen.m1.tourament.console;

public interface ICommand {
    String getHelpMessage();

    void run(CommandContext cmdContext);

    String getCommandName();
}
