package server;


import client.model.Users;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.util.Pair;


/**
 *
 * @author Lenovo
 */
public interface RMIInterface extends Remote{
    public ArrayList<Users> listRank() throws RemoteException;
    public ArrayList<Pair<Users,String>> matchHistory(Users u) throws RemoteException;
}
