package service;
import model.*;
import java.sql.*;
import java.util.*;

public class VehiculeDAO {

 private String[] REQUETES = {"select * from Véhicule inner join model"};

public VehiculeList requete(String requete, String argument){
 List<Vehicule> rendu = new ArrayList<Vehicule>();
 DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
 Connection con = data.getConnection();try
 {
  Statement stmt = con.createStatement();
  ResultSet res = stmt.executeQuery(REQUETES[1] + argument + "\"");
  while (res.next()) {
   //Adresse adresse = new Adresse(res.getString(5), res.getString(6), res.getInt(7));
   //rendu.add(new Vehicule(res.getString(2), res.getString(3), res.getString(4), adresse, res.getInt(8)));
  }
 }catch(
 SQLException e)
 {
  System.out.println(e.getMessage());
 }return new VehiculeList(rendu);
 
}
public void requestnew(String registrationNumber, int kilometers,boolean manual, boolean ac, String fuel, boolean inlocation, String identifiant, String modelname, int price, String category, String brand){
 DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
 Connection con = data.getConnection();
 try{
  CallableStatement stmt = con.prepareCall("{call new_vehicule(?,?,?,?,?,?,?,?,?,?,?)}");
  stmt.setString(1, registrationNumber);
  stmt.setInt(2, kilometers);
  stmt.setBoolean(3, manual);
  stmt.setBoolean(4, ac);
  stmt.setString(5, fuel);
  stmt.setBoolean(6, inlocation);
  stmt.setString(7,identifiant);
  stmt.setString(8,modelname);
  stmt.setInt(9,price);
  stmt.setString(10,category);
  stmt.setString(11,brand);
  stmt.execute();
  stmt.close();
  System.out.println("Ajout réussi.");
 }catch(SQLException e){
  System.out.println(e.getMessage());
 }
 data.close();
}

}

