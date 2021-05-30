package service;
import java.sql.*;

public class DataAccess {
 private Connection con;

 public DataAccess(String url, String login, String mdp) {
  try {
   con = DriverManager.getConnection(url, login, mdp);
  } catch (Exception e) {
   System.out.println(e);
  }
 }

 public Connection getConnection() {
  return con;
 }

 public void close() {
  try {
   con.close();
  } catch (SQLException e) {
   System.out.println(e.getMessage());
  }
 }

}
