/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.zul.separadorcategoriavideo;

import br.zul.zwork4.io.ZFile;
import br.zul.zwork4.io.ZOSFile;
import br.zul.zwork4.str.ZStr;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author luizh
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileOpen = new javax.swing.JFileChooser();
        jFileSave = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        inputIn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inputOut = new javax.swing.JTextField();
        btnProcess = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Arquivo JSON Entrada:");

        jLabel2.setText("Arquivo JSON saída:");

        btnProcess.setText("Processar");
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        jButton2.setText("Selecionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Selecionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(btnProcess)
                    .addComponent(inputIn, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addComponent(inputOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(btnProcess)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        try {
            ZFile in = new ZOSFile(inputIn.getText());
            ZFile out = new ZOSFile(inputOut.getText());
            boolean replaceOut = false;
            
            if (out.exists()){
                int op = JOptionPane.showConfirmDialog(this, "O arquivo de saída já existe. Deseja substituir?", "Substituir arquivo", JOptionPane.YES_NO_OPTION);
                replaceOut = op==JOptionPane.YES_OPTION;
            }
            
            new Process(in, out, replaceOut).process();
        } catch (IOException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnProcessActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jFileOpen.setSelectedFile(new File(inputIn.getText()));
        jFileOpen.setFileFilter(new FileNameExtensionFilter("Arquivo JSON", "json"));
        if (jFileOpen.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            inputIn.setText(jFileOpen.getSelectedFile().getAbsolutePath());
            
            String path = new File(inputIn.getText()).getParentFile().getAbsolutePath();
            String name = new File(inputIn.getText()).getName();
            String extension = new ZStr(name).fromLast(".").toString();
            String nameWithoutExt = new ZStr(name).tillLast(".").toString();
            
            inputOut.setText(new File(path, nameWithoutExt+"_edited."+extension).getAbsolutePath());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jFileSave.setSelectedFile(new File(inputOut.getText()));
        jFileSave.setFileFilter(new FileNameExtensionFilter("Arquivo JSON", "json"));
        if (jFileSave.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            inputOut.setText(jFileSave.getSelectedFile().getAbsolutePath());
            if (!inputOut.getText().toLowerCase().endsWith(".json")){
                inputOut.setText(inputOut.getText()+".json");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcess;
    private javax.swing.JTextField inputIn;
    private javax.swing.JTextField inputOut;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileOpen;
    private javax.swing.JFileChooser jFileSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
