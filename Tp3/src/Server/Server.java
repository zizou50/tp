package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class Server {
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(1099);
            ArithOpImpl mediator = new ArithOpImpl();
            System.out.println("server running !! ");
            Naming.bind("AithmeticOperations", mediator);
            System.out.println("waiting for clients");
        }
        catch(Exception e){

        }
    }
}
