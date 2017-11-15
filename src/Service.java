import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;


public class Service implements ServerInterface {

    private static final  long serialVersionUID = 172118;

    protected Service() throws RemoteException{
        super();
    }

    @Override
    public String welcome() throws RemoteException {
        String welcomeMsg = "*********************************************************\n"
                + "** Welcome to this Authentication Service!\n"
                + "What would you like to do?"
                + "** 1.Create new user\n** 2.Log in\n** 4.Log out\n** 5.Terminate\n"
                + "*********************************************************\n";

        return welcomeMsg;
    }

    @Override
    public boolean newUser(String userName, char[] pw) throws RemoteException, NoSuchAlgorithmException, InvalidKeySpecException{
        System.out.println("REQUEST: Create a user:"+userName);
        DbConnection db = new DbConnection();

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Connection conn = db.connectToMysql();
        //TODO: use my own hash algorithm and generate salt
        String hashedPassword = hashPwd(pw);
        String salt = "asr";
        
        try {
            Statement stmt = conn.createStatement();
            int id = findLastId(conn)+1;
            // Creating statement to insert new user to DB
            String sql = "INSERT INTO userDB VALUES("+ id +","+ userName +"','"+hashedPassword+"','"+salt+"','"+sdf.format(dt)+")";
            stmt.executeUpdate(sql);
            conn.close();
            System.out.println("New User created!\n");
            return true;
        } catch (SQLException e) {
            System.out.println("!!SQL exception while creating a user...");
            return false;
        }
    }

    private String hashPwd(char[] pw) {
        // Create instance of Argon2 object
        Argon2 argon2 = Argon2Factory.create();
        String hash =  argon2.hash(2, 65536, 1, pw);
        argon2.wipeArray(pw);
        return hash;
    }

    private int findLastId(Connection conn) throws SQLException {
        int key;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM userDB ORDER BY ID");
        if (rs.last()) {
            key = rs.getInt("id");
        } else {
            key = 1;
        }
        rs.close();
        return  key;
    }

    //Todo
    @Override
    public boolean login(String userName, char[] pw) throws RemoteException {
        return verifyPwd(pw);
    }

    private boolean verifyPwd(char[] pw) {
        // Create instance of Argon2 object
        Argon2 argon2 = Argon2Factory.create();
        String hash =  argon2.hash(2, 65536, 1, pw);
        if (argon2.verify(hash, pw)) {
            // Hash matches password
            argon2.wipeArray(pw);
            return true;
        } else {
            // Hash doesn't match password
            argon2.wipeArray(pw);
            return false;
        }
    }


    @Override
    public boolean validateSession(String userName) throws RemoteException {
        return false;
    }

    @Override
    public boolean logout(String userName) throws RemoteException {
        return false;
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
}
