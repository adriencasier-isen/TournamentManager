package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;

import java.util.Optional;

public class SelectCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Sélectionne la ligue indiquée dans les paramètres.\n" +
                "S'utilise ainsi: \"league select <nom:String> <edition:String>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 2) {
            String[] args = cmdContext.args;
            Optional<Competition> competition = League.list.stream()
                    .filter(l -> l.getName().equals(args[0]) && l.getEdition().equals(args[1])).findFirst();
            if (competition.isPresent()) {
                Main.selectedCompetition = competition.get();
            } else {
                System.out.println("Cette league n'existe pas.");
            }
        } else {
            System.out.println("Cette commande attend exactement 2 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "select";
    }
}
