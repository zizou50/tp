import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // Demande à l'utilisateur le numéro de port
        System.out.print("Port d'écoute : ");
        try {
            // Lecture du numéro de port saisi par l'utilisateur
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // Gestion des exceptions
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }

        try {
            // Création d'une socket serveur liée au port spécifié
            ServerSocket serverSocket = new ServerSocket(port);

            // Acceptation des connexions entrantes des clients
            Socket socket = serverSocket.accept();

            // Initialisation des flux pour envoyer et recevoir des objets
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Lecture d'un objet chaîne envoyé par le client
            String chaine = (String) input.readObject();
            System.out.println(" reçu : " + chaine);

            // Affichage de l'adresse IP et du numéro de port du client
            System.out.println(" cela vient de : " + socket.getInetAddress() + ":" + socket.getPort());

            // Envoi d'un message d'accusé de réception au client
            output.writeObject(new String("bien reçu"));
            
            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            // Gestion des exceptions
            System.err.println("Erreur : " + e);
        }
    }
}
