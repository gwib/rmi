import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server extends UnicastRemoteObject implements ServerInterface {

    private static final  long serialVersionUID = 172118;

    protected Server() throws RemoteException{
        super();
    }

    @Override
    public String print(String filename, String printer) throws RemoteException {
        return "print(" + filename + "," + printer + ")";
    }

    @Override
    public String queue() throws RemoteException {
        return "queue()";
    }

    @Override
    public String topQueue(int job) throws RemoteException {
        return "topQueue("+job+")";
    }

    @Override
    public String start() throws RemoteException {
        return "start()";
    }

    @Override
    public String stop() throws RemoteException {
        return "stop()";
    }

    @Override
    public String restart() throws RemoteException {
        return "restart()";
    }

    @Override
    public String status() throws RemoteException {
        return "status()";
    }

    @Override
    public String readConfig(String parameter) throws RemoteException {
        return "readConfig("+parameter+")";
    }

    @Override
    public String setConfig(String parameter, String value) throws RemoteException {
        return "setConfig("+parameter+")";
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry vRegistry = LocateRegistry.getRegistry();
            vRegistry.rebind(ServerInterface.class.getName(), new Server());
            System.out.println("Server successfully bound to registry");
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
