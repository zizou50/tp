import java.io.IOException;
import java.net.*;
import java.util.Date;
public class Serveur {  
    private static int port  = 1250;
    private static void LaunchSever(){
        DatagramSocket socketCopy =null;
        try{
            DatagramSocket socket = new DatagramSocket(port);
            socketCopy=socket;
            while (true) {
                byte[] data= new byte[1024];
                DatagramPacket p =new DatagramPacket(data,data.length);
                socket.receive(p);
                String date= new Date().toString();
                int port = p.getPort();
                InetAddress add = p.getAddress();
                byte[] out = date.getBytes();
                DatagramPacket packetOut = new DatagramPacket(out,out.length,add, port);
                socket.send(packetOut); 
                System.out.println("date et heure envoyé avec succées");               
            }

        }
        catch(IOException e ){
            e.printStackTrace();
        }
        finally{
            if(socketCopy!=null){
                socketCopy.close();
            }
        }
    }
    

    public static void main(String[] args) throws Exception {
        Serveur.LaunchSever() ;
    }
}
