package model;
import java.util.List;

public class ClientList {

 List<Client> Clients;

 public ClientList(List<Client> test){
  this.Clients = test;
 }

 public List<Client> getClients(){
  return Clients;
 }

 @Override
 public String toString(){
  String rendu = "";
  for(Client client : Clients){
   rendu = rendu + client.toString() + "\n";
  }
  return rendu;
 }

}
