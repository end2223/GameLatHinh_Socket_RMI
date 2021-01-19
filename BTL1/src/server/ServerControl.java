package server;

import gamelathinh.Model.Matranhinh;
import client.model.FriendsList;
import client.model.Users;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Lenovo
 */
public class ServerControl extends UnicastRemoteObject implements RMIInterface{

    private int port = 101;
    private int rmiport = 102;
    private ServerSocket serverSocket;
    private DBAccess db;
    HashMap<String, Handler> clientMap;
    Object lock;
    ArrayList<Pair<Handler, Handler>> pairs;
    private Registry registry;
    private String rmiService="rmi";
    public ServerControl() throws RemoteException {
        lock = new Object();
        db = new DBAccess();
        openConnection();
        clientMap = new HashMap<>();
        pairs = new ArrayList<>();
        try{
            registry=LocateRegistry.createRegistry(rmiport);
            registry.rebind(rmiService, this);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        while (true) {
            listening();
        }
    }

    public void openConnection() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listening() {
        try {
            Socket client = serverSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            String rc = ois.readUTF();
            Users u = (Users) ois.readObject();
            if (rc.equals("login")) {
                System.out.println(u.getUsername());
                if (db.checkUser(u)) {

                    Handler handler = new Handler(u, lock, ois, oos);
                    handler.setSocket(client);
                    clientMap.put(u.getHoten(), handler);

                    oos.writeUTF("Login Successfully");
                    oos.writeObject(u);
                    oos.flush();

                    handler.start();

                    updateOnlineUsers();
                } else {
                    oos.writeUTF("Login Fail");
                    oos.flush();
                }
            } else if (rc.equals("signup")) {
                if (!db.checkUserExist(u)) {
                    oos.writeObject("Signup Successfully");
                    oos.flush();
                } else {
                    oos.writeObject("Signup Fail");
                    oos.flush();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateOnlineUsers() {
        try {
            for (Map.Entry<String, Handler> entry : clientMap.entrySet()) {
                Handler value = entry.getValue();
                value.getOos().writeUTF("online user");
                FriendsList fl = new FriendsList(value.getUser());
                fl.setLf(db.listFr(value.getUser()));
                value.getOos().writeObject(fl);
                value.getOos().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updatePoints() {
        try {
            for (Map.Entry<String, Handler> entry : clientMap.entrySet()) {
                Handler value = entry.getValue();
                value.getOos().writeUTF("update points");
                value.setUser(db.getInfo(value.getUser().getHoten()));
                value.getOos().writeObject(value.getUser());
                value.getOos().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Users> listRank() throws RemoteException {
        ArrayList<Users>ar=db.listRank();
        return ar;
    }

    @Override
    public ArrayList<Pair<Users, String>> matchHistory(Users u) throws RemoteException {
        ArrayList<Pair<Users,String>>logs=db.matchHistory(u);
        return logs;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class Handler extends Thread {

        Object lock;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        Socket socket;
        Users user;

        public Handler(Users user, Object lock, ObjectInputStream ois, ObjectOutputStream oos) {
            this.user = user;
            this.lock = lock;
            this.oos = oos;
            this.ois = ois;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        public ObjectInputStream getOis() {
            return ois;
        }

        public ObjectOutputStream getOos() {
            return oos;
        }

        public Users getUser() {
            return user;
        }

        public void setUser(Users user) {
            this.user = user;
        }

        
        private void closeSocket() {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void run() {
            Pair<Handler, Handler> temp_pair = null;
            while (true) {
                try {
                    String rq = ois.readUTF();
                    System.out.println(rq);
                    if (rq.equals("log out")) {
                        Users u = (Users) ois.readObject();
                        db.logOut(u);
                        clientMap.remove(u.getHoten());
                        updateOnlineUsers();
                        break;
                    } else if (rq.equals("add friend")) {
                        Users thisu = (Users) ois.readObject();
                        Users u = (Users) ois.readObject();
                        if (db.checkUserExist2(u)) {
                            if (db.addFriend(thisu, u)) {
                                synchronized (lock) {
                                    clientMap.get(thisu.getHoten()).getOos().writeUTF("Add friend successfully");
                                }
                                updateOnlineUsers();
                            } else {
                                synchronized (lock) {
                                    clientMap.get(thisu.getHoten()).getOos().writeUTF("Add friend fail");
                                }
                                updateOnlineUsers();
                            }
                        } else {
                            synchronized (lock) {
                                clientMap.get(thisu.getHoten()).getOos().writeUTF("Value doesn't exist");
                            }
                            updateOnlineUsers();
                        }
                    } else if (rq.equals("challenge")) {
                        synchronized (lock) {
                            Users thisu = (Users) ois.readObject();
                            Users u = (Users) ois.readObject();
//                            clientMap.get(thisu.getHoten()).getOos().writeUTF("challenge");
                            clientMap.get(u.getHoten()).getOos().writeUTF("challenge");
                            clientMap.get(u.getHoten()).getOos().writeObject(thisu);
                            clientMap.get(u.getHoten()).getOos().writeObject(u);
                        }
//                        updateOnlineUsers();
                    } else if (rq.equals("accept")) {
                        synchronized (lock) {
                            Users thisu = (Users) ois.readObject();
                            Users u = (Users) ois.readObject();
                            System.out.println(thisu+"\n"+u);
                            if(db.getStatus(u)==1&&db.getStatus(thisu)==1){
                                db.updateStatus(u, 2);
                                db.updateStatus(thisu, 2);
                                updateOnlineUsers();
                                Matranhinh mt = new Matranhinh(4, 31);
                                db.sinhngaunhien(mt);
                                clientMap.get(thisu.getHoten()).getOos().writeUTF("accept");
                                clientMap.get(thisu.getHoten()).getOos().writeObject(u);
                                clientMap.get(thisu.getHoten()).getOos().writeObject(mt);
                                clientMap.get(u.getHoten()).getOos().writeUTF("accept");
                                clientMap.get(u.getHoten()).getOos().writeObject(thisu);
                                clientMap.get(u.getHoten()).getOos().writeObject(mt);
                                pairs.add(new Pair<>(clientMap.get(thisu.getHoten()), clientMap.get(u.getHoten())));
                            }
                            else{
                                
                            }

                        }
                        //updateOnlineUsers();
                    } else if (rq.equals("not accept")) {
                        synchronized (lock) {
                            Users thisu = (Users) ois.readObject();
                            Users u = (Users) ois.readObject();
                            clientMap.get(thisu.getHoten()).getOos().writeUTF("not accept");
                            clientMap.get(thisu.getHoten()).getOos().writeObject(u);
//                            clientMap.get(u.getHoten()).getOos().writeUTF("not accept");
//                            clientMap.get(u.getHoten()).getOos().writeObject(thisu);
                        }
                        //updateOnlineUsers();
                    } else if (rq.equals("Calculate")) {
                        synchronized (lock) {
                            Users u = new Users();
                            u=(Users) ois.readObject();
                            long time_=(long) ois.readObject();
                            u.setFi_time(time_);
                            System.out.println(u+" "+time_);
                            Handler temp = clientMap.get(u.getHoten());
                            for (Pair<Handler, Handler> i : pairs) {
                                if (i.getKey().getUser().getHoten().equals(temp.getUser().getHoten())) {
                                    i.getKey().getUser().setFi_time(u.getFi_time());
                                    temp_pair = i;
                                    i.getKey().getUser().setCheck(1);
                                    System.out.println(i.getKey().getUser().getFi_time()+" key");
                                    break;
                                }
                                if (i.getValue().getUser().getHoten().equals(temp.getUser().getHoten())) {
                                    i.getValue().getUser().setFi_time(u.getFi_time());
                                    temp_pair = i;
                                    i.getValue().getUser().setCheck(1);
                                    System.out.println(i.getValue().getUser().getFi_time()+" value");
                                    break;
                                }
                            }
                            if (temp_pair.getKey().getUser().getCheck() == 1 && temp_pair.getValue().getUser().getCheck() == 1) {

                                long t1 = temp_pair.getKey().getUser().getFi_time();
                                long t2 = temp_pair.getValue().getUser().getFi_time();
                                System.out.println(t1+" "+t2);
                                String rs="";
                                if (t1 < t2) {
                                    temp_pair.getKey().oos.writeUTF("result");
                                    temp_pair.getKey().oos.writeObject("YOU WIN");
                                    rs="win";
                                    temp_pair.getValue().oos.writeUTF("result");
                                    temp_pair.getValue().oos.writeObject("YOU LOSE");
                                    db.updatePointsAndGame(temp_pair.getKey().getUser(), 1, "win");
                                    db.updatePointsAndGame(temp_pair.getValue().getUser(), 0, "lose");
                                } else if (t1 > t2) {
                                    temp_pair.getKey().oos.writeUTF("result");
                                    temp_pair.getKey().oos.writeObject("YOU LOSE");
                                    rs="lose";
                                    temp_pair.getValue().oos.writeUTF("result");
                                    temp_pair.getValue().oos.writeObject("YOU WIN");
                                    db.updatePointsAndGame(temp_pair.getValue().getUser(), 1,"win");
                                    db.updatePointsAndGame(temp_pair.getKey().getUser(), 0,"lose");
                                } else {
                                    temp_pair.getKey().oos.writeUTF("result");
                                    temp_pair.getKey().oos.writeObject("TIE");
                                    rs="tie";
                                    temp_pair.getValue().oos.writeUTF("result");
                                    temp_pair.getValue().oos.writeObject("TIE");
                                    db.updatePointsAndGame(temp_pair.getKey().getUser(), (float) 0.5,"tie");
                                    db.updatePointsAndGame(temp_pair.getValue().getUser(), (float) 0.5,"tie");
                                }
                                updatePoints();
                                db.updateHistoryMatch(temp_pair.getKey().getUser(), temp_pair.getValue().getUser(), rs);
                                temp_pair.getKey().getUser().setFi_time(-1);
                                temp_pair.getValue().getUser().setFi_time(-1);
                                temp_pair.getKey().getUser().setCheck(0);
                                temp_pair.getValue().getUser().setCheck(0);
                            }

                        }

                    } else if (rq.equals("play again")) {
                        synchronized (lock) {
                            Users u = (Users) ois.readObject();
                            Handler temp = clientMap.get(u.getHoten());
                            if (temp_pair.getKey().equals(temp)) {
                                temp_pair.getKey().getUser().setCheck(2);
                            }
                            if (temp_pair.getValue().equals(temp)) {
                                temp_pair.getValue().getUser().setCheck(2);
                            }
                            if (temp_pair.getKey().getUser().getCheck() == 2 && temp_pair.getValue().getUser().getCheck() == 2) {
                                Matranhinh mt = new Matranhinh(4, 31);
                                db.sinhngaunhien(mt);
                                temp_pair.getKey().oos.writeUTF("accept");
                                temp_pair.getKey().oos.writeObject(temp_pair.getKey().getUser());
                                temp_pair.getKey().oos.writeObject(mt);
                                temp_pair.getValue().oos.writeUTF("accept");
                                temp_pair.getValue().oos.writeObject(temp_pair.getValue().getUser());
                                temp_pair.getValue().oos.writeObject(mt);
                            }
                        }
                    } else if (rq.equals("quit")) {
                        synchronized (lock) {
                            Users u = (Users) ois.readObject();
                            Handler temp = clientMap.get(user.getHoten());
                            if (temp_pair.getKey().equals(temp)) {
                                temp_pair.getKey().getUser().setCheck(3);
                            }
                            if (temp_pair.getValue().equals(temp)) {
                                temp_pair.getValue().getUser().setCheck(3);
                            }
                            if (temp_pair.getKey().getUser().getCheck() == 3 || temp_pair.getValue().getUser().getCheck() == 3) {
                                temp_pair.getKey().oos.writeUTF("not accept");
                                temp_pair.getKey().oos.writeObject(temp_pair.getKey().getUser());
                                temp_pair.getValue().oos.writeUTF("not accept");
                                temp_pair.getValue().oos.writeObject(temp_pair.getValue().getUser());
                                db.updateStatus(temp_pair.getKey().getUser(), 1);
                                db.updateStatus(temp_pair.getValue().getUser(), 1);
                                pairs.remove(temp_pair);
                                updateOnlineUsers();
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}
