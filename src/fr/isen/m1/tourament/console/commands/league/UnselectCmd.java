package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

public class UnselectCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Déselectionne la league actuellement selectionné.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 0) {
            if (Main.selectedCompetition != null) {
                Main.selectedCompetition = null;
            } else {
                System.out.println("Aucune league n'est actuellement selectionné.");
            }
        } else {
            System.out.println("Cette commande attend exactement 0 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "unselect";
    }
}
