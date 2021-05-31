package view;
import java.util.Scanner;
public class HomeView {

  public void afficherhomepage(){
   LocationView location = new LocationView();
   ClientView client = new ClientView();
   VehiculeView vehicule = new VehiculeView();
   Scanner myObj = new Scanner(System.in);
   System.out.println("________________Home Page_________________");
   System.out.println("1. Louer un véhicule\n2. Gestion des Véhicules\n3. Gestion des Clients\n4. FIN");
   int commande = myObj.nextInt();
   while(commande != 4){
    if(commande == 1){
     location.afficher();
    }else if(commande == 2){
     vehicule.affichervehicule();
    }else if(commande == 3){
     client.afficherclient();
    }
    System.out.println("1. Louer un véhicule\n2. Gestion des Véhicules\n3. Gestion des Clients\n4. FIN");
    commande = myObj.nextInt();
   }
   myObj.close();
  }
 
}
