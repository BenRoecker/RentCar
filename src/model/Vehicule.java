package model;

public class Vehicule {
 
 private int immat;
 private String marque;
 private String model;
 private int kilometers;
 private boolean manual;
 private boolean climatisation;
 private String carbu;
 private String category;

 public Vehicule(int immat,String marque,String model,int kilometers,boolean manual,boolean climatisation,String carbu,String category){
  this.immat = immat;
  this.marque = marque;
  this.model = model;
  this.kilometers = kilometers;
  this.manual = manual;
  this.climatisation = climatisation;
  this.carbu = carbu;
  this.category = category;
 }

 @Override
 public String toString(){
  return marque + " " + model;
 }

}
