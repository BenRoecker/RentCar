package view;
import service.*;
import java.util.Scanner;

public class ClientView {
 
 public void afficherclient(){
  Scanner myObj = new Scanner(System.in);
  ClientDAO test = new ClientDAO();
  System.out.println("Page clients\n");
  System.out.println("1. Nouveau client\n2. Affichage clients\n3. Supprimer un client\n4.Modifier les information d'un client\n5. FIN");
  int commande = myObj.nextInt();
  myObj.nextLine();
  while(commande != 5){
   if(commande == 1){
     System.out.println("NOM :");
     String name = myObj.nextLine();
     System.out.println("Prenom :");
     String surname = myObj.nextLine();
     System.out.println("email :");
     String email = myObj.nextLine();
     System.out.println("street :");
     String street = myObj.nextLine();
     System.out.println("city :");
     String city = myObj.nextLine();
     System.out.println("Code postal :");
     String postalcode = myObj.nextLine();
     System.out.println("telephone :");
     String tel = myObj.nextLine();
     test.requetenew("l", name, surname, email, street, city, postalcode, tel);
   }
    else if(commande == 2){
     System.out.println("1. Par ordre alphabétique\n2. recherche par nom\n3. location en cours\n4. loué un véhicule donné");
     int commandenext = myObj.nextInt();
     myObj.nextLine();
     if(commandenext == 1){
      System.out.println("Les clients :");
      System.out.println(test.requetelList(commandenext, "null"));
     }else if(commandenext == 2){
      System.out.println("Quel est ne nom du client ?");
      String searchname = myObj.nextLine();
      System.out.println(test.requetelList(commandenext, searchname));
     }else if(commandenext == 3){
      System.out.println("Les clients :");
      System.out.println(test.requetelList(commandenext, "null"));
     }
    }else if(commande == 3){
     System.out.println("Id du clients à supprimmer :");
     int commandenext = myObj.nextInt();
     myObj.nextLine();
     test.requetesupp(commandenext);
    }else if(commande == 4){
      System.out.println("Id du clients à modifier :");
      int id = myObj.nextInt();
      myObj.nextLine();
      System.out.println("NOM :");
      String name = myObj.nextLine();
      System.out.println("Prenom :");
      String surname = myObj.nextLine();
      System.out.println("email :");
      String email = myObj.nextLine();
      System.out.println("street :");
      String street = myObj.nextLine();
      System.out.println("city :");
      String city = myObj.nextLine();
      System.out.println("Code postal :");
      String postalcode = myObj.nextLine();
      System.out.println("telephone :");
      String tel = myObj.nextLine();
      test.requetemodif(id, name, surname, email, street, city, postalcode, tel);
    }
    System.out.println("1. Nouveau client\n2. Affichage clients\n3. Supprimer un client\n4. Modifier les information d'un client\n5. FIN");
   commande = myObj.nextInt();
   myObj.nextLine();
   }
   
  }
  
  
 }
