package view;
import service.VehiculeDAO;
import java.util.Scanner;

public class VehiculeView {

 public void affichervehicule(){
  Scanner myObj = new Scanner(System.in);
  VehiculeDAO test = new VehiculeDAO();
  //test.requestnew("890098891",0,true,false,"essence",false,"Paris","208",30,"luxe","Peugeot");
  System.out.println("Page Véhicules\n");
  System.out.println("1. Nouveau véhicule\n2. Affichage véhicules\n3. fin");
  int commande = myObj.nextInt();
  myObj.nextLine();
  while(commande != 3){
   if(commande == 1){
    System.out.println("Immatriculation :");
    String immat = myObj.nextLine();
    System.out.println("marque :");
    String marque = myObj.nextLine();
    System.out.println("model :");
    String model = myObj.nextLine();
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
    System.out.println(test.requestview(1, "lol"));
   }
   System.out.println("1. Nouveau véhicule\n2. Affichage véhicules\n3. fin");
   commande = myObj.nextInt();
   myObj.nextLine();
  }
 }
}
