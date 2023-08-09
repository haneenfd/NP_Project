package NP_Project;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) {
        try {
            RemoteObject obj = new RemoteObject();

            RMI stub = null;
            if (obj instanceof UnicastRemoteObject) {
                // Object has already been exported
                stub = (RMI) obj;
            } else {
                // Export the object as a remote object
                stub = (RMI) UnicastRemoteObject.exportObject(obj, 0);
            }
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("db", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
