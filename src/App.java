import view.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ClientView test = new ClientView();
        test.afficherclient();
        VehiculeView newtest = new VehiculeView();
        newtest.affichervehicule();
        LocationView anothertest = new LocationView();
        anothertest.afficher();
    }
}
