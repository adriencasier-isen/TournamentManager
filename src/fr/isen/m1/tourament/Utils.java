package fr.isen.m1.tourament;

import fr.isen.m1.tourament.competition.Competition;
import fr.isen.m1.tourament.competition.League;
import fr.isen.m1.tourament.models.Team;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class Utils {
    private static final String basePath = new File("").getAbsolutePath();
    private static final String leaguesPath = "/data/leagues/";
    private static final String teamsPath = "/data/teams/";

    public static void displayBasePath() {
        System.out.printf("BasePath: %s\n", basePath);
    }

    public static boolean initSaveFileLocation() {
        File leaguesDir = new File(basePath.concat(leaguesPath));
        File teamsDir = new File(basePath.concat(teamsPath));
        if (!leaguesDir.exists()) leaguesDir.mkdirs();
        if (!teamsDir.exists()) teamsDir.mkdirs();
        return leaguesDir.isDirectory() && teamsDir.exists();
    }

    public static void saveLeagues() {
        int successCount = 0;
        for (Competition l : League.list) {
            try {
                String fileName = l.getName().toLowerCase() + "_" + l.getEdition().toLowerCase() + ".txt";
                FileOutputStream fichierOut = new FileOutputStream(Paths.get(basePath + leaguesPath + fileName).toString());
                ObjectOutputStream objOut = new ObjectOutputStream(fichierOut);
                objOut.writeObject(l);
                objOut.close();
                fichierOut.close();
                successCount += 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Sauvegarde des leagues réussis (%d/%d)\n", successCount, League.list.size());
    }

    public static void loadLeagues() {
        int successCount = 0;
        try (Stream<Path> walk = Files.walk(Paths.get(basePath + leaguesPath))) {
            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
            if (!result.isEmpty()) {
                for (String p : result) {
                    try {
                        FileInputStream fichierIn = new FileInputStream(p);
                        ObjectInputStream objIn = new ObjectInputStream(fichierIn);
                        League tmp = (League) objIn.readObject();
                        if (tmp.getClass().equals(League.class)) {
                            if (League.list.stream().noneMatch(
                                    l -> l.getName().equals(tmp.getName()) && l.getEdition().equals(tmp.getEdition())
                            )) {
                                new League(tmp);
                                successCount++;
                            }
                        }
                        objIn.close();
                        fichierIn.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("%d leagues ont été chargés avec succès.\n", successCount);
            } else {
                System.out.println("Aucune league n'a été trouvé dans le dossier de stockage.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTeams() {
        int successCount = 0;
        for (Team t : Team.teamList) {
            try {
                String fileName = t.get_name().toLowerCase() + "_"
                        + t.get_tag().toLowerCase() + "_"
                        + t.get_sport().toLowerCase() + ".txt";
                FileOutputStream fichierOut = new FileOutputStream(Paths.get(basePath + teamsPath + fileName).toString());
                ObjectOutputStream objOut = new ObjectOutputStream(fichierOut);
                objOut.writeObject(t);
                objOut.close();
                fichierOut.close();
                successCount++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Sauvegarde des équipes réussis (%d/%d)\n", successCount, Team.teamList.size());
    }

    public static void loadTeams() {
        int successCount = 0;
        try (Stream<Path> walk = Files.walk(Paths.get(basePath + teamsPath))) {
            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
            if (!result.isEmpty()) {
                for (String p : result) {
                    try {
                        FileInputStream fichierIn = new FileInputStream(p);
                        ObjectInputStream objIn = new ObjectInputStream(fichierIn);
                        Team tmp = (Team) objIn.readObject();
                        if (tmp.getClass().equals(Team.class)) {
                            if (Team.teamList.stream().noneMatch(
                                    t -> t.get_tag().equals(tmp.get_tag()) && t.get_sport().equals(tmp.get_sport())
                            )) {
                                new Team(tmp);
                                successCount++;
                            }
                        }
                        objIn.close();
                        fichierIn.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("%d équipes ont été chargés avec succès.\n", successCount);
            } else {
                System.out.println("Aucune équipe n'a été trouvé dans le dossier de stockage.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
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
    public void readTournament(){
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
