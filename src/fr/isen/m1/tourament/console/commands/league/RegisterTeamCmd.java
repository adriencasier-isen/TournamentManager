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
        // On vérifie que l'utilisateur a bien sélectionné une competition de type league
        if (Main.selectedCompetition != null) {
            if (Main.selectedCompetition.getClass().equals(League.class)) {
                // On vérifie qu'il y ai bien 1 seul commentaire
                if (cmdContext.args.length == 1) {
                    String[] args = cmdContext.args;
                    // On vérifie qu'il n'y ai pas d'équipe enregistré avec les arguments donné
                    boolean alreadyRegistered = Main.selectedCompetition.teamlist.stream()
                            .anyMatch(t -> t.get_tag().equals(args[0].toUpperCase()));
                    if (!alreadyRegistered) {
                        // On recherche l'équipe parmis toute les équipes dans la mémoire
                        Optional<Team> team = Team.teamList.stream()
                                .filter(t -> t.get_tag().equals(args[0].toUpperCase())
                                        && t.get_sport().equals(Main.selectedCompetition.getSport()))
                                .findFirst();
                        if (team.isPresent()) {
                            // Si l'équipe existe alors on l'inscrit
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
        } else {
            System.out.println("Vous n'avez pas selectionné de compétition.");
        }
    }

    @Override
    public String getCommandName() {
        return ("register");
    }
}
