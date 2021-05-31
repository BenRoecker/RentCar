package service;
import model.*;
import java.time.*;
import java.sql.*;

public class LocationDAO {
 
 private String[] REQUETE = {"SELECT * from quote where No_client = \"","select * from Véhicule inner join Model on Model.modelName = Véhicule.modelname where RegistrationNumber = \"",
"SELECT * FROM rentcar.contact_information natural join clients Where No_Client = \"",
   "SELECT * from quote where quote_id = \"","select * from invoice where No_Client =\""};

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


public quotes getquote(int No_Client){
 DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
 Connection con = data.getConnection();
 quotes lol = null;
 try{
  Statement stmt = con.createStatement();
  
  ResultSet res = stmt.executeQuery(REQUETE[0] + No_Client + "\"order by subscribingDate DESC limit 1;");
  while (res.next()) {
   int quote_id = res.getInt(1);
   boolean insurance = res.getBoolean(2);
   LocalDate startingdate =  res.getDate(3).toLocalDate();
   LocalDate returndate = res.getDate(4).toLocalDate();
   LocalDate subscribingdate = res.getDate(5).toLocalDate();
   int announceprice = res.getInt(6);
   String registrationNumber = res.getString(7);
    Statement stmt2 = con.createStatement();
    ResultSet res2 = stmt2.executeQuery(REQUETE[1] +registrationNumber + "\";");
    while (res2.next()) {
       Vehicule rendu = new Vehicule(res2.getString(1), res2.getString(12), res2.getString(8), res2.getInt(2), res2.getBoolean(3),
       res2.getBoolean(4), res2.getString(5), res2.getString(11));
       Statement stmt3 = con.createStatement();
       ResultSet res3 = stmt3.executeQuery(REQUETE[2] + No_Client + "\";");
       while(res3.next()){
          Adresse adresse = new Adresse(res3.getString(5), res3.getString(6), res3.getInt(7));
          Client client = new Client(res3.getInt(9), res3.getString(2), res3.getString(3), res3.getString(4), adresse, res3.getInt(8));
          lol =  new quotes(quote_id, insurance,  startingdate, returndate, subscribingdate, announceprice, client, rendu);
        }
    }
  }
 }catch(SQLException e){
  System.out.println(e.getMessage());
 }
 return lol;
}

public invoice getinvoice(int No_client){
  DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  invoice rendu = null;
  try{
   Statement stmt = con.createStatement();
   ResultSet res = stmt.executeQuery(REQUETE[4] + No_client + "\"order by returnDate DESC limit 1;");
   while(res.next()){
     int idInvoice = res.getInt(1);
    int fuelConsommation = res.getInt(2);
    int VehiculeState = res.getInt(3);
    LocalDate returnDate = res.getDate(4).toLocalDate();
    rendu = new invoice(getquote(No_client), returnDate, VehiculeState, fuelConsommation, idInvoice);
   }
  }catch(SQLException e){
   System.out.println(e.getMessage());
  }
  return rendu;
}
}
