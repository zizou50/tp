package ClientTCP;

import java.net.*;
import Component.Voiture;
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

    public void CreateVoiture(String type,String model,int carburant){
        try{
            if(this.socket!=null){
                Voiture v = new Voiture(type,model);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Map<String,Object> Data = new HashMap<>();
                Data.put("voiture", v);
                Data.put("Carburant",carburant);
                output.writeObject(Data); 
                Object response = input.readObject();
                Voiture Result = (response instanceof Voiture) ? (Voiture) response : null;
                System.out.println(Result.toString());              
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
