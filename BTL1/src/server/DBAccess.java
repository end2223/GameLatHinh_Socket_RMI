package server;

import gamelathinh.Model.Matranhinh;
import client.model.Users;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Lenovo
 */
public class DBAccess implements Serializable {

    private Connection con;

    public DBAccess() throws RemoteException {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/th1";
            String dbClass = "com.mysql.jdbc.Driver";
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbURL, "root", "MAYKHAUMOI");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUser(Users u) {
        String sql = "SELECT * FROM users WHERE username=? AND pass=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPass());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setHoten(rs.getString("hoten"));
                u.setPoints(rs.getFloat("points"));
                u.setTotaltime(rs.getLong("totaltime"));
                u.setGames(rs.getInt("games"));
                u.setGameswin(rs.getInt("gameswin"));
                if (rs.getInt("isOnl") == 0) {
                    String sql1 = "UPDATE users SET isOnl=1 WHERE id=?";
                    PreparedStatement ps1 = con.prepareStatement(sql1);
                    ps1.setInt(1, rs.getInt("id"));
                    ps1.executeUpdate();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUserExist(Users u) {
        String sql = "SELECT * FROM users WHERE username=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                String sql1 = "INSERT INTO users(hoten,username,pass,points,isonl,status,games,totaltime) VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1, u.getHoten());
                ps1.setString(2, u.getUsername());
                ps1.setString(3, u.getPass());
                ps1.setFloat(4, 0);
                ps1.setInt(5, 0);
                ps1.setInt(6, 0);
                ps1.setInt(7, 0);
                ps1.setInt(8, 0);
                ps1.executeUpdate();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkUserExist2(Users u) {
        String sql = "SELECT * FROM users WHERE username=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setHoten(rs.getString("hoten"));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public ArrayList<Users> listFr(Users u) {
        String sql1 = "SELECT * FROM isfriend,users WHERE users.id=isfriend.id1 AND users.id=?";
        String sql2 = "SELECT * FROM isfriend,users WHERE users.id=isfriend.id2 AND users.id=?";
        ArrayList<Users> lf = new ArrayList<>();
        try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, u.getId());
            ResultSet rs1 = ps1.executeQuery();
            ArrayList<Integer> temp1 = new ArrayList<>();
            while (rs1.next()) {
                temp1.add(rs1.getInt("id2"));
                //System.out.println(rs1.getInt("id2"));
            }

            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, u.getId());
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                temp1.add(rs2.getInt("id1"));
                //System.out.println(rs2.getInt("id1"));
            }
            for (Integer i : temp1) {
                String sql3 = "SELECT * FROM users  WHERE id=?";
                PreparedStatement ps3 = con.prepareStatement(sql3);
                ps3.setInt(1, i);
                ResultSet rs3 = ps3.executeQuery();
                if (rs3.next()) {
                    Users u3 = new Users();
                    u3.setHoten(rs3.getString("hoten"));
                    u3.setPoints(rs3.getFloat("points"));
                    u3.setIsOnl(rs3.getInt("isOnl"));
                    //System.out.println(u3.getHoten());
                    lf.add(u3);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lf;
    }

    public void logOut(Users p) {
        String sql = "UPDATE users SET isOnl=0 WHERE hoten=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getHoten());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean addFriend(Users thisu, Users u) {
        try {
            String sql = "Select * from isfriend where (id1=? and id2=?) or (id2=? and id1=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, thisu.getId() + "");
            ps.setString(2, u.getId() + "");
            ps.setString(3, thisu.getId() + "");
            ps.setString(4, u.getId() + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("lalalal");
                return false;
            } else {
                String sql1 = "Insert into isfriend value(?, ?)";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1, thisu.getId() + "");
                ps1.setString(2, u.getId() + "");
                ps1.executeUpdate();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void updatePointsAndGame(Users u, float p, String winOrlose) {
        try {
            String sql = "UPDATE th1.users SET points=? , games=? ,gameswin=?, totaltime=? WHERE hoten=?";
            PreparedStatement ps = con.prepareStatement(sql);
            float temp = u.getPoints() + p;
            ps.setFloat(1, temp);
            ps.setInt(2, u.getGames()+1);
            if(winOrlose.equals("win")){
                ps.setInt(3, u.getGameswin()+1);
                ps.setLong(4, u.getTotaltime()+u.getFi_time());
            } 
            else{
                ps.setInt(3, u.getGameswin());
                ps.setLong(4, u.getTotaltime());
            }
            ps.setString(5, u.getHoten());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Users getInfo(String s){
        Users u=new Users();
        String sql = "SELECT * FROM users WHERE hoten=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setHoten(rs.getString("hoten"));
                u.setPoints(rs.getFloat("points"));
                u.setTotaltime(rs.getLong("totaltime"));
                u.setGames(rs.getInt("games"));
                u.setGameswin(rs.getInt("gameswin"));
                u.setIsOnl(rs.getInt("isOnl"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
        return u;
    }
    public void updateStatus(Users u,int status) {
        String sql = "UPDATE th1.users SET isOnl=? WHERE hoten=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, u.getHoten());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sinhngaunhien(Matranhinh mt) {
        int sz = mt.getSz() * mt.getSz();
        Random rd = new Random();

        //Sinh ra so luong anh can thiet khong trung lap 
        Set<Integer> setsz = new HashSet<Integer>();
        while (setsz.size() < sz / 2) {
            setsz.add((rd.nextInt(rd.nextInt(Integer.MAX_VALUE)) % mt.getSla()));
        }

        mt.getA().addAll(setsz);
        mt.getA().addAll(mt.getA());
        int id = 555;

        //random lan nua de tranh doi xung
//        for (int i = 0; i < mt.getA().size(); i++) {
//            System.out.println(mt.getA().get(i));
//        }
        while (id >= 0) {
            int x = rd.nextInt(mt.getA().size());
            int y = rd.nextInt(mt.getA().size());
            int tmp = mt.getA().get(x);
            mt.getA().set(x, mt.getA().get(y));
            mt.getA().set(y, tmp);
            id--;
        }
    }

    
    
    public int getStatus(Users u) {
        try {
            String sql = "SELECT * FROM users Where hoten=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getHoten());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("isOnl");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Users> listRank() throws RemoteException {
        ArrayList<Users> ar = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users ORDER BY points DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int dem = 0;
            while (rs.next()) {
                Users u = new Users();
                u.setHoten(rs.getString("hoten"));
                u.setGames(rs.getInt("games"));
                u.setPoints(rs.getFloat("points"));
                u.setGameswin(rs.getInt("gameswin"));
                u.setTotaltime(rs.getLong("totaltime"));
                ar.add(u);
                dem++;
                if (dem == 9) {
                    break;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }
    public ArrayList<Pair<Users,String>> matchHistory(Users u) throws RemoteException{
        ArrayList<Pair<Users,String>>logs=new ArrayList<>();
        String sql1 = "SELECT * FROM matchhistory,users WHERE users.id=matchhistory.id1 AND users.id=?";
        String sql2 = "SELECT * FROM matchhistory,users WHERE users.id=matchhistory.id2 AND users.id=?";
        try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, u.getId());
            ResultSet rs1 = ps1.executeQuery();
            ArrayList<Pair<Integer,String>> temp1 = new ArrayList<>();
            while (rs1.next()) {
                temp1.add(new Pair<>(rs1.getInt("id2"),rs1.getString("result")));
                //System.out.println(rs1.getInt("id2"));
            }

            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, u.getId());
            ResultSet rs2 = ps2.executeQuery();
            System.out.println(rs2);
            while (rs2.next()) {
                if(rs2.getString("result").equals("win"))
                    temp1.add(new Pair<>(rs2.getInt("id1"),"lose"));
                else if(rs2.getString("result").equals("lose"))
                    temp1.add(new Pair<>(rs2.getInt("id1"),"win"));
                else{
                    temp1.add(new Pair<>(rs2.getInt("id1"),"tie"));
                }
                //System.out.println(rs2.getInt("id1"));
            }
            for (Pair<Integer,String> i : temp1) {
                String sql3 = "SELECT * FROM users  WHERE id=?";
                PreparedStatement ps3 = con.prepareStatement(sql3);
                ps3.setInt(1, i.getKey());
                ResultSet rs3 = ps3.executeQuery();
                if (rs3.next()) {
                    Users u3 = new Users();
                    u3.setHoten(rs3.getString("hoten"));
                    logs.add(new Pair<>(u3,i.getValue()));
                    //System.out.println(u3.getHoten());
                }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logs;
    }
    public void updateHistoryMatch(Users u1,Users u2,String result){
        try {
            String sql="INSERT INTO matchhistory VALUES(?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, u1.getId());
            ps.setInt(2, u2.getId());
            ps.setString(3, result);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
