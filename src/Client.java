

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            Registry vRegistry = LocateRegistry.getRegistry();
            ServerInterface vDateProvider = (ServerInterface)vRegistry.lookup(ServerInterface.class.getName()); // Casting, um nicht nur remote Objekt zu bekommen
            //System.out.println(vDateProvider.getDate());
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        catch (NotBoundException e){    // wenn Server noch nicht gestartet ist
            e.printStackTrace();
        }
    }
}
