package view;
import java.time.LocalDate;

import service.LocationDAO;
public class LocationView {
 
 public void afficher(){
  LocationDAO fine = new LocationDAO();
  fine.requetenewquote(false, LocalDate.of(2021,6,10), LocalDate.of(2021,6,12), 1, "208", "Paris");
  fine.requetenewinvoice(3, 4, "Paris", 1);
 }
}
