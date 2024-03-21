import java.rmi.*;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            InterfaceRMI server = (InterfaceRMI) Naming.lookup("rmi://localhost/Server");
            String input = "Hello, world!";
            System.out.println("Chaîne envoyée au serveur: " + input);
            String result = server.convertToUpperCase(input);
            System.out.println("Résultat reçu du serveur: " + result);
        } catch (Exception e) {
            System.out.println("Erreur: " + e);
        }
    }
}
