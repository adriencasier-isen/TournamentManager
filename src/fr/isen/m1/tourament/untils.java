import fr.isen.m1.tourament.competition.League;


public class untils {
    def creatLeague(){
        System.out.println("Nom,Confrontation,edition,location");
        Scanner scanner= new Scanner(System.in);
        tableaux tabref= scanner.split(",\n");
        String name=tabref[0];
        int confrontation=tabref[1];
        String edition=tabref[2];
        String location=tabref[3];
        new League(name, confrontation,"League",edition, location );
    }
    def creatTornement(){
        
    }
    
}
