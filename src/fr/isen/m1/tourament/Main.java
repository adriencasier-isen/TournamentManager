package fr.isen.m1.tourament;

import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.console.CommandHandler;
import fr.isen.m1.tourament.console.ICommand;
import fr.isen.m1.tourament.console.commands.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static Competition selectedCompetition = null;

    public static void main(String[] args) {
        // Registre de commandes de premier niveau
        ICommand[] commandRegister = {
                new LeagueCmd(),
                new TeamCmd(),
                new MatchCmd(),
                new LoadCmd(),
                new SaveCmd()
        };
        if (Utils.initSaveFileLocation()) {
            final CommandHandler cmdHandler = new CommandHandler(commandRegister);
            Scanner scanner = new Scanner(System.in);
            boolean isExited = false;
            String userInput;
            String[] exitCmd = {"exit", "quit", "close"};

            System.out.println("# CompetitionManager.\nAfin d'afficher la liste des commandes, entrez \"help\".");
            Utils.displayBasePath();

            while (!isExited) {
                // Boucle de lecture des commandes
                System.out.printf("%s> ", renderSelected());
                userInput = scanner.nextLine();
                if (Arrays.asList(exitCmd).contains(userInput)) isExited = true;
                else cmdHandler.handleMessage(userInput);
            }
        } else {
            System.out.println("Une erreur est survenue lors de l'initialisation de l'application.");
        }
    }

    private static String renderSelected() {
        return Main.selectedCompetition != null ?
                String.format("%s|%s",
                        Main.selectedCompetition.getStageType(),
                        (Main.selectedCompetition.isArchived() ? "(A)" : "") +
                                Main.selectedCompetition.getName() + " "
                                + Main.selectedCompetition.getEdition())
                : "";
    }
}

