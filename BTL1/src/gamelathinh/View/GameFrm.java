/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamelathinh.View;

import gamelathinh.Control.Sinhngaunhien;
import gamelathinh.Model.Matranhinh;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author E5470
 */
public class GameFrm extends javax.swing.JFrame {

    /**
     * Creates new form GameFrm
     */
    ArrayList<JButton> arr;
    ArrayList<Object> luuvet;
    Sinhngaunhien s;
    Matranhinh mt;
    long st_Time;
    long en_Time;
    long fi_Time;
    CountDownd c;
    public GameFrm(Matranhinh mt) {
        
        initComponents();
        fi_Time = -1;
        this.mt = mt;
        s = new Sinhngaunhien(mt);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        Opponent.setForeground(Color.BLUE);
        Opponent.setFont(new Font("Arial", Font.BOLD, 18));
        st_Time = System.currentTimeMillis();
        lbname.setFont(new Font("Arial", Font.BOLD, 15));
        c=new CountDownd();
        c.start();
        arr = new ArrayList<>();
        luuvet = new ArrayList<>();
        arr.add(b1);
        arr.add(b2);
        arr.add(b3);
        arr.add(b4);
        arr.add(b5);
        arr.add(b6);
        arr.add(b7);
        arr.add(b8);
        arr.add(b9);
        arr.add(b10);
        arr.add(b11);
        arr.add(b12);
        arr.add(b13);
        arr.add(b14);
        arr.add(b15);
        arr.add(b16);

        //Tao mang hinh anh ban dau
        s.khoitaoanh(arr);

        int i = 0;
        
        for (JButton j : arr) {
            j.setText(mt.getA().get(i) + "");
            i++;
        }
        
    }

    public CountDownd getC() {
        return c;
    }

    public void setC(CountDownd c) {
        this.c = c;
    }
    
    public void setLbname(String lbname) {
        this.lbname.setText(lbname);
    }
    
    public long getFi_Time() {
        return fi_Time;
    }
    int checkx = 0, count = 0;
    JButton _b1, _b2;

    public void kiemtrahinh() {
//        System.out.println("1 - "+" "+ luuvet.size());
        if (luuvet.size() == 4) {
            String x = (String) luuvet.get(1), y = (String) luuvet.get(3);
            _b1 = (JButton) luuvet.get(0);
            _b2 = (JButton) luuvet.get(2);
            if (x.equals(y) && _b1.equals(_b2) == false) {
                _b1.setEnabled(false);
                _b2.setEnabled(false);
                _b1 = null;
                _b2 = null;
                count++;
            } else if (_b1.equals(_b2) == true) {
                s.khoitaoanh(_b1, "img\\init.jpg",
                        70, 70);
            } else if (x.equals(y) == false) {
                checkx = 1;
            }
            luuvet.clear();
//            System.out.println(luuvet.size()+" "+checkx);
        } else if (luuvet.size() == 2 && checkx == 1) {
            s.khoitaoanh(_b1, "img\\init.jpg",
                    70, 70);
            s.khoitaoanh(_b2, "img\\init.jpg",
                    70, 70);
            checkx = 0;
        }
//        System.out.println("2 - "+" "+ luuvet.size());
        if (count == (mt.getSz() * mt.getSz()) / 2) {
            for (JButton j : arr) {
                j.setEnabled(true);
            }
            en_Time = System.currentTimeMillis();
            fi_Time = en_Time - st_Time;
            c.stop();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b1 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        lbTime = new javax.swing.JLabel();
        Opponent = new javax.swing.JLabel();
        lbname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));

        b1.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b1.setText("1");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b4.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b4.setText("4");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b2.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b2.setText("2");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b3.setText("3");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b5.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b5.setText("5");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b6.setText("6");
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b7.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b7.setText("7");
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        b8.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b8.setText("8");
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        b10.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b10.setText("10");
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });

        b12.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b12.setText("12");
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });

        b11.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b11.setText("11");
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });

        b9.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b9.setText("9");
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });

        b14.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b14.setText("14");
        b14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b14ActionPerformed(evt);
            }
        });

        b15.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b15.setText("15");
        b15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b15ActionPerformed(evt);
            }
        });

        b16.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b16.setText("16");
        b16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b16ActionPerformed(evt);
            }
        });

        b13.setFont(new java.awt.Font("Dialog", 1, 1)); // NOI18N
        b13.setText("13");
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });

        lbTime.setText("  Thời gian còn lại");

        Opponent.setText("Opponent");

        lbname.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTime)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Opponent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Opponent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(lbTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed

        s.khoitaoanh(b1, "img\\"
                + b1.getText() + ".jpg", 150, 150);
        luuvet.add(b1);
        luuvet.add(b1.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b1ActionPerformed

    public void setFi_Time(long fi_Time) {
        this.fi_Time = fi_Time;
    }

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b3, "img\\"
                + b3.getText() + ".jpg", 150, 150);
        luuvet.add(b3);
        luuvet.add(b3.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b4, "img\\"
                + b4.getText() + ".jpg", 150, 150);

        luuvet.add(b4);
        luuvet.add(b4.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b4ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b10, "img\\"
                + b10.getText() + ".jpg", 150, 150);
        luuvet.add(b10);
        luuvet.add(b10.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b10ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b11, "img\\"
                + b11.getText() + ".jpg", 150, 150);
        luuvet.add(b11);
        luuvet.add(b11.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b11ActionPerformed

    private void b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b14, "img\\"
                + b14.getText() + ".jpg", 150, 150);
        luuvet.add(b14);
        luuvet.add(b14.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b14ActionPerformed

    private void b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15ActionPerformed
        // TODO add your handling code here
        s.khoitaoanh(b15, "img\\"
                + b15.getText() + ".jpg", 150, 150);
        luuvet.add(b15);
        luuvet.add(b15.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b15ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b7, "img\\"
                + b7.getText() + ".jpg", 150, 150);
        luuvet.add(b7);
        luuvet.add(b7.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b8, "img\\"
                + b8.getText() + ".jpg", 150, 150);
        luuvet.add(b8);
        luuvet.add(b8.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b8ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b2, "img\\"
                + b2.getText() + ".jpg", 150, 150);
        luuvet.add(b2);
        luuvet.add(b2.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b2ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b5, "img\\"
                + b5.getText() + ".jpg", 150, 150);
        luuvet.add(b5);
        luuvet.add(b5.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b6, "img\\"
                + b6.getText() + ".jpg", 150, 150);
        luuvet.add(b6);
        luuvet.add(b6.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b6ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b9, "img\\"
                + b9.getText() + ".jpg", 150, 150);
        luuvet.add(b9);
        luuvet.add(b9.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b9ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b12, "img\\"
                + b12.getText() + ".jpg", 150, 150);
        luuvet.add(b12);
        luuvet.add(b12.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b12ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b13, "img\\"
                + b13.getText() + ".jpg", 150, 150);
        luuvet.add(b13);
        luuvet.add(b13.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b13ActionPerformed

    private void b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16ActionPerformed
        // TODO add your handling code here:
        s.khoitaoanh(b16, "img\\"
                + b16.getText() + ".jpg", 150, 150);
        luuvet.add(b16);
        luuvet.add(b16.getText());
        kiemtrahinh();
    }//GEN-LAST:event_b16ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GameFrm g = new GameFrm();
//                g.setVisible(true);
//                g.setLocationRelativeTo(null);
//                g.setTitle("GAME LẬT HÌNH");
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Opponent;
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbname;
    // End of variables declaration//GEN-END:variables

    class CountDownd extends Thread{

        int second=120;

        public CountDownd() {
        }
        
        @Override
        public void run() {
            while(true){
                try {
                    int minute=second/60;
                    int secondS=second%60;
                    lbTime.setForeground(Color.red);
                    lbTime.setFont(new Font("Arial", Font.BOLD, 20));
                    lbTime.setText("Time left: "+minute+" minute "+secondS+" second");
                    sleep(1000);
                    second--;
                    if(second==0){
                        setFi_Time(Integer.MAX_VALUE);
                        this.stop();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameFrm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            //super.run(); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
