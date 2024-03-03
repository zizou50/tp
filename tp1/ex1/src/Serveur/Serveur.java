package Serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur implements Runnable{
    private int port = 0;
    private ServerSocket socket;


    public Serveur(int port){
       try {
        this.port=port;
        this.socket = new ServerSocket(this.port);
        System.out.println("SERVER CREATED SUCCESSFULLY");
         }
       catch(IOException e){
        e.printStackTrace();
        }
    }


    public void run(){
        try{
            if(this.socket!=null){
                    Socket s = socket.accept();
                    ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                    String result = (String) input.readObject();
                    System.out.println("Data Received from : " +s.getInetAddress()+" On port : "+s.getPort()+"  :  " +result);
                    output.writeObject("ACknowledgment : TRUE");
                
        }
    }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void serveurDown() throws IOException{
        if (this.socket!=null){
            this.socket.close();
        }
    }
}
