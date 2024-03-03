package Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
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

    public void sendMessage(String message){
        try{
            if(this.socket!=null){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                output.writeObject(message);  
                Object response = input.readObject();
                System.out.println(response.toString());              
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
