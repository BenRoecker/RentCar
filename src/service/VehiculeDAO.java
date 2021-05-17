package service;
import model.*;
import java.sql.*;
import java.util.*;

public class VehiculeDAO {

 private String[] REQUETES = {"select * from vehicule"};

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
 
}}

