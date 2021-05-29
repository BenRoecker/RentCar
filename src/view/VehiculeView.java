package view;
import service.VehiculeDAO;
public class VehiculeView {

 public void affichervehicule(){
  VehiculeDAO test = new VehiculeDAO();
  test.requestnew("890098891",0,true,false,"essence",false,"Paris","208",30,"luxe","Peugeot");
 }
}
