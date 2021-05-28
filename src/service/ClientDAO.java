package service;
import model.*;
import java.sql.*;
import java.util.*;

public class ClientDAO {

 private String[] REQUETES = {"SELECT * FROM rentcar.clients natural join rentcar.contact_information order by contact_information.name",
 "SELECT * FROM rentcar.clients Where clients.Nom_client = \""};


 public ClientList requete(String requete, String argument){
  List<Client> rendu = new ArrayList<Client>();
  DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  try{
   Statement stmt = con.createStatement();
   ResultSet res = stmt.executeQuery(REQUETES[0]);
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
