import java.io.IOException; 
import java.net.*;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import java.util.Map;
public class Client {

final static int DEFAULT_PORT = 4500; // Port par défaut si non spécifié
private int port;
private DatagramSocket socket;
private InetAddress adr ;

Client() {
    this.port = DEFAULT_PORT;
    try{
        this.adr = InetAddress.getByName("localhost");
    }
    catch(UnknownHostException e){
        System.out.println("error creating the host");
    }
    
}

Client(int _port,String host) {
    port = _port; 
    try{
        this.adr = InetAddress.getByName(host);
    }
    catch(UnknownHostException e){
        System.out.println("error creating the host");
    }
}

public void launchClient(String _type, String _model,int c) {
    try {
        System.out.println("client créé");
        this.socket = new DatagramSocket(); // Création de la socket
        voiture voiture = new voiture(_type, _model);
        Map<String, Object> dataToSend = new HashMap<>();
        dataToSend.put("voiture", voiture);
        dataToSend.put("carburant", c);
        byte[] data =serializeMap(dataToSend)  ;// Données   à envoyer
        DatagramPacket packet = new DatagramPacket(data, data.length, this.adr, this.port); // Création du paquet de données
        System.out.println("data envoyé au serveur : "+packet.getAddress() + " données  :  "+dataToSend);
        this.socket.send(packet);
        this.socket.close();
    } catch (IOException e) {
        System.out.println("Erreur lors du lancement du client");
        e.printStackTrace();
    }
}

public static byte[] serializeMap(Map<String, Object> map) {
    try {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();//creation d'un ouptut stream pouvant creer des byte arrays
        ObjectOutputStream oos = new ObjectOutputStream(bos);//creation d'un ObjectOutputStream capable de faire la seriablisation dune map
        oos.writeObject(map);//ligne50 et ligne 60 pour la serialissation de la map et l'envoie pourle byteARRAYOutputStream
        oos.flush();
        return bos.toByteArray();//lecture des données
    } catch (IOException e) {
        // exception pour la serialisation
        e.printStackTrace();
        return null;
    }
}

public static void main(String args []){
    Client cl =new Client(4200,"localhost");
    cl.launchClient("toyota", "jaguar", 400);

}
}