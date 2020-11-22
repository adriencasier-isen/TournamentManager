package fr.isen.m1.tourament.console.commands.league;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

import java.util.Optional;

public class RegisterTeamCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return ("Permet d'inscrire une équipe à la league selectionné.\n" +
                "S'utilise ainsi: \"league register <team_tag:String>\"");
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (Main.selectedCompetition.getClass().equals(League.class)) {
            if (cmdContext.args.length == 1) {
                String[] args = cmdContext.args;
                boolean alreadyRegistered = Main.selectedCompetition.teamlist.stream()
                        .anyMatch(t -> t.get_tag().equals(args[0].toUpperCase()));
                if (!alreadyRegistered) {
                    Optional<Team> team = Team.teamList.stream()
                            .filter(t -> t.get_tag().equals(args[0].toUpperCase())
                                    && t.get_sport().equals(Main.selectedCompetition.getSport()))
                            .findFirst();
                    if (team.isPresent()) {
                        Main.selectedCompetition.teamlist.add(team.get());
                        System.out.printf("L'équipe %s [%s] a été ajouté à la liste des participants.\n", team.get().get_name(), team.get().get_tag());
                    } else {
                        System.out.println("Aucune équipe n'a été trouvé.");
                    }
                } else {
                    System.out.printf("L'équipe [%s] est déjà inscrite à la league.\n", args[0].toUpperCase());
                }
            } else {
                System.out.println("Cette commande attend exactement 1 arguments");
            }
        } else {
            System.out.println("La compétition selectionné ne correspond pas au type LEAGUE.");
        }
    }

    @Override
    public String getCommandName() {
        return ("register");
    }
}
