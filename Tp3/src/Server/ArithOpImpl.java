package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ArithOpImpl extends UnicastRemoteObject implements IArithOp  {

    public ArithOpImpl() throws RemoteException {
        super();
    }
    @Override
    public double addition(double a, double b) throws RemoteException {
        return a+b;
    }

    @Override
    public double soustraction(double a, double b) throws RemoteException {
        return a-b;
    }

    @Override
    public double division(double a, double b) throws RemoteException {
            return a/b;
    }

    @Override
    public double multiplication(double a, double b) throws RemoteException {
        return a*b;
    }
    
}
