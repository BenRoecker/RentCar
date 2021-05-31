package model;
import java.time.*;

public class invoice {
 private quotes quote;
 private LocalDate returndate;
 private int VehiculeState;
 private int fuelConsommation;
 private int idInvoice;

 public invoice(quotes quote, LocalDate returndate,int VehiculeState, int fuelConsommation, int idInvoice){
  this.quote = quote;
  this.returndate = returndate;
  this.VehiculeState = VehiculeState;
  this.fuelConsommation = fuelConsommation;
  this.idInvoice = idInvoice;
 }

 @Override
 public String toString(){
  return "Devis : " + String.valueOf(this.idInvoice) + ", Dta de rendu réelle " + this.returndate.toString() + "\n Consommation d'essence :"
    + String.valueOf(this.fuelConsommation) + "/4\n Etat du véhicule :" + String.valueOf(this.VehiculeState) + " \n"
    + quote.toString(); 
 }

 
}
