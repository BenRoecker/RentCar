package service;
import model.*;
import java.sql.*;
import java.util.*;

public class ClientDAO {

 private String Alphabetic = "SELECT * FROM rentcar.clients order by clients.Prenom_client";


 public ClientList requete(String requete, String argument){
  List<Client> rendu = new ArrayList<Client>();
  DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  try{
   Statement stmt = con.createStatement();
   ResultSet res = stmt.executeQuery(Alphabetic);
   while(res.next()){
    Adresse adresse = new Adresse(res.getString(5), res.getString(6), res.getInt(7));
    rendu.add(new Client(res.getString(2), res.getString(3), res.getString(4), adresse, res.getInt(8)));
   }
  }catch(SQLException e){
   System.out.println(e.getMessage());
 
  }
  return new ClientList(rendu);
  
 }
 
}
