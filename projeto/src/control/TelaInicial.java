/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Color;
import utils.Consts;

/**
 *
 * @author João Vitor
 */
public class TelaInicial extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonJogar = new javax.swing.JButton();
        jButtonContinuar = new javax.swing.JButton();
        jButtonRecordes = new javax.swing.JButton();
        jButtonCreditos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jButtonJogar.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButtonJogar.setIcon(new javax.swing.ImageIcon("D:\\João Vitor\\Desktop\\trabalho POO\\Trabalho-POO-master\\projeto\\imgs\\play.gif")); // NOI18N
        jButtonJogar.setText("Jogar");
        jButtonJogar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJogarActionPerformed(evt);
            }
        });

        jButtonContinuar.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButtonContinuar.setIcon(new javax.swing.ImageIcon("D:\\João Vitor\\Desktop\\trabalho POO\\Trabalho-POO-master\\projeto\\imgs\\continue.gif")); // NOI18N
        jButtonContinuar.setText("Continuar");
        jButtonContinuar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinuarActionPerformed(evt);
            }
        });

        jButtonRecordes.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButtonRecordes.setIcon(new javax.swing.ImageIcon("D:\\João Vitor\\Desktop\\trabalho POO\\Trabalho-POO-master\\projeto\\imgs\\recordes.gif")); // NOI18N
        jButtonRecordes.setText("Recordes");
        jButtonRecordes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonRecordes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordesActionPerformed(evt);
            }
        });

        jButtonCreditos.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jButtonCreditos.setIcon(new javax.swing.ImageIcon("D:\\João Vitor\\Desktop\\trabalho POO\\Trabalho-POO-master\\projeto\\imgs\\creditos.gif")); // NOI18N
        jButtonCreditos.setText("Créditos");
        jButtonCreditos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreditosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 48)); // NOI18N
        jLabel1.setText("PAC-MAN POO");

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\João Vitor\\Desktop\\trabalho POO\\Trabalho-POO-master\\projeto\\imgs\\fantasmaesquerda.gif")); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\João Vitor\\Desktop\\trabalho POO\\Trabalho-POO-master\\projeto\\imgs\\fantasmadireita.gif")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonRecordes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonJogar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonContinuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)))
                .addComponent(jLabel3)
                .addGap(78, 78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43)
                        .addComponent(jButtonJogar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonContinuar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRecordes)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCreditos))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRecordesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordesActionPerformed
        //Se o susuario clica no botao recordes devemos abrir a tela recordes
        //instanciando a nossa tela de recordes
        //TelaRecordes screenRecordes = new TelaRecordes();
        //mudando a cor do fundo da nossa tela de recordes
        Consts.screenRecordes.getContentPane().setBackground(Color.BLACK);
        //tornando a nossa tela de recordes visivel
        Consts.screenRecordes.setVisible(true);
        //não tenho certeza do que essa linha faz
        Consts.screenRecordes.createBufferStrategy(2);
        
        //Devemos esconder a tela de menu tambem
        this.setVisible(false);
    }//GEN-LAST:event_jButtonRecordesActionPerformed

    private void jButtonJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJogarActionPerformed
        //Se o usuario clica no botão jogar devemos abrir a game screen
        //Primeiro estanciamos a tela da primeira fase
        Stage screen = new Stage();
        //tornamos ela visivel
        screen.setVisible(true);
        //chamamos os metodos para o seu correto funcionamento
        screen.createBufferStrategy(2);
        //inicia o thread do jogo
        screen.go();
        
        //Devemos esconder a tela de menu tambem
        this.setVisible(false);
    }//GEN-LAST:event_jButtonJogarActionPerformed

    private void jButtonCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreditosActionPerformed
        //Se o susuario clica no botao recordes devemos abrir a tela recordes
        //instanciando a nossa tela de creditos
        //TelaCreditos screenCreditos = new TelaCreditos();
        //mudando a cor do fundo da nossa tela de creditos
        Consts.screenCreditos.getContentPane().setBackground(Color.BLACK);
        //tornando a nossa tela de creditos
        Consts.screenCreditos.setVisible(true);
        //não tenho certeza do que essa linha faz
        Consts.screenCreditos.createBufferStrategy(2);
        
        //Devemos esconder a tela de menu tambem
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCreditosActionPerformed

    private void jButtonContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinuarActionPerformed
        //FELIPÃO !!!!!!!!!!!!!
        //@@@@@@@@@@@@@@@@@@@@@
    }//GEN-LAST:event_jButtonContinuarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonContinuar;
    private javax.swing.JButton jButtonCreditos;
    private javax.swing.JButton jButtonJogar;
    private javax.swing.JButton jButtonRecordes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}