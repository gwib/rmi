import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends Service {

    protected Server() throws RemoteException{
        super();
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            // Instantiating the implementation class
            Service obj = new Service();

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(obj, 0);

            Registry vRegistry = LocateRegistry.getRegistry();
            vRegistry.rebind(ServerInterface.class.getName(), stub);
            System.out.println("Server successfully bound to registry");
        }
        catch (RemoteException e){
            System.err.println("Server exception: "+ e.toString());
            e.printStackTrace();
        }
    }
}
