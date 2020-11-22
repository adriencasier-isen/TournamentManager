package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.stream.Collectors;

public class ListRegisteredCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Affiche la liste des participants de la league selectionné.\n" +
                "S'utilise ainsi: \"league teams\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 0) {
            if (!Main.selectedCompetition.teamlist.isEmpty()) {
                System.out.printf("Liste des équipes inscrites :\n" +
                                "%s\n",
                        Main.selectedCompetition.teamlist.stream()
                                .map(t -> " - " + t.get_name() + " [" + t.get_tag() + "]")
                                .collect(Collectors.joining("\n")));
            } else {
                System.out.println("Aucun match n'a été enregistré dans la base de données.");
            }
        } else {
            System.out.println("Cette commande attend exactement 0 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "teams";
    }
}
