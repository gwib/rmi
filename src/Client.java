import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.*;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException{
        System.setSecurityManager(new SecurityManager());
        try {
            Registry vRegistry = LocateRegistry.getRegistry(null);

            // looking up the registry for the remote object
            // Stub is a representation of the remote object on the client side; gateway for the client programme
            ServerInterface stub = (ServerInterface)vRegistry.lookup(ServerInterface.class.getName());


            //Todo: Calling remote methods
            //Calling welcome() method
            String start = stub.welcome();
            System.out.print(start);
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
        catch (NotBoundException e){    // if server has not been started yet
            e.printStackTrace();
        }
    }
}
