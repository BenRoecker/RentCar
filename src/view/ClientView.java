package view;
import service.*;


public class ClientView {
 
 public void afficherclient(){
  ClientDAO test = new ClientDAO();
  System.out.println(test.requete("lol", "DOGG").toString());
 }
}
