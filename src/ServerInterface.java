import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public interface ServerInterface extends Remote{

    //authentication
    public String welcome() throws RemoteException;

    public boolean newUser(String userName, char[] pw) throws RemoteException, NoSuchAlgorithmException, InvalidKeySpecException;

    public boolean login(String userName, char[] pw) throws RemoteException;

    public boolean validateSession(String userName) throws RemoteException;

    public boolean logout(String userName) throws  RemoteException;

    // functionalities

    public String print(String filename, String printer) throws  RemoteException;   // prints file filename on the specified printer

    public String queue() throws  RemoteException;   // lists the print queue on the user's display in lines of the form <job number>   <file name>

    public String topQueue(int job) throws  RemoteException;   // moves job to the top of the queue

    public String start() throws  RemoteException;   // starts the print server

    public String stop() throws  RemoteException;   // stops the print server

    public String restart() throws  RemoteException;   // stops the print server, clears the print queue and starts the print server again

    public String status() throws  RemoteException;  // prints status of printer on the user's display

    public String readConfig(String parameter) throws  RemoteException;   // prints the value of the parameter on the user's display

    public String setConfig(String parameter, String value) throws  RemoteException;   // sets the parameter to value
}
