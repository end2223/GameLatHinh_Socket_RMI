package client.control;

import gamelathinh.Model.Matranhinh;
import gamelathinh.View.GameFrm;
import client.model.FriendsList;
import client.model.Users;
import client.view.ChallengeFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import server.RMIInterface;
/**
 *
 * @author Lenovo
 */
public class ClientControl extends Thread{

    private int port = 101;
    private int rmiport = 102;
    private String host = "localhost";
    private Socket mySocket;
    private Users user;
    private Registry registry;
    private String rmiService="rmi";
    private RMIInterface rmiServer;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    
    JTable tblFriends;
    JTextField lbPoints;

    public void setLbPoints(JTextField lbPoints) {
        this.lbPoints = lbPoints;
    }
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


    public void setTblFriends(JTable tblFriends) {
        this.tblFriends = tblFriends;
    }

    public ClientControl(){
        openConnection();
        try {
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
            registry=LocateRegistry.getRegistry(host, rmiport);
            rmiServer=(RMIInterface) registry.lookup(rmiService);
        } catch (Exception ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void openConnection() {
        try {
            mySocket = new Socket(host, port);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void closeConnection() {
        try {
            mySocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(String rq, Object o) {
        try {
            oos.writeUTF(rq);
            oos.writeObject(o);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendData(String rq) {
        try {
            oos.writeUTF(rq);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(Object o) {
        try {
            oos.writeObject(o);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receiveText() {
        String rs = null;
        try {
            rs = ois.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Object receiveData() {
        Object rs = null;
        try {
            rs = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                
                String rq = ois.readUTF();
                System.out.println(rq);
                if (rq.equals("online user")) {
                    FriendsList fl = (FriendsList) ois.readObject();
                    setModel(fl);
                } 
                else if(rq.equals("update points")){
                    Users u=(Users) ois.readObject();
                    System.out.println(u);
                    lbPoints.setText("Points: " + u.getPoints());
                }
                else if (rq.equals("Add friend successfully")) {
                    JOptionPane.showMessageDialog(tblFriends, rq);
                } else if (rq.equals("Add friend fail")) {
                    JOptionPane.showMessageDialog(tblFriends, rq);
                } else if (rq.equals("Value doesn't exist")) {
                    JOptionPane.showMessageDialog(tblFriends, rq);
                } else if (rq.equals("challenge")) {
                    Users thisu = (Users) ois.readObject();
                    Users user = (Users) ois.readObject();
                    ChallengeFrm c = new ChallengeFrm();
                    c.setVisible(true);
                    c.setLocationRelativeTo(tblFriends);
                    c.getTxt1().setText(c.getTxt1().getText()+" "+thisu.getHoten());
                    c.getYes().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("donh y thach dau");
                            sendData("accept", thisu);
                            sendData(user);
                            c.dispose();
                        }
                    });
                    c.getNo().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("Khong dong y thach dau");
                            sendData("not accept",thisu);
                            sendData(user);
                            c.dispose();
                        }
                    });
                    
                } else if(rq.equals("accept")){
//                    System.out.println("loading game...");
                    Users u=(Users) ois.readObject();
                    //JOptionPane.showMessageDialog(tblFriends, "READY?");
                    Matranhinh mt=(Matranhinh) ois.readObject();
                    mt.showMT();
                    GameFrm game = new GameFrm(mt);
                    game.setLbname("Name: "+u.getHoten());
                    game.setVisible(true);
                    game.setLocationRelativeTo(tblFriends);

                    while(true){
                        sleep(1000);
                        if(game.getFi_Time()!=-1){
                            //System.out.println("2");
                            user.setFi_time(game.getFi_Time());
                            
                            System.out.println(user);
                            sendData("Calculate",user);
                            sendData(game.getFi_Time());
                            String rq1=ois.readUTF();
                            if(rq1.equals("result")){
                                String rs=(String) ois.readObject();
                                int n=JOptionPane.showConfirmDialog (tblFriends, rs+"\nWould You Like To Play Again?","Game",JOptionPane.YES_NO_OPTION);
                                if(n==0){
                                    sendData("play again", user);
                                    game.dispose();
                                }
                                else{
                                    sendData("quit", user);
                                    game.dispose();

                                }
                            }
                            break;
                        }
                    }
                }
                else if(rq.equals("not accept")){
                    Users u=(Users) ois.readObject();
                    JOptionPane.showMessageDialog(tblFriends, u.getHoten()+" has refused your challenge!");
                }
//                else if(rq.equals("Wait")){
//                    JOptionPane.showMessageDialog(tblFriends, "Wait For the opponent...");
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setModel(FriendsList fl) {
        Vector vcData;
        Vector vcHead;
        vcData = new Vector();
        ArrayList<Users> lf = fl.getLf();
        vcHead = new Vector();
        vcHead.add("Player");
        vcHead.add("Points");
        vcHead.add("Status");
        for (Users u : lf) {
            if (u.getHoten() != getUser().getHoten()) {
                Vector row = new Vector();
                row.add(u.getHoten());
                row.add(u.getPoints());
                if (u.getIsOnl() == 1) {
                    row.add("Online");
                } else if (u.getIsOnl() == 0){
                    row.add("Offline");
                }
                else{
                    row.add("Busy");
                }
                vcData.add(row);
            }
        }
        tblFriends.setModel(new DefaultTableModel(vcData, vcHead));
        tblFriends.setEnabled(true);

    }
    public DefaultTableModel modeltblRank(){
        DefaultTableModel mod=null;
        try {
            Vector vcData=new Vector();
            Vector vcHead=new Vector();
            ArrayList<Users>ar=rmiServer.listRank();
            vcHead.add("Rank");
            vcHead.add("Player");
            vcHead.add("Points");
            vcHead.add("Points/Game");
            vcHead.add("Time/GameWin");
            int rank=1;
            for(Users u:ar){
                Vector row=new Vector();
                row.add(rank);
                row.add(u.getHoten());
                row.add(u.getPoints());
                if(u.getGames()==0)
                    row.add(u.getPoints());
                else
                    row.add(u.getPoints()/u.getGames());
                if(u.getGameswin()==0)
                    row.add(u.getTotaltime());
                else
                    row.add(u.getTotaltime()/u.getGameswin());
                System.out.println(u.getPoints()+" "+u.getTotaltime());
                vcData.add(row);
                rank++;
            }
            mod=new DefaultTableModel(vcData, vcHead);
            
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mod;
    }
    public DefaultTableModel modeltblHistory(Users u){
        DefaultTableModel mod=null;
        try {
            Vector vcData=new Vector();
            Vector vcHead=new Vector();
            ArrayList<Pair<Users,String>>ar=rmiServer.matchHistory(u);
            vcHead.add("No");
            vcHead.add("Opponent");
            vcHead.add("Result");
            int rank=1;
            for(Pair<Users,String> p:ar){
                Vector row=new Vector();
                row.add(rank);
                row.add(p.getKey().getHoten());
                row.add(p.getValue());
                vcData.add(row);
                rank++;
            }
            mod=new DefaultTableModel(vcData, vcHead);
            
        } catch (RemoteException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mod;
    }
  

}
