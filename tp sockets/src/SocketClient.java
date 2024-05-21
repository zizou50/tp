import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

        // Demande à l'utilisateur le nom du serveur
        System.out.print("Nom du serveur : ");
        host = keyb.next();

        // Demande à l'utilisateur le port du serveur
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // Gestion si le second paramètre n'est pas un entier
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            // Résolution du nom d'hôte en adresse IP
            InetAddress adr = InetAddress.getByName(host);

            // Établissement de la connexion avec le serveur
            Socket socket = new Socket(adr,port);

            // Initialisation des flux pour envoyer et recevoir des objets
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Envoi d'une chaîne de caractères au serveur
            output.writeObject(new String("ma première socket"));

            // Lecture d'une chaîne de caractères envoyée par le serveur
            String chaine = (String) input.readObject();
            System.out.println(" reçu du serveur : " + chaine);
        } catch (Exception e) {
            // Gestion des exceptions
            System.err.println("Erreur : " + e);
        }
    }
}
