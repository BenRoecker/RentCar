package model;

public class Client {
 private String Name;
 private String Surname;
 private String Email;
 private Adresse adresse;
 private int PhoneNumber;
 
 public Client(String Name, String Surname, String Email, Adresse adresse, int PhoneNumber) {
  this.adresse = adresse;
  this.Name = Name;
  this.Surname = Surname;
  this.Email = Email;
  this.PhoneNumber = PhoneNumber;
 }

 @Override
 public String toString() {
  return this.Surname + " " + this.Name + " : " + this.Email + this.adresse + " appel :" + String.valueOf(this.PhoneNumber);
 }

}
