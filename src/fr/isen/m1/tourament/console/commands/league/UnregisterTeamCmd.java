package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

import java.util.Optional;

public class UnregisterTeamCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Retire une équipe de la liste des équipes participantes à la league selectionné.\n" +
                "S'utilise ainsi: \"league unregister <team_tag:String>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        // On vérifie que l'utilisateur a bien sélectionné une competition de type league
        if (Main.selectedCompetition != null) {
            if (Main.selectedCompetition.getClass().equals(League.class)) {
                // On vérifie qu'il y ai bien 1 seul commentaire
                if (cmdContext.args.length == 1) {
                    String[] args = cmdContext.args;
                    // On recherche l'équipe à retirer de la liste des équipes participantes
                    Optional<Team> team = Main.selectedCompetition.teamlist.stream()
                            .filter(t -> t.get_tag().equals(args[0].toUpperCase()))
                            .findFirst();
                    if (team.isPresent()) {
                        // Si trouvé, on retire la liste des équipes participantes
                        Main.selectedCompetition.teamlist.remove(team.get());
                        System.out.printf("L'équipe %s [%s] a été retiré des participants.\n", team.get().get_name(), team.get().get_tag());
                    } else {
                        System.out.println("Aucune équipe n'a été trouvé.");
                    }
                } else {
                    System.out.println("Cette commande attend exactement 1 arguments");
                }
            } else {
                System.out.println("La compétition selectionné ne correspond pas au type LEAGUE.");
            }
        } else {
            System.out.println("Vous n'avez pas selectionné de compétition.");
        }
    }

    @Override
    public String getCommandName() {
        return "unregister";
    }
}
