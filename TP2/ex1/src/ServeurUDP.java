import java.net.*;
import java.util.Scanner;
public class ServeurUDP {
public static void main(String argv[]) {
int port = 0;
Scanner keyb = new Scanner(System.in);
try {
// on récupère le paramètre : port d'écoute
System.out.println("port d'écoute : ");
port = keyb.nextInt();
keyb.close();
DatagramPacket packet;
// création d'une socket liée au port précisé en paramètre
DatagramSocket socket = new DatagramSocket(port);
// tableau de 15 octets qui contiendra les données reçues
byte[] data = new byte[15];
// création d'un paquet en utilisant le tableau d'octets
packet = new DatagramPacket(data, data.length);

// attente de la réception d'un paquet. Le paquet reçu est placé
// dans packet et ses données dans data.

socket.receive(packet);

// récupération et affichage des données (une chaîne de caractères)

String chaine = new String(packet.getData(), 0,
packet.getLength());
System.out.println(" recu : " + chaine);
System.out.println(" ca vient de : " + packet.getAddress() + ":" +

packet.getPort());

// on met une nouvelle donnée dans le paquet
// (qui contient donc le couple @IP/port de la socket coté client)
byte[] reponse = (new String("bien recu")).getBytes();
packet.setData(reponse);
packet.setLength(reponse.length);
// on envoie le paquet au client
socket.send(packet);
socket.close();
} catch (Exception e) {
System.err.println("Erreur : " + e);
}
}
}