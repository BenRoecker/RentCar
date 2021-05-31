package view;
import service.VehiculeDAO;
import java.util.Scanner;

public class VehiculeView {

 public void affichervehicule(){
  Scanner myObj = new Scanner(System.in);
  VehiculeDAO test = new VehiculeDAO();
  //test.requestnew("890098891",0,true,false,"essence",false,"Paris","208",30,"luxe","Peugeot");
  System.out.println("Page Véhicules\n");
  System.out.println("1. Nouveau véhicule\n2. Affichage véhicules\n3. suppression d'un véhicule\n4. Modification d'un véhicule\n5. Modification du prix d'un modèle\n6. fin");
  int commande = myObj.nextInt();
  myObj.nextLine();
  while(commande != 6){
   if(commande == 1){
    System.out.println("Immatriculation :");
    String immat = myObj.nextLine();
    System.out.println("marque :");
    String marque = myObj.nextLine();
    System.out.println("model :");
    String model = (myObj.nextLine()).replace("\n", "");
    System.out.println("kilometrage :");
    int kilometers = myObj.nextInt();
    System.out.println("manual ?(true/false)");
    boolean manual = myObj.nextBoolean();
    System.out.println("climatisation ?(true/false)");
    boolean climatisation = myObj.nextBoolean();
    myObj.nextLine();
    System.out.println("Carburant :");
    String carbu = myObj.nextLine();
    System.out.println("category ? (Luxe/Economique/Confort)");
    String category = myObj.nextLine();
    System.out.println("Prix ?");
    int price = myObj.nextInt();
    myObj.nextLine();
    test.requestnew(immat, kilometers, manual, climatisation, carbu, false, "Paris", model, price, category, marque);
   }else if(commande == 2){
    System.out.println("1. Affichage par marque\n2. recherche par marque\n3. en location");
    int commandenext = myObj.nextInt();
    myObj.nextLine();
    if(commandenext == 1){
     System.out.println(test.requestview(1, "lol"));
    }else if(commandenext == 2){
     System.out.println("Quelle marque voulez trouvé la voiture ?");
     String marque = myObj.nextLine();
     System.out.println(test.requestview(2,marque));
    }else if(commandenext == 3){
     System.out.println(test.requestview(3, "null"));
    }
   }else if(commande == 3){
    System.out.println("Immatriculation de la voiture a supprimé :");
    String commandenext = myObj.nextLine();
    test.requestsupp(commandenext);
   }else if(commande == 4){
    System.out.println("Immatriculation de la voiture a modifié :");
    String immat = myObj.nextLine();
    System.out.println("kilometrage à ajouté :");
    int kilometers = myObj.nextInt();
    System.out.println("en location ?(true/false)");
    boolean inlocation = myObj.nextBoolean();
    myObj.nextLine();
    test.requestmodifvehicule(immat, kilometers, inlocation);
   }else if(commande == 5){
    System.out.println("Le model a modifié :");
    String model = myObj.nextLine();
    System.out.println("prix par jour de la location du modèle :");
    int price = myObj.nextInt();
    test.requestmodifmodel(model, price);
   }
   System.out.println("1. Nouveau véhicule\n2. Affichage véhicules\n3. suppression d'un véhicule\n4. Modification d'un véhicule\n5. Modification du prix d'un modèle\n6. fin");
   commande = myObj.nextInt();
   myObj.nextLine();
  }
  myObj.close();
 }
}
