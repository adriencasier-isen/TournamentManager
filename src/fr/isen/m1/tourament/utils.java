package fr.isen.m1.tourament;

import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.competition.Tournament;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


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
    public void saveLeague(){
        League.list.stream().forEach(l -> {
            try {
                FileOutputStream fichierOut = new FileOutputStream("./data/leagues/"+ l.getName().toLowerCase()+"_"+ l.getEdition().toLowerCase()+".txt");
                ObjectOutputStream objOut= new ObjectOutputStream(fichierOut);
                objOut.writeObject(l);
                objOut.close();
                fichierOut.close();
                System.out.println("Object has been serialized");
            } catch (Exception e) {
                System.out.println("IOException is caught");
            }
          });
    }
    public void readLeague(){
         try(Stream<Path> walk = Files.walk(Paths.get("./data/leagues/"))){
            List<String> result = walk.map(x -> x.toString())
            .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
            result.forEach(p->{
                try{
                FileInputStream fichierIn = new FileInputStream(p);
                ObjectInputStream objIn = new ObjectInputStream(fichierIn);
                new League((League) objIn.readObject());
                objIn.close();
                fichierIn.close();
                }
                catch(Exception e) {
                    System.out.println("IOException is caught");
                }
            });
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveTournament(){
        Tournament.list.stream().forEach(l -> {
            try {
                FileOutputStream fichierOut = new FileOutputStream("./data/tournaments/"+ l.getName().toLowerCase()+"_"+ l.getEdition().toLowerCase()+".txt");
                ObjectOutputStream objOut= new ObjectOutputStream(fichierOut);
                objOut.writeObject(l);
                objOut.close();
                fichierOut.close();
                System.out.println("Object has been serialized");
            } catch (Exception e) {
                System.out.println("IOException is caught");
            }
          });
    }
    /*public void readTournament(){
        try(Stream<Path> walk = Files.walk(Paths.get("./data/tournaments/"))){
            List<String> result = walk.map(x -> x.toString())
            .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
            result.forEach(p->{
                try{
                FileInputStream fichierIn = new FileInputStream(p);
                ObjectInputStream objIn = new ObjectInputStream(fichierIn);
                new League((League) objIn.readObject());
                objIn.close();
                fichierIn.close();
                }
                catch(Exception e) {
                    System.out.println("IOException is caught");
                }
            });
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}
