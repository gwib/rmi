import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.*;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException{
        System.setSecurityManager(new SecurityManager());
        try {
            Registry vRegistry = LocateRegistry.getRegistry();
            ServerInterface vDateProvider = (ServerInterface)vRegistry.lookup(ServerInterface.class.getName()); // Casting, um nicht nur remote Objekt zu bekommen

            //Todo: Calling remote methods
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        catch (NotBoundException e){    // if server has not been started yet
            e.printStackTrace();
        }
    }
}
