package service;
import model.*;
import java.sql.*;
import java.util.*;

public class VehiculeDAO {

 private String[] REQUETES = {"select * from Véhicule inner join Model on Model.modelName = Véhicule.modelname order by brand;",
 "select * from Véhicule inner join Model on Model.modelName = Véhicule.modelname where brand =\"",
 "select * from Véhicule inner join Model on Model.modelName = Véhicule.modelname where inlocation is true"};

 private String url;
 public VehiculeDAO(String url){
    this.url = url;
 }

public VehiculeList requestview(int requete, String argument){
 List<Vehicule> rendu = new ArrayList<Vehicule>();
 DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
 Connection con = data.getConnection();
 if(requete == 1){
  try {
   Statement stmt = con.createStatement();
   ResultSet res = stmt.executeQuery(REQUETES[0]);
   while (res.next()) {
    rendu.add(new Vehicule(res.getString(1), res.getString(12), res.getString(8), res.getInt(2), res.getBoolean(3),
      res.getBoolean(4), res.getString(5), res.getString(11)));
   }
  } catch (SQLException e) {
   System.out.println(e.getMessage());
  }
 }else if(requete == 2){
  try {
   Statement stmt = con.createStatement();
   ResultSet res = stmt.executeQuery(REQUETES[1]+ argument + "\";");
   while (res.next()) {
    rendu.add(new Vehicule(res.getString(1), res.getString(12), res.getString(8), res.getInt(2), res.getBoolean(3),
      res.getBoolean(4), res.getString(5), res.getString(11)));
   }
  } catch (SQLException e) {
   System.out.println(e.getMessage());
  }
 }else if(requete == 3){
  try {
   Statement stmt = con.createStatement();
   ResultSet res = stmt.executeQuery(REQUETES[2]);
   while (res.next()) {
    rendu.add(new Vehicule(res.getString(1), res.getString(12), res.getString(8), res.getInt(2), res.getBoolean(3),
      res.getBoolean(4), res.getString(5), res.getString(11)));
   }
  } catch (SQLException e) {
   System.out.println(e.getMessage());
  }
 }
 return new VehiculeList(rendu);
}


public void requestnew(String registrationNumber, int kilometers,boolean manual, boolean ac, String fuel, boolean inlocation, String identifiant, String modelname, int price, String category, String brand){
 DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
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

public void requestsupp(String immat){
 DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
 Connection con = data.getConnection();
 try{
  CallableStatement stmt = con.prepareCall("{call supp_vehicule(?)}");
  stmt.setString(1, immat);
  stmt.execute();
  stmt.close();
  System.out.println("suppression réussi.");
 }catch (SQLException e) {
  System.out.println(e.getMessage());
 }
 data.close();
}

public void requestmodifvehicule(String immat, int kilometers, boolean inlocation){
 DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
 Connection con = data.getConnection();
 try{
  CallableStatement stmt = con.prepareCall("{call modif_vehicule(?,?,?)}");
  stmt.setString(1,immat);
  stmt.setInt(2, kilometers);
  stmt.setBoolean(3, inlocation);
  stmt.execute();
   stmt.close();
   System.out.println("Modification réussite.");
}catch (SQLException e) {
   System.out.println(e.getMessage());
  }
  data.close();

}

public void requestmodifmodel(String ModelName, int price){
 DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
 Connection con = data.getConnection();
 try{
  CallableStatement stmt = con.prepareCall("{call modif_model(?,?)}");
  stmt.setString(1,ModelName);
  stmt.setInt(2,price);
  stmt.execute();
   stmt.close();
   System.out.println("Modification réussite.");
}catch(SQLException e)
{
 System.out.println(e.getMessage());
}data.close();
}
}

