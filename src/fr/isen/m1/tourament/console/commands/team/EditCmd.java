package fr.isen.m1.tourament.console.commands.team;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class EditCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Permet d'éditer les informations d'une équipe.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        
    }

    @Override
    public String getCommandName() {
        return "edit";
    }
}
