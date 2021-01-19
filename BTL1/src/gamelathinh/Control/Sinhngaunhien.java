/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelathinh.Control;

import gamelathinh.Model.Matranhinh;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author E5470
 */
public class Sinhngaunhien implements Serializable{

    private Matranhinh mt;
//    private ArrayList<Integer> a;

    public Sinhngaunhien() {
    }

    public Sinhngaunhien(Matranhinh mt) {
//        this.a = new ArrayList<>();
        this.mt = mt;
//        this.sinhngaunhien();
    }

    // Sinh ma tran hinh anh
    public void sinhngaunhien() {
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
        

        //day ma anh vao ma tran
        int index = 0;
        for (int i = 0; i < mt.getSz(); i++) {
            for (int j = 0; j < mt.getSz(); j++) {
                mt.getMt()[i][j] = mt.getA().get(index++);
                System.out.print(mt.getMt()[i][j] + "\t");
            }
            System.out.println("");
        }

    }

    public void setMt(Matranhinh mt) {
        this.mt = mt;
    }


    //Check 2 anh trung lap
    public boolean ktdungdan(int x, int y) {
        boolean check = false;
        if (x == y) {
            check = true;
        }
        return check;
    }
    
    
    //Tai anh init
    public void khoitaoanh(ArrayList<JButton> arrBt) {
        for (int i = 0; i < arrBt.size(); i++) {
            arrBt.get(i).setIcon(load("img\\init.jpg", 70, 70));
        }
    }
    // tai 1 anh
    public void khoitaoanh(JButton b , String _link,int i,int j) {
            b.setIcon(load(_link, j, i));
    }
    //resize anh
    public Icon load(String linkImage, int k, int m) {/*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/
        try {
            BufferedImage image = ImageIO.read(new File(linkImage));//đọc ảnh dùng BufferedImage
            int x = k;
            int y = m;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0, dy = 0;

            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            return new ImageIcon(image.getScaledInstance(dx, dy,
                    image.SCALE_SMOOTH));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }
    
//    public static void main(String[] args) {
//        Sinhngaunhien a = new Sinhngaunhien(new Matranhinh(4,16));
////        System.out.println("lallalala");
////        a.taianh();
//    }
}
