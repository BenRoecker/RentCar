package service;
import model.*;
import java.sql.*;
import java.util.*;

public class ClientDAO {

 private String[] REQUETES = {"SELECT * FROM rentcar.clients natural join rentcar.contact_information order by contact_information.name",
 "SELECT * FROM rentcar.clients natural join contact_information Where contact_information.name = ?"};


 public ClientList requetelList(String requete, String argument){
  List<Client> rendu = new ArrayList<Client>();
  DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  try{
   PreparedStatement stmt = con.prepareStatement(REQUETES[1]);
   stmt.setString(1, "Dog");
   ResultSet res = stmt.executeQuery();
   while(res.next()){
    Adresse adresse = new Adresse(res.getString(5), res.getString(6), res.getInt(7));
    rendu.add(new Client(res.getString(2), res.getString(3), res.getString(4), adresse, res.getInt(8)));
   }
  }catch(SQLException e){
   System.out.println(e.getMessage());
  }
  return new ClientList(rendu);
 }


 public void requeteinput(String requete, String argument) {
  DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  try{
   CallableStatement stmt = con.prepareCall("{create_contact_information(?,?,?,?,?,?,?)}");
  stmt.setString(1, "Roecker");
  stmt.setString(2, "Benjamin");
  stmt.setString(3, "benjamin.roecker@gmail.com");
  stmt.setString(4, "Molière");
  stmt.setString(5, "Saint-germain-en-laye");
  stmt.setString(6, "78100");
  stmt.setString(7, "0635103616");
  stmt.execute();
  stmt.close();
  }catch(SQLException e){
   System.out.println(e.getMessage());
  }
  
  
 }

}

