package model;
import java.util.List;

public class VehiculeList {
 List<Vehicule> Vehicules;

 public VehiculeList(List<Vehicule> test){
  this.Vehicules = test;
 }

 public List<Vehicule> getVehicules() {
  return Vehicules;
 }

 @Override
 public String toString() {
  String rendu = "";
  for (Vehicule vehicule : Vehicules) {
   rendu = rendu + vehicule.toString() + "\n";
  }
  return rendu;
 }
}
