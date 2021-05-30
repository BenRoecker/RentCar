package model;
import java.time.*;

public class quotes {
 private int id;
 private boolean insurance;
 private LocalDate takingDate;
 private LocalDate returnDate;
 private LocalDate subscribing;
 private int announceprice;
 private int no_client;
 private String immat;
 private String modele;

 public quotes(int id, boolean insurance, LocalDate takingDate, LocalDate returnDate, LocalDate subscribing, int announceprice, int No_client){
  this.id = id;
  this.insurance = insurance;
  this.takingDate = takingDate;
  this.returnDate = returnDate;
  this.subscribing = subscribing;
  this.announceprice = announceprice;
  this.no_client = No_client;
 }

 @Override
 public String toString(){
  return String.valueOf(this.id) + " , insurance :" + String.valueOf(this.insurance) + "\n Date début :" + takingDate.toString() + ",\n date de rendu :" + returnDate.toString() + " " 
  + this.immat + " "+ this.modele + "\n Prix : " + this.announceprice;
 }
}
