/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tareaarreglada;


import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dashs
 */
public class Cant_TOG extends javax.swing.JFrame {

    /**
     * Creates new form Cant_TOG
     */
    public Cant_TOG() {
        initComponents();
        setLocationRelativeTo(null); // Centrar en la pantalla
        setResizable(false); // Desactivar la posibilidad de redimensionar
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ATG = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Manual", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Ingrese el Tipo y el Grupo del animal");

        ATG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ATGActionPerformed(evt);
            }
        });

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ATG))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(ATG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel2.setText("Seleccione el  Tipo y el Grupo del animal");

        jButton3.setText("Aceptar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anfibios", "Artrópodos", "Aves", "Celentéreos", "Equinodermos", "Gusanos", "Mamíferos", "Moluscos", "Peces", "Poríferos", "Reptiles", "Vertebrados", "Invertebrados", "Vertebrados Mamíferos", "Vertebrados Aves", "Vertebrados Peces", "Vertebrados Anfibios", "Vertebrados Reptiles", "Invertebrados Artrópodos", "Invertebrados Moluscos", "Invertebrados Equinodermos", "Invertebrados Gusanos", "Invertebrados Poríferos", "Invertebrados Celentéreos" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(53, 53, 53))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jButton4.setText("Volver");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton4)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ATGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ATGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ATGActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Esta es la accion del boton volver hacemos la nueva instancia del Menu y luego le decimos que se haga visible para acto despues usar this.dispose() para cerrar la ventana actual.
        Menu volver = new Menu();
        volver.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String itemSeleccionado = jComboBox1.getSelectedItem().toString();
        
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Capturamos lo entregado en el JTextField cuyo nombre de variable es ATG en una variable tipo String
        String nombre = ATG.getText();
        CAnimal cant = new CAnimal();
        int cantidad = 0;
        //El nombre de la ListaBlanca lo dice todo haremos que el String donde se guardo el JTextField pase por un filtro
        List<String> ListaBlanca = Arrays.asList("Invertebrados","Vertebrados","Mamíferos", "Anfibios", "Artrópodos", "Aves", "Celentéreos", "Equinodermos", "Gusanos", "Moluscos", "Peces", "Poríferos", "Reptiles", "Vertebrados Mamíferos", "Vertebrados Aves", "Vertebrados Peces", "Vertebrados Anfibios", "Vertebrados Reptiles", "Invertebrados Artrópodos", "Invertebrados Moluscos", "Invertebrados Equinodermos", "Invertebrados Gusanos", "Invertebrados Poríferos", "Invertebrados Celentéreos");
        //Primer filtro, este nos dira que cuando nuestro String nombre no este dentro de la lista le arrojaremos este error
        if (!ListaBlanca.contains(nombre)) {
            JOptionPane.showMessageDialog(null, "El tipo o grupo de animales ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Si pasa el filtro ya entraremos a este else donde se buscara el nombre tipo o grupos del cual se quiere saber la cantidad y iniciamos la conexion para realizar acciones con la base de datos
        else {
            Conexion cuenta = new Conexion();
            //Si el nombre esta aqui lo mandamos al metodo de CAnimal obtenerCantidadFilas
            if (nombre.equals("Mamíferos") || nombre.equals("Anfibios") || nombre.equals("Artrópodos") || nombre.equals("Aves") || nombre.equals("Celentéreos") || nombre.equals("Equinodermos") || nombre.equals("Gusanos") || nombre.equals("Moluscos") || nombre.equals("Peces") || nombre.equals("Poríferos") || nombre.equals("Reptiles")) {
                cantidad = cant.obtenerCantidadFilas(nombre); //Una vez llamado al obtenerCantidadFilas le pasamos el String nombre que sera considerado dentro del obtenerCantidadFilas como el nombre de la tabla de la cual queremos saber su cantidad de filas 
            }
            if (nombre.equals("Vertebrados")){
                cantidad = cant.obtenerCantidadFilas("mamíferos")+ cant.obtenerCantidadFilas("aves")+cant.obtenerCantidadFilas("reptiles") + cant.obtenerCantidadFilas("peces") + cant.obtenerCantidadFilas("anfibios");
            }
            if(nombre.equals("Invertebrados")){
                cantidad = cant.obtenerCantidadFilas("artrópodos")+ cant.obtenerCantidadFilas("moluscos")+cant.obtenerCantidadFilas("equinodermos") + cant.obtenerCantidadFilas("gusanos") + cant.obtenerCantidadFilas("poríferos")+ cant.obtenerCantidadFilas("celentéreos");
            }
            if (nombre.equalsIgnoreCase("Vertebrados Mamíferos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Aves")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(4);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Peces")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(2);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Anfibios")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(5);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Reptiles")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(3);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Artrópodos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(6);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Moluscos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(7);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Equinodermos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(8);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Gusanos")){
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(9);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Poríferos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(10);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Celentéreos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(11);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Aqui por ultimo le mandamos un mensaje al usuario
            JOptionPane.showMessageDialog(null,"La cantidad de "+ nombre +" es de: "+ cantidad);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Rescatamos los seleccionado en el combobox y lo guardamos en un string
        String nombre = jComboBox1.getSelectedItem().toString();
        CAnimal cant = new CAnimal();
        int cantidad = 0;
        Conexion cuenta = new Conexion();
        //Este metodo es parecido al primero pero al ser un comboBox no es necesario que pase por un filtro debido a que el usuario no puede cometer errores aqui
        if (nombre.equals("Mamíferos") || nombre.equals("Anfibios") || nombre.equals("Artrópodos") || nombre.equals("Aves") || nombre.equals("Celentéreos") || nombre.equals("Equinodermos") || nombre.equals("Gusanos") || nombre.equals("Moluscos") || nombre.equals("Peces") || nombre.equals("Poríferos") || nombre.equals("Reptiles")) {
                cantidad = cant.obtenerCantidadFilas(nombre);//Una vez llamado al obtenerCantidadFilas le pasamos el String nombre que sera considerado dentro del obtenerCantidadFilas como el nombre de la tabla de la cual queremos saber su cantidad de filas 
            }
            if (nombre.equals("Vertebrados")){
                cantidad = cant.obtenerCantidadFilas("mamíferos")+ cant.obtenerCantidadFilas("aves")+cant.obtenerCantidadFilas("reptiles") + cant.obtenerCantidadFilas("peces") + cant.obtenerCantidadFilas("anfibios");
            }
            if(nombre.equals("Invertebrados")){
                cantidad = cant.obtenerCantidadFilas("artrópodos")+ cant.obtenerCantidadFilas("moluscos")+cant.obtenerCantidadFilas("equinodermos") + cant.obtenerCantidadFilas("gusanos") + cant.obtenerCantidadFilas("poríferos")+ cant.obtenerCantidadFilas("celentéreos");
            }
            if (nombre.equalsIgnoreCase("Vertebrados Mamíferos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Aves")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(4);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Peces")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(2);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Anfibios")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(5);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Vertebrados Reptiles")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(3);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Artrópodos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(6);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Moluscos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(7);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Equinodermos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(8);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Gusanos")){
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(9);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Poríferos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(10);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nombre.equalsIgnoreCase("Invertebrados Celentéreos")) {
                try {
                    cantidad = cant.BuscadorTablaGeneralGrupos(11);
                } catch (SQLException ex) {
                    Logger.getLogger(Cant_TOG.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        JOptionPane.showMessageDialog(null,"La cantidad de "+ nombre +" es de: "+ cantidad);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Unicamente limpia lo escrito en el JTextField dejandolo vacio o conteniendo  una cadena de texto vacia
        ATG.setText("");
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Cant_TOG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cant_TOG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cant_TOG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cant_TOG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cant_TOG().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ATG;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}