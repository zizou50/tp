
import ServeurTCP.*;
import ClientTCP.*;
public class App {
    public static void main(String[] args) throws Exception {
            System.out.println("------Satrting the Test -------");
            Serveur server = new Serveur(8000);
            Client client = new Client("127.0.0.1",8000);
            Thread serverInstance = new Thread(server);
            serverInstance.start();
            client.CreateVoiture("voiture Peugot", "Partner" ,140);
            client.clientDown();
        }
}
