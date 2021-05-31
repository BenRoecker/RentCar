package service;
import model.*;
import java.sql.*;
import java.util.*;

public class ClientDAO {

 private String[] REQUETES = {"select * from contact_information natural join clients order by name;",
 "SELECT * FROM rentcar.contact_information natural join clients Where contact_information.name = \"",
  "SELECT * FROM rentcar.contact_information natural join clients where No_Client not in (select No_client from quote);"};

  private String url;
  public ClientDAO(String url){
    this.url = url;
  }

 public ClientList requetelList(int requete, String argument){
  List<Client> rendu = new ArrayList<Client>();
  DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  try{
   PreparedStatement stmt;
   ResultSet res;
   if(requete == 1){
     stmt = con.prepareStatement(REQUETES[0]);
     res = stmt.executeQuery();
     while (res.next()) {
      Adresse adresse = new Adresse(res.getString(5), res.getString(6), res.getInt(7));
      rendu.add(new Client(res.getInt(9),res.getString(2), res.getString(3), res.getString(4), adresse, res.getInt(8)));
     }
   }else if(requete == 2){
    stmt = con.prepareStatement(REQUETES[1]+argument+"\"");
     res = stmt.executeQuery();
     while (res.next()) {
      Adresse adresse = new Adresse(res.getString(5), res.getString(6), res.getInt(7));
      rendu.add(new Client(res.getInt(9),res.getString(2), res.getString(3), res.getString(4), adresse, res.getInt(8)));
     }
   }else if(requete == 3){
    stmt = con.prepareStatement(REQUETES[2]);
     res = stmt.executeQuery();
     while (res.next()) {
      Adresse adresse = new Adresse(res.getString(5), res.getString(6), res.getInt(7));
      rendu.add(new Client(res.getInt(9),res.getString(2), res.getString(3), res.getString(4), adresse, res.getInt(8)));
     }
   }
  }catch(SQLException e){
   System.out.println(e.getMessage());
  }
  data.close();
  return new ClientList(rendu);
 }


 public void requetenew(String requete, String name, String surname, String email, String street, String city, String postalCode, String tel) {
  DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  try{
   CallableStatement stmt = con.prepareCall("{call new_client(?,?,?,?,?,?,?)}");
  stmt.setString(1, name);
  stmt.setString(2, surname);
  stmt.setString(3, email);
  stmt.setString(4, street);
  stmt.setString(5, city);
  stmt.setString(6, postalCode);
  stmt.setString(7, tel);
  stmt.execute();
  stmt.close();
  System.out.println("Ajout réussi.");
  }catch(SQLException e){
   System.out.println(e.getMessage());
  }
  data.close();
 }

 public void requetesupp(int id){
  DataAccess data = new DataAccess(this.url, "Administrateur", "Administrateur");
  Connection con = data.getConnection();
  try{
   CallableStatement stmt = con.prepareCall("{call supp_client(?)}");
   stmt.setInt(1, id);
   stmt.execute();
   stmt.close();
   System.out.println("suppression réussi.");
  }catch (SQLException e) {
   System.out.println(e.getMessage());
  }
  data.close();
 }

 public void requetemodif(int id,String name, String surname, String email, String street, String city,String postalCode, String tel) {
   DataAccess data = new DataAccess("jdbc:mysql://localhost:3306/rentcar", "Administrateur", "Administrateur");
   Connection con = data.getConnection();
   try {
     CallableStatement stmt = con.prepareCall("{call modif_client(?,?,?,?,?,?,?,?)}");
     stmt.setInt(1,id);
     stmt.setString(2, name);
     stmt.setString(3, surname);
     stmt.setString(4, email);
     stmt.setString(5, street);
     stmt.setString(6, city);
     stmt.setString(7, postalCode);
     stmt.setString(8, tel);
     stmt.execute();
     stmt.close();
     System.out.println("Modification réussie.");
   } catch (SQLException e) {
     System.out.println(e.getMessage());
   }
   data.close();
 }

}

