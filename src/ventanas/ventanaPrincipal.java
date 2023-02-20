/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Omar Eduardo Moreno Caro ITC 6to 2023
 */
public class ventanaPrincipal extends javax.swing.JFrame {

    private int casillas[][] = new int [3][3];
    private String turno = "Usuario1";
    private boolean vsIA = false;
    
    public ventanaPrincipal() {
        initComponents();
        setSize(450,450);
        setLocationRelativeTo(null);
        llenarMatriz();
    }
    
    private void llenarMatriz(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                casillas[i][j] = 0;
            }
        }
    }
    
    private void ponerX(JButton boton){
        boton.setIcon(new ImageIcon(getClass().getResource("/imagenes/equis.png")));
    }
    
    private void ponerO(JButton boton){
        boton.setIcon(new ImageIcon(getClass().getResource("/imagenes/circulo.png")));
    }
    
    private void ganador(){
        int resultado = revisionMatriz();
        switch(resultado){
            case 1 -> {
                ventanaPostJuego ventanaPJ1 = new ventanaPostJuego(this, true, "Ganó el jugador X");
                ventanaPJ1.setVisible(true);
                reinicioJuego();
            }
            case 2 -> {
                ventanaPostJuego ventanaPJ2 = new ventanaPostJuego(this, true,"Ganó el jugador O");
                ventanaPJ2.setVisible(true);
                reinicioJuego();
            }
            case 3 -> {
                ventanaPostJuego ventanaPJ3 = new ventanaPostJuego(this, true,"Empate");
                ventanaPJ3.setVisible(true);
                reinicioJuego();
            }
            default -> {
            }
        }
    }

    private int revisionMatriz(){
        int salida = 0;
        int espacios = contadorTurnos();
        if(espacios==0){salida=3;}
        for(int i=0;i<3;i++){
            if((casillas[i][0]==casillas[i][1])&&(casillas[i][0]==casillas[i][2])){
                salida = casillas[i][0];
                break;
            }
        }
        for(int j=0;j<3;j++){
            if((casillas[0][j]==casillas[1][j])&&(casillas[0][j]==casillas[2][j])){
                salida = casillas[0][j];
                break;
            }
        }
        if((casillas[0][0]==casillas[1][1])&&(casillas[0][0]==casillas[2][2])){
            salida = casillas[0][0];
        }
        if((casillas[0][2]==casillas[1][1])&&(casillas[0][2]==casillas[2][0])){
            salida = casillas[0][2];
        }
        return salida;
    }
    
    private int contadorTurnos(){
        int espacios = 9;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(casillas[i][j]);
                if(casillas[i][j] != 0){espacios--;}
            }
            System.out.println("");
        }
        System.out.println("\n");
        return espacios;
    }
    
    private void reinicioJuego(){
        llenarMatriz();
        botonGato1.setIcon(null);
        botonGato2.setIcon(null);
        botonGato3.setIcon(null);
        botonGato4.setIcon(null);
        botonGato5.setIcon(null);
        botonGato6.setIcon(null);
        botonGato7.setIcon(null);
        botonGato8.setIcon(null);
        botonGato9.setIcon(null);
        turno = "Usuario1";
    }
    
    private void R(int entrada){
        System.out.println("codigo:" + entrada);
    }
    
    private void algoritmoIA(boolean activo, String turnoIA){
        if(activo && turnoIA.equals("Usuario2")){
            ventanaRespuesta ventanaR = new ventanaRespuesta(this,false);
            ventanaR.setVisible(true);
            int espacios = contadorTurnos();
            
            //inicio
            if(casillas[1][1]==0){ponerO(botonGato5); casillas[1][1]=2;R(1);}//centro libre
            else if(casillas[1][1] == 1 && espacios==8){ponerO(botonGato1); casillas[0][0]=2;R(2);}//centro ocupado
            
            //ataque
            else if((casillas[0][0]==2&&casillas[2][0]==2)&&(casillas[1][0]==0)){ponerO(botonGato4); casillas[1][0]=2;R(103);}
            else if((casillas[0][0]==2&&casillas[0][2]==2)&&(casillas[0][1]==0)){ponerO(botonGato2); casillas[0][1]=2;R(104);}
            else if((casillas[0][2]==2&&casillas[2][2]==2)&&(casillas[1][2]==0)){ponerO(botonGato6); casillas[1][2]=2;R(105);}
            else if((casillas[2][0]==2&&casillas[2][2]==2)&&(casillas[2][1]==0)){ponerO(botonGato8); casillas[2][1]=2;R(106);}
            
            else if(((casillas[2][0]==2&&casillas[2][1]==2)||(casillas[0][2]==2&&casillas[1][2]==2))&&(casillas[2][2]==0)){
               {ponerO(botonGato9); casillas[2][2]=2;R(107);}
            }
            else if(((casillas[1][0]==2&&casillas[2][0]==2)||(casillas[0][1]==2&&casillas[0][2]==2))&&(casillas[0][0]==0)){
               {ponerO(botonGato1); casillas[0][0]=2;R(108);}
            }
            else if(((casillas[0][0]==2&&casillas[0][1]==2)||(casillas[1][2]==2&&casillas[2][2]==2))&&(casillas[0][2]==0)){
               {ponerO(botonGato3); casillas[0][2]=2;R(109);}
            }
            else if(((casillas[0][0]==2&&casillas[1][0]==2)||(casillas[2][1]==2&&casillas[2][2]==2))&&(casillas[2][0]==0)){
               {ponerO(botonGato7); casillas[2][0]=2;R(110);}
            }
            
            else if(casillas[0][1]==2&&casillas[1][1]==2&&casillas[2][1]==0){ponerO(botonGato8); casillas[2][1]=2;R(111);}
            else if(casillas[2][1]==2&&casillas[1][1]==2&&casillas[0][1]==0){ponerO(botonGato2); casillas[0][1]=2;R(112);}
            else if(casillas[1][0]==2&&casillas[1][1]==2&&casillas[1][0]==0){ponerO(botonGato4); casillas[1][0]=2;R(114);}
            else if(casillas[0][2]==2&&casillas[1][1]==2&&casillas[2][0]==0){ponerO(botonGato7); casillas[2][0]=2;R(115);}
            
            //defensa
            else if((casillas[0][0]==1&&casillas[2][0]==1)&&(casillas[1][0]==0)){ponerO(botonGato4); casillas[1][0]=2;R(3);}
            else if((casillas[0][0]==1&&casillas[0][2]==1)&&(casillas[0][1]==0)){ponerO(botonGato2); casillas[0][1]=2;R(4);}
            else if((casillas[0][2]==1&&casillas[2][2]==1)&&(casillas[1][2]==0)){ponerO(botonGato6); casillas[1][2]=2;R(5);}
            else if((casillas[2][0]==1&&casillas[2][2]==1)&&(casillas[2][1]==0)){ponerO(botonGato8); casillas[2][1]=2;R(6);}
            
            else if(((casillas[2][0]==1&&casillas[2][1]==1)||(casillas[0][2]==1&&casillas[1][2]==1))&&(casillas[2][2]==0)){
               {ponerO(botonGato9); casillas[2][2]=2;R(7);}
            }
            else if(((casillas[1][0]==1&&casillas[2][0]==1)||(casillas[0][1]==1&&casillas[0][2]==1))&&(casillas[0][0]==0)){
               {ponerO(botonGato1); casillas[0][0]=2;R(8);}
            }
            else if(((casillas[0][0]==1&&casillas[0][1]==1)||(casillas[1][2]==1&&casillas[2][2]==1))&&(casillas[0][2]==0)){
               {ponerO(botonGato3); casillas[0][2]=2;R(9);}
            }
            else if(((casillas[0][0]==1&&casillas[1][0]==1)||(casillas[2][1]==1&&casillas[2][2]==1))&&(casillas[2][0]==0)){
               {ponerO(botonGato7); casillas[2][0]=2;R(10);}
            }

            else if(casillas[0][1]==1&&casillas[1][1]==1&&(casillas[2][1]==0)){ponerO(botonGato8); casillas[2][1]=2;R(11);}
            else if(casillas[2][1]==1&&casillas[1][1]==1&&(casillas[0][1]==0)){ponerO(botonGato2); casillas[0][1]=2;R(12);}
            else if(casillas[1][0]==1&&casillas[1][1]==1&&(casillas[1][2]==0)){ponerO(botonGato6); casillas[1][2]=2;R(13);}
            else if(casillas[1][2]==1&&casillas[1][1]==1&&(casillas[1][0]==0)){ponerO(botonGato4); casillas[1][0]=2;R(14);}
            else if(casillas[0][2]==1&&casillas[1][1]==1&&(casillas[2][0]==0)){ponerO(botonGato7); casillas[2][0]=2;R(15);}
            else if((casillas[2][0]==1||casillas[2][2]==1)&&casillas[1][1]==1&&(casillas[0][2]==0)){ponerO(botonGato3); casillas[0][2]=2;R(16);}
            
            //ataque
            else if((casillas[2][0]==2||casillas[2][2]==2)&&casillas[1][1]==2&&(casillas[0][2]==0)){ponerO(botonGato3); casillas[0][2]=2;R(116);}
            
            else if(casillas[1][1]==2&&casillas[2][2]==2&&casillas[0][0]==0){ponerO(botonGato1);casillas[0][0]=2;R(127);}
            else if(casillas[1][1]==2&&casillas[0][0]==2&&casillas[2][2]==0){ponerO(botonGato9);casillas[2][2]=2;R(128);}
            
            //defensa
            else if(casillas[1][0]==1&&casillas[2][1]==1&&casillas[2][0]==0){ponerO(botonGato7); casillas[2][0]=2;R(23);}
            else if(casillas[1][2]==1&&casillas[2][1]==1&&casillas[2][2]==0){ponerO(botonGato9); casillas[2][2]=2;R(24);}
            else if(casillas[1][0]==1&&casillas[0][1]==1&&casillas[0][0]==0){ponerO(botonGato1); casillas[0][0]=2;R(25);}
            else if(casillas[0][1]==1&&casillas[1][2]==1&&casillas[0][2]==0){ponerO(botonGato3); casillas[0][2]=2;R(26);}
            
            else if(casillas[0][1]==0){ponerO(botonGato2); casillas[0][1]=2;R(17);}
            
            else if(casillas[0][2]==0){ponerO(botonGato3); casillas[0][2]=2;R(18);}
            else if(casillas[2][0]==0){ponerO(botonGato7); casillas[2][0]=2;R(19);}
            
            else if(casillas[2][2]==0){ponerO(botonGato9); casillas[2][2]=2;R(20);}
            else if(casillas[0][0]==0){ponerO(botonGato1); casillas[0][0]=2;R(21);}
            
            else {
                int count = 0;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        count++;
                        if(casillas[i][j]==0){
                            switch(count){
                                case 1 -> {ponerO(botonGato1);}
                                case 2 -> {ponerO(botonGato2);}
                                case 3 -> {ponerO(botonGato3);}
                                case 4 -> {ponerO(botonGato4);}
                                case 5 -> {ponerO(botonGato5);}
                                case 6 -> {ponerO(botonGato6);}
                                case 7 -> {ponerO(botonGato7);}
                                case 8 -> {ponerO(botonGato8);}
                                case 9 -> {ponerO(botonGato9);}
                                    
                            }
                            casillas[i][j]=2;}
                            R(22);
                    }
                }
                System.out.println(count);
            }
            
            
            turno = "Usuario1";
            ventanaR.setVisible(false);
        }
        ganador();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonGato1 = new javax.swing.JButton();
        botonGato2 = new javax.swing.JButton();
        botonGato3 = new javax.swing.JButton();
        botonGato4 = new javax.swing.JButton();
        botonGato5 = new javax.swing.JButton();
        botonGato6 = new javax.swing.JButton();
        botonGato7 = new javax.swing.JButton();
        botonGato8 = new javax.swing.JButton();
        botonGato9 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        botonJuego = new javax.swing.JMenu();
        chkBotonVsIA = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        botonReinicio = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(3, 3));

        botonGato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato1ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato1);

        botonGato2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato2ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato2);

        botonGato3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato3ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato3);

        botonGato4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato4ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato4);

        botonGato5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato5ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato5);

        botonGato6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato6ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato6);

        botonGato7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato7ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato7);

        botonGato8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato8ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato8);

        botonGato9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGato9ActionPerformed(evt);
            }
        });
        jPanel1.add(botonGato9);

        botonJuego.setText("Juego");

        chkBotonVsIA.setText("vs IA");
        chkBotonVsIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBotonVsIAActionPerformed(evt);
            }
        });
        botonJuego.add(chkBotonVsIA);
        botonJuego.add(jSeparator1);

        botonReinicio.setText("Reiniciar Juego");
        botonReinicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReinicioActionPerformed(evt);
            }
        });
        botonJuego.add(botonReinicio);

        jMenuBar1.add(botonJuego);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato1ActionPerformed
        if (casillas[0][0] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato1);
                turno = "Usuario2";
                casillas[0][0] = 1;
            }
            else{
                ponerO(botonGato1);
                turno = "Usuario1";
                casillas[0][0] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato1ActionPerformed

    private void botonGato2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato2ActionPerformed
        if (casillas[0][1] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato2);
                turno = "Usuario2";
                casillas[0][1] = 1;
            }
            else{
                ponerO(botonGato2);
                turno = "Usuario1";
                casillas[0][1] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato2ActionPerformed

    private void botonGato3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato3ActionPerformed
        if (casillas[0][2] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato3);
                turno = "Usuario2";
                casillas[0][2] = 1;
            }
            else{
                ponerO(botonGato3);
                turno = "Usuario1";
                casillas[0][2] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato3ActionPerformed

    private void botonGato4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato4ActionPerformed
        if (casillas[1][0] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato4);
                turno = "Usuario2";
                casillas[1][0] = 1;
            }
            else{
                ponerO(botonGato4);
                turno = "Usuario1";
                casillas[1][0] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato4ActionPerformed

    private void botonGato5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato5ActionPerformed
        if (casillas[1][1] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato5);
                turno = "Usuario2";
                casillas[1][1] = 1;
            }
            else{
                ponerO(botonGato5);
                turno = "Usuario1";
                casillas[1][1] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato5ActionPerformed

    private void botonGato6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato6ActionPerformed
        if (casillas[1][2] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato6);
                turno = "Usuario2";
                casillas[1][2] = 1;
            }
            else{
                ponerO(botonGato6);
                turno = "Usuario1";
                casillas[1][2] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato6ActionPerformed

    private void botonGato7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato7ActionPerformed
        if (casillas[2][0] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato7);
                turno = "Usuario2";
                casillas[2][0] = 1;
            }
            else{
                ponerO(botonGato7);
                turno = "Usuario1";
                casillas[2][0] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato7ActionPerformed

    private void botonGato8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato8ActionPerformed
        if (casillas[2][1] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato8);
                turno = "Usuario2";
                casillas[2][1] = 1;
            }
            else{
                ponerO(botonGato8);
                turno = "Usuario1";
                casillas[2][1] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato8ActionPerformed

    private void botonGato9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGato9ActionPerformed
        if (casillas[2][2] == 0){
            if(turno.equals("Usuario1")){
                ponerX(botonGato9);
                turno = "Usuario2";
                casillas[2][2] = 1;
            }
            else{
                ponerO(botonGato9);
                turno = "Usuario1";
                casillas[2][2] = 2;
            }
            ganador();
            algoritmoIA(vsIA,turno);
        }
    }//GEN-LAST:event_botonGato9ActionPerformed

    private void botonReinicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReinicioActionPerformed
        reinicioJuego();
    }//GEN-LAST:event_botonReinicioActionPerformed

    private void chkBotonVsIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBotonVsIAActionPerformed
        vsIA = true;
        reinicioJuego();
    }//GEN-LAST:event_chkBotonVsIAActionPerformed

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
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGato1;
    private javax.swing.JButton botonGato2;
    private javax.swing.JButton botonGato3;
    private javax.swing.JButton botonGato4;
    private javax.swing.JButton botonGato5;
    private javax.swing.JButton botonGato6;
    private javax.swing.JButton botonGato7;
    private javax.swing.JButton botonGato8;
    private javax.swing.JButton botonGato9;
    private javax.swing.JMenu botonJuego;
    private javax.swing.JMenuItem botonReinicio;
    private javax.swing.JCheckBoxMenuItem chkBotonVsIA;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
