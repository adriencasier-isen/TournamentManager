package fr.isen.m1.tourament;

import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.competition.Tournament;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class utils {
    public void creatLeague() {
        try {
            createCompetition(League.class);
        } catch (Exception ignored) {

        }
    }

    public void createTournement() {
        try {
            createCompetition(Tournament.class);
        } catch (Exception ignored) {

        }
    }

    private void createCompetition(Class<? extends Competition> competitionClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Scanner userInput = new Scanner(System.in);
        String name, edition, location;
        int confrontation;
        System.out.print("Nom de la compétition:\n: ");
        name = userInput.nextLine();
        System.out.print("Nombre de matchs par confrontations:\n: ");
        confrontation = Integer.parseInt(userInput.nextLine());
        System.out.print("Edition:\n: ");
        edition = userInput.nextLine();
        System.out.print("Zone geographique:\n: ");
        location = userInput.nextLine();
        competitionClass.getDeclaredConstructor().newInstance(name, confrontation, edition, location);
    }
}
