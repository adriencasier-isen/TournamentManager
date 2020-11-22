package fr.isen.m1.tourament.console.commands;

import fr.isen.m1.tourament.Utils;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class SaveCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Enregistrer les données de l'application dans des fichiers situé dans \"./data/\"\n" +
                "S'utilise ainsi: \"save\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // Sauvegarde des données depuis les fichiers situé dans "./data/"
        Utils.saveLeagues();
        Utils.saveTeams();
    }

    @Override
    public String getCommandName() {
        return "save";
    }
}
