package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IArithOp extends Remote{
    double addition(double a , double b ) throws RemoteException;
    double soustraction(double a, double b) throws RemoteException;
    double division(double a ,double b) throws RemoteException;
    double multiplication(double a, double b) throws RemoteException;    
}