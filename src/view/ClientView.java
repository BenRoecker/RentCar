package view;
import service.*;


public class ClientView {
 
 public void afficherclient(){
  ClientDAO test = new ClientDAO();
  System.out.println(test.requetelList("lol", "DOGG").toString());
  test.requeteinput("lol", "lol");
 }
}
