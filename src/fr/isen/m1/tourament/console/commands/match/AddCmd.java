package fr.isen.m1.tourament.console.commands.match;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Ajoute un match à la compétition selectionné.\n" +
                "S'utilise ainsi: \"match add <team_tag_A:String> <team_tag_B:String>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 2) {
            String[] args = cmdContext.args;
            Optional<Team> teamA = Main.selectedCompetition.teamlist.stream()
                    .filter(t -> t.get_tag().equals(args[0].toUpperCase()))
                    .findFirst();
            Optional<Team> teamB = Main.selectedCompetition.teamlist.stream()
                    .filter(t -> t.get_tag().equals(args[1].toUpperCase()))
                    .findFirst();
            if (teamA.isPresent() && teamB.isPresent()) {
                Main.selectedCompetition.addNewMatch(teamA.get(), teamB.get());
                System.out.printf("Le match qui opposera %s [%s] à %s [%s] a été crée.\n",
                        teamA.get().get_name(), teamA.get().get_tag(), teamB.get().get_name(), teamB.get().get_tag());
            } else {
                List<String> unknowTeams = new ArrayList<>();
                if (teamA.isEmpty()) unknowTeams.add(args[0].toUpperCase());
                if (teamB.isEmpty()) unknowTeams.add(args[1].toUpperCase());
                if (unknowTeams.size() == 2) {
                    System.out.printf("Les équipes [%s] et [%s] n'ont pas été reconnu dans la liste des participants.\n",
                            unknowTeams.get(0), unknowTeams.get(1));
                } else if (unknowTeams.size() == 1) {
                    System.out.printf("L'équipes [%s] n'a pas été reconnu dans la liste des participants.\n",
                            unknowTeams.get(0));
                } else {
                    System.out.println("Une erreur s'est produite lors de la recherche d'équipes existante.");
                }
            }
        } else {
            System.out.println("Cette commande attend exactement 2 arguments.");
        }
    }

    @Override
    public String getCommandName() {
        return "add";
    }
}
