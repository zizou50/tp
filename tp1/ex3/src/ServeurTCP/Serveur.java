package ServeurTCP;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import Data.Personne;

public class Serveur implements Runnable{
    int port;
    ServerSocket socket;
    ArrayList<Personne> Personnes = new ArrayList<>();
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
                Socket s = this.socket.accept();
                ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                Object query = input.readObject();
                if(query instanceof Personne){
                    Personne pers = (Personne)  query;
                    if(!this.Personnes.contains(pers))
                           this.Personnes.add(pers);
                    output.writeObject(Personnes.indexOf(pers));
                }
                

            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
            

    }



    
}
