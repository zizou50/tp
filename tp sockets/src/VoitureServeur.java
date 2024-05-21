/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fares
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class VoitureServeur {
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

            // Lecture d'un objet voiture envoyé par le client
            Voiture voitureRecue = (Voiture) input.readObject(); // Lecture de l'objet voiture envoyé par le client
            System.out.println("Voiture reçue : " + voitureRecue.getModel() + " " + voitureRecue.getType());
            // Set carburant
            voitureRecue.setCarburant(100);
            // Affichage de l'adresse IP et du numéro de port du client
            System.out.println(" cela vient de : " + socket.getInetAddress() + ":" + socket.getPort());

            // Envoi d'un message d'accusé de réception au client
            output.writeObject(voitureRecue);
            output.flush();
            input.close();
            output.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            // Gestion des exceptions
            System.err.println("Erreur : " + e);
        }
    }
}
