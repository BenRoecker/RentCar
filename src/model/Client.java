package model;

public class Client {
 private int id;
 private String Name;
 private String Surname;
 private String Email;
 private Adresse adresse;
 private int PhoneNumber;
 
 public Client(int id,String Name, String Surname, String Email, Adresse adresse, int PhoneNumber) {
  this.id = id;
  this.adresse = adresse;
  this.Name = Name;
  this.Surname = Surname;
  this.Email = Email;
  this.PhoneNumber = PhoneNumber;
 }

 @Override
 public String toString() {
  return "Client n°" + String.valueOf(this.id) + "\n" + this.Surname + " " + this.Name + "\nEmail address :" + this.Email +"\nAdresse :"+ this.adresse + "\nN° de téléphone :" + String.valueOf(this.PhoneNumber);
 }

}
