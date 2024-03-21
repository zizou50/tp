import java.rmi.*;

public interface InterfaceRMI extends Remote {
    public String convertToUpperCase(String str) throws RemoteException;
}
