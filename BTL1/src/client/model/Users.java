package client.model;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
public class Users implements Serializable{
    private int id;
    private String hoten;
    private String username;
    private String pass;

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", hoten=" + hoten + ", username=" + username + ", pass=" + pass + ", isOnl=" + isOnl + ", points=" + points + ", fi_time=" + fi_time + ", check=" + check + ", totaltime=" + totaltime + ", games=" + games + ", gameswin=" + gameswin + '}';
    }
    private int isOnl;
    private float points;
    private long fi_time;
    private int check;
    private long totaltime;
    private int games;
    private int gameswin;
    public Users(String hoten, String username, String pass,float points,long totaltime,int games,int gameswin) {
        this.hoten = hoten;
        this.username = username;
        this.pass = pass;
        this.points=points;
        this.isOnl=0;
        this.fi_time=-1;
        this.check=0;
        this.totaltime=totaltime;
        this.games=games;
        this.gameswin=gameswin;
    }


    
    public Users(String username, String pass) {
        this.username = username;
        this.pass = pass;
        this.isOnl=0;
        this.fi_time=-1;
        this.check=0;
        this.totaltime=0;
        this.games=0;
        this.gameswin=0;
    }

    public Users() {
        points=0;
        this.isOnl=0;
        this.fi_time=-1;
        this.check=0;
        this.totaltime=0;
        this.games=0;
        this.gameswin=0;
    }

    public int getCheck() {
        return check;
    }

    public long getTotaltime() {
        return totaltime;
    }

    public int getGameswin() {
        return gameswin;
    }

    public void setGameswin(int gameswin) {
        this.gameswin = gameswin;
    }

    
    public void setTotaltime(long totaltime) {
        this.totaltime = totaltime;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
    
    
    public void setCheck(int check) {
        this.check = check;
    }

    public long getFi_time() {
        return fi_time;
    }

    public void setFi_time(long fi_time) {
        this.fi_time = fi_time;
    }

    
    public int getIsOnl() {
        return isOnl;
    }

    public void setIsOnl(int isOnl) {
        this.isOnl = isOnl;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    
}
