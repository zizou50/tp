    import java.io.IOException;
    import java.io.ByteArrayInputStream;
    import java.io.ObjectInputStream;
    import java.net.*;
    import java.util.Map;
    public class Server {
    
    final static  int DEFAULT_PORT=4500;//pour le port par défault si on spécifie pas
    
    private int port ;
    private DatagramSocket socket;
    
    Server(){
        this.port=DEFAULT_PORT; 
    }

    Server(int _port){
        port = _port;
    }

    public void launchServer(){
        
        try
        {
            System.out.println("serveur en marche  :   👌");
            this.socket=new DatagramSocket(this.port);///creation de la socket
            byte[] data =new byte[1024];//creation du tableau pour contenir les données
            DatagramPacket packet = new DatagramPacket(data,data.length);//creation du pakcet de reception
            System.out.println("en attente de la reponse 🫷🏾");
            socket.receive(packet);///reception du packet
            Map<String,Object> input= deserializeMap(packet.getData());
            voiture voit = (voiture)input.get("voiture");
            int carburant = (Integer)input.get("carburant");
            System.out.println("Reponse Recueee  "+input);
            voit.setCarburant(carburant);
        }
        catch(IOException e){
            System.out.println("error happend in server launching ");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String,Object>deserializeMap(byte[] data ){
        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bis);//to desserialize from the byte ARRAYY TYPPE
            return (Map<String, Object>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            // Handle deserialization exception
            e.printStackTrace();
            return null;
        }
    } 

    public static void main(String[] args) {
        Server server = new Server(4200);
        server.launchServer();
    }
}
