package fr.isen.m1.tourament;

import fr.isen.m1.tourament.console.CommandHandler;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final CommandHandler cmdHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        boolean isExited = false;
        String userInput;
        String[] exitCmd = {"exit", "quit", "close"};

        System.out.println("Bienvenu à TournamentManager.\nAfin d'afficher la liste des commandes, entrez \"help\".");

        while (!isExited) {
            System.out.print("> ");
            userInput = scanner.nextLine();
            if (Arrays.asList(exitCmd).contains(userInput)) isExited = true;
            else cmdHandler.handleMessage(userInput);
        }
    }
}
