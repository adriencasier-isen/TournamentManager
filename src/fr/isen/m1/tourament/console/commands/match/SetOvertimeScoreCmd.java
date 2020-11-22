package fr.isen.m1.tourament.console.commands.match;

import fr.isen.m1.tourament.Main;
import fr.isen.m1.tourament.console.CommandContext;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.models.Match;

import java.util.Optional;

public class SetOvertimeScoreCmd implements ICommand {
    @Override
    public String getHelpMessage() {
        return "Définir le score de prolongations.\n" +
                "S'utilise ainsi: \"match setotscore <match_id:Number> <score_team_A:Number> <score_team_B:Number>\"";
    }

    @Override
    public void run(CommandContext cmdContext) {
        if (cmdContext.args.length == 3) {
            String[] args = cmdContext.args;
            Optional<Match> match = Main.selectedCompetition.matchList.stream()
                    .filter(m -> m.get_id().equals(Long.parseLong(args[0]))).findFirst();
            if (match.isPresent()) {
                match.get().setOvertimeScore(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                System.out.printf("Les scores de prolongation du match #%s ont été défini.\n", args[0]);
            } else {
                System.out.printf("Aucun match n'a été trouvé avec l'ID %s.\n", args[0]);
            }
        } else {
            System.out.println("Cette commande attend exactement 3 argument.");
        }
    }

    @Override
    public String getCommandName() {
        return "setotscore";
    }
}
