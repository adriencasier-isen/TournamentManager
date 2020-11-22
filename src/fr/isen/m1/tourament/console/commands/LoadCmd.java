package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.Utils;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class LoadCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Charger les données des fichiers situé dans \"./data/\"\n" +
                "S'utilise ainsi: \"load\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        Utils.loadLeagues();
        Utils.loadTeams();
    }

    @Override
    public String getCommandName() {
        return "load";
    }
}
