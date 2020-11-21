package fr.isen.m1.tourament.console.commands.team;

import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Team;

import java.util.Optional;

public class RemoveCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Supprimer une équipe de la base de données.";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 3) {
            String[] args = cmdContext.args;
            Optional<Team> team = Team.teamList.stream()
                    .filter(t -> t.get_name().equals(args[0])
                            && t.get_tag().equals(args[1])
                            && t.get_country().equals(args[2]))
                    .findFirst();
            if (team.isPresent()) {
                Team.teamList.remove(team.get());
                System.out.printf("L'équipe %s [%s] a été supprimé.\n", args[0], args[1]);
            } else {
                System.out.println("Aucune équipe n'a été trouvé.");
            }
        } else {
            System.out.println("Cette commande attend exactement 3 arguments");
        }
    }

    @Override
    public String getCommandName() {
        return "remove";
    }
}
