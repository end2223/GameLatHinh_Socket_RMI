/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelathinh.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author E5470
 */
public class Matranhinh implements Serializable{
    private int sz;
    private int mt[][];
    private ArrayList<Integer> a;
    private int sla;

    public Matranhinh() {
    }

    public Matranhinh(int sz, int sla) {
        this.sz = sz;
        this.mt = new int[sz][sz];
        this.a = new ArrayList<>();
        this.sla = sla;
    }
    public void showMT(){
        for(int i=0; i<a.size();i++){
            System.out.print(a.get(i)+"\t");
            if((i+1)%4==0){
                System.out.println("");
            }
        }
    }
    public int getSz() {
        return sz;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public int[][] getMt() {
        return mt;
    }

    public void setMt(int[][] mt) {
        this.mt = mt;
    }

    public int getSla() {
        return sla;
    }

    public void setSla(int sla) {
        this.sla = sla;
    }

    public ArrayList<Integer> getA() {
        return a;
    }

    public void setA(ArrayList<Integer> a) {
        this.a = a;
    }
    
}
