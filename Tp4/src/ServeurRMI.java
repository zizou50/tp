import java.rmi.*;
import java.rmi.server.*;

public class ServeurRMI extends UnicastRemoteObject implements InterfaceRMI {
    public ServeurRMI() throws RemoteException {
        super();
    }

    @Override
    public String convertToUpperCase(String str) throws RemoteException {
        return str.toUpperCase();
    }

    public static void main(String[] args) {
        try {
            ServeurRMI server = new ServeurRMI();
            Naming.rebind("Server", server);
            System.out.println("Serveur RMI démarré...");
        } catch (Exception e) {
            System.out.println("Erreur: " + e);
        }
    }
}
