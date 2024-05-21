import java.io.*;
import java.net.*;
import java.util.Scanner;

class VoitureClient {
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

             Voiture maVoiture = new Voiture("SUV", "Toyota");
            output.writeObject(maVoiture); // Envoi de l'objet voiture au serveur
            output.flush();

            Voiture voitureModifiee = (Voiture) input.readObject(); // Réception de l'objet modifié du serveur
            System.out.println("Voiture modifiée reçue : " + voitureModifiee.getModel() + " " + voitureModifiee.getType() + " avec " + voitureModifiee.getCarburant() + " litres de carburant");

            input.close();
            output.close();
            socket.close();
        }
        catch(Exception e){
            // Gestion des exceptions
            System.err.println("Erreur : " + e);
        }
    }
}
