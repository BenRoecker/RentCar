package service;
import model.Vehicule;
import model.quotes;
import java.time.*;
import java.sql.*;

public class LocationDAO {
 
 public void requetenewquote(boolean insurance,LocalDate startingDate,LocalDate returnDate, int No_Client, String modelName, String identifiant){
  DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  int id_devis = 0;
 try{
  int days = Period.between(startingDate,returnDate).getDays();
  CallableStatement stmt = con.prepareCall("{call new_quote(?,?,?,?,?,?,?,?)}");
  stmt.setBoolean(1, insurance);
  stmt.setDate(2, Date.valueOf(startingDate));
  stmt.setDate(3, Date.valueOf(returnDate));
  stmt.setDate(4,Date.valueOf(LocalDate.now()));
  stmt.setInt(6, No_Client);
  stmt.setInt(5, days);
  stmt.setString(7,modelName);
  stmt.setString(8, identifiant);
  stmt.execute();
  stmt.close();
 }catch(SQLException e){
  System.out.println(e.getMessage());
 }System.out.println(id_devis);
}
public void requetenewinvoice(int fuelConsommation,int VehiculeState, String identifiant, int No_Client){
 DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
  Connection con = data.getConnection();
 try{
  CallableStatement stmt = con.prepareCall("{call new_facture(?,?,?,?,?)}");
  stmt.setInt(1, fuelConsommation);
  stmt.setInt(2, VehiculeState);
  stmt.setDate(3, Date.valueOf(LocalDate.now()));
  stmt.setString(4, identifiant);
  stmt.setInt(5, No_Client);
  stmt.execute();
  stmt.close();
 }catch(SQLException e){
  System.out.println(e.getMessage());
 }
}
}
