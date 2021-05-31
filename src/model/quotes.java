package model;
import java.time.*;

public class quotes {
 private int id;
 private boolean insurance;
 private LocalDate takingDate;
 private LocalDate returnDate;
 private LocalDate subscribing;
 private int announceprice;
 private Client client;
 private Vehicule loue;
 


 public quotes(int id, boolean insurance, LocalDate takingDate, LocalDate returnDate, LocalDate subscribing, int announceprice,Client client, Vehicule voiture){
  this.id = id;
  this.insurance = insurance;
  this.takingDate = takingDate;
  this.returnDate = returnDate;
  this.subscribing = subscribing;
  this.announceprice = announceprice;
  this.client = client;
  this.loue = voiture;
 }

 @Override
 public String toString(){
  return "Devis n°" + String.valueOf(this.id) + ",Signé le "+subscribing.toString() +"\n assurance :" + String.valueOf(this.insurance) + "\n Date début :" + takingDate.toString() + ",\n date de rendu :" + returnDate.toString() + " " 
  +" \nVéhicule :" + this.loue.toString() + "Prix : " + String.valueOf(announceprice) + " \n Client :" + this.client.toString();
 }
}
