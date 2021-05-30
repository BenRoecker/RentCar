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
  return String.valueOf(this.id) + " " + this.Surname + " " + this.Name + " : " + this.Email + this.adresse + " appel :" + String.valueOf(this.PhoneNumber);
 }

}
