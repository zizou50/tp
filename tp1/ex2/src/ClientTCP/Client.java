package ClientTCP;

import java.net.*;
import Component.Voiture;
import Data.Personne;

import java.util.*;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.io.IOException;

public class Client {
    private InetAddress host;
    private Integer port;
    private Socket socket;
   
    public Client(String Host,int port){
        try{

            this.host= InetAddress.getByName(Host);
            this.port=port; 
            this.socket = new Socket(this.host,this.port);
            System.out.println("CLIENT CREATED SUCCESSFULLY");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void AddPersonne(int age ,String Name){
        try{
            if(this.socket!=null){
                Voiture v = new Voiture(type,model);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Personne pers= new Personne(age,Name);
                output.writeObject(pers); 
                Integer response = (Integer)(input.readObject());
                System.out.println("ID is   :  "+response);              
            }
            else throw  new InternalError ("Error ! message not send");

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void clientDown() throws IOException{
        if (this.socket!=null){
            this.socket.close();
        }
    }
}
