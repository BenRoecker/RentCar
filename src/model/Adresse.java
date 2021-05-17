package model;

public class Adresse {
  private String Street;
  private String City;
  private int PostalCode;

  public Adresse(String Street, String City, int PostalCode) {
   this.Street = Street;
   this.City = City;
   this.PostalCode = PostalCode;
  }

  public String getStreet() {
   return this.Street;
  }

  public String getCity() {
   return this.City;
  }

  public int getPostalCode() {
   return this.PostalCode;
  }

  public void setStreet(String street) {
   this.Street = street;
  }

  public void setCity(String City) {
   this.City = City;
  }

  public void setPostalCode(int postalCode) {
   this.PostalCode = postalCode;
  }

  @Override
  public String toString(){
   return " Adresse  : rue " + this.Street + ", " + this.City + ", "+ String.valueOf(this.PostalCode);
  }

}
