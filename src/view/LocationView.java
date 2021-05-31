package view;
import java.time.LocalDate;
import java.util.Scanner;

import service.LocationDAO;
public class LocationView {
 
 public void afficher(){
  LocationDAO fine = new LocationDAO();
  Scanner myObj = new Scanner(System.in);
  System.out.println("Page de locations");
  System.out.println("1. Louer un modèle de voiture\n2. rendre un véhicule\n3. Devis en cours pour un client\n4. Dernière facture\n5. fin");
  int commande = myObj.nextInt();
  myObj.nextLine();
  while(commande != 5){
   if(commande == 1){
    System.out.println("Quelle est votre numéro client ?");
    int client_id = myObj.nextInt();
    System.out.println("Année de la location ?");
    int startingyear = myObj.nextInt();
    System.out.println("mois de la location ?");
    int startingmonth = myObj.nextInt();
    System.out.println("jour du début de la location ?");
    int startingday = myObj.nextInt();
    System.out.println("Année de la fin de location ?");
    int returnyear = myObj.nextInt();
    System.out.println("mois de la fin de location ?");
    int returnmonth = myObj.nextInt();
    System.out.println("jour de fin de la location ?");
    int returnday = myObj.nextInt();
    myObj.nextLine();
    System.out.println("Quelle modèle voulez vous louer ?");
    String model = myObj.nextLine();
    System.out.println("Où voulez vous louer cette voiture ?");
    String place = myObj.nextLine();
    System.out.println("Voulez vous prendre un assurance ?(true/false)");
    Boolean insurance = myObj.nextBoolean();
    fine.requetenewquote(insurance, LocalDate.of(startingyear, startingmonth, startingday), LocalDate.of(returnyear, returnmonth, returnday), client_id ,model, place);
   } else if(commande == 2){
    System.out.println("Quelle est votre numéro client ?");
    int No_Client = myObj.nextInt();
    System.out.println("Quelle est l'état de la voiture ?/4");
    int etat = myObj.nextInt();
    System.out.println("Quelle est le reste de carburant ?/4");
    int carbu = myObj.nextInt();
    myObj.nextLine();
    System.out.println("Où voulez vous la rendre ?");
    String place = myObj.nextLine();
    fine.requetenewinvoice(carbu, etat, place, No_Client);
   }else if(commande == 3){
    System.out.println("Quelle est votre numéro client ?");
    int No_client = myObj.nextInt();
    System.out.println(fine.getquote(No_client));
   }else if(commande == 4){
    System.out.println("Quelle est votre numéro client ?");
    int No_client = myObj.nextInt();
    myObj.nextLine();
    System.out.println(fine.getinvoice(No_client));
   }
   System.out.println(
     "1. Louer un modèle de voiture\n2. rendre un véhicule\n3. Devis en cours pour un client\n4. Dernière facture\n5. fin");
   commande = myObj.nextInt();
   }

  
  
 }
}
