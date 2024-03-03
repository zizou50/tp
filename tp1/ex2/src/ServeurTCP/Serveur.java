package ServeurTCP;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import Component.Voiture;

public class Serveur implements Runnable{
    int port;
    ServerSocket socket;

    public Serveur(int _port){
        try{
            port = _port;
            this.socket= new ServerSocket(port);

        }
        catch(IOException e){
            System.out.println("Errror creating the server");
        }
    }

    @Override 
    public void run(){
        if(this.socket!=null){
            try{
                Voiture voit = null;int carburant=0;
                Socket s = this.socket.accept();
                ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                Object query = input.readObject();
                if(query instanceof Map){
                    Map<?,?> map = (Map<?,?>) query;
                    if(map.containsKey("voiture"))
                        voit = (Voiture) map.get("voiture");
                    if(map.containsKey("Carburant"))
                        carburant = (Integer) map.get("Carburant");
                    if(voit !=null)
                    {
                        voit.setCarburant(carburant);
                        output.writeObject(voit);}
                }
                

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
            

    }



    
}
