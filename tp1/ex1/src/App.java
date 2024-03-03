
import Serveur.*;
import Client.Client;

public class App {
    public static void main(String[] args) throws Exception {
            System.out.println("------Satrting the Test -------");
            Serveur server = new Serveur(8000);
            Client client = new Client("127.0.0.1",8000);
            Thread serverInstance = new Thread(server);
            serverInstance.start();
            client.sendMessage("message to server");
            server.serveurDown();
            client.clientDown();
        }
}
