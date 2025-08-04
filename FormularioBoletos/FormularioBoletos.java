package FormularioBoletos;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FormularioBoletos extends JFrame {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel modelo;

  public FormularioBoletos() {
    initComponents(); 
    conectar();        
    cargarTabla();     

    // Y luego agregas las acciones
    btnAgregar.addActionListener(e -> agregarBoleto());
    btnActualizar.addActionListener(e -> actualizarBoleto());
    btnEliminar.addActionListener(e -> eliminarBoleto());
    btnLimpiar.addActionListener(e -> limpiarCampos());
}

    public void conectar() {
        try {
            con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/concierto_boleto_db",
                    
                "postgres", "admin"
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) tablaBoletos.getModel();
        modelo.setRowCount(0);

        try {
            ps = con.prepareStatement("SELECT * FROM boletos");
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id"),
                    rs.getString("nombre_cliente"),
                    rs.getString("correo"),
                    rs.getString("artista"),
                    rs.getInt("cantidad"),
                    rs.getString("zona")
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
        }
    }

    public void agregarBoleto() {
        try {
            ps = con.prepareStatement("INSERT INTO boletos (nombre_cliente, correo, artista, cantidad, zona) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtCorreo.getText());
            ps.setString(3, comboArtista.getSelectedItem().toString());
            ps.setInt(4, Integer.parseInt(txtCantidad.getText()));
            ps.setString(5, comboZona.getSelectedItem().toString());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Boleto agregado correctamente.");
            cargarTabla();
            limpiarCampos();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar: " + e.getMessage());
        }
    }

    public void actualizarBoleto() {
        int fila = tablaBoletos.getSelectedRow();
        if (fila >= 0) {
            try {
                ps = con.prepareStatement("UPDATE boletos SET nombre_cliente=?, correo=?, artista=?, cantidad=?, zona=? WHERE id=?");
                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtCorreo.getText());
                ps.setString(3, comboArtista.getSelectedItem().toString());
                ps.setInt(4, Integer.parseInt(txtCantidad.getText()));
                ps.setString(5, comboZona.getSelectedItem().toString());
                ps.setInt(6, (int) modelo.getValueAt(fila, 0));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
                cargarTabla();
                limpiarCampos();
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para actualizar.");
        }
    }

    public void eliminarBoleto() {
        int fila = tablaBoletos.getSelectedRow();
        if (fila >= 0) {
            try {
                ps = con.prepareStatement("DELETE FROM boletos WHERE id=?");
                ps.setInt(1, (int) modelo.getValueAt(fila, 0));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
                cargarTabla();
                limpiarCampos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar.");
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtCorreo.setText("");
        txtCantidad.setText("");
        comboArtista.setSelectedIndex(0);
        comboZona.setSelectedIndex(0);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBoletos = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        comboArtista = new javax.swing.JComboBox<>();
        comboZona = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel1.setText("NOMBRE CLIENTE ");

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel2.setText("CORREO");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setText("ARTISTA");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setText("CANTIDAD");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setText("ZONA");

        btnAgregar.setBackground(new java.awt.Color(0, 51, 51));
        btnAgregar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("AGREGAR");
        btnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnActualizar.setBackground(new java.awt.Color(0, 51, 51));
        btnActualizar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnEliminar.setBackground(new java.awt.Color(0, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("BORRAR");
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnLimpiar.setBackground(new java.awt.Color(0, 51, 51));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaBoletos.setBackground(new java.awt.Color(204, 255, 204));
        tablaBoletos.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        tablaBoletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "CORREO", "ARTISTA", "CANTIDAD", "ZONA"
            }
        ));
        jScrollPane1.setViewportView(tablaBoletos);

        txtNombre.setBorder(null);

        txtCorreo.setBorder(null);

        txtCantidad.setBorder(null);

        comboArtista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maria Becerra", "Tiago PZK", "Feid", "Rels B", "Junior H" }));
        comboArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArtistaActionPerformed(evt);
            }
        });

        comboZona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIP", "General", "Gradas" }));
        comboZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboZonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(565, 565, 565))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(102, 102, 102))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnAgregar)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(btnActualizar)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnLimpiar)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboZonaActionPerformed

    private void comboArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArtistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboArtistaActionPerformed
   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioBoletos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboArtista;
    private javax.swing.JComboBox<String> comboZona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaBoletos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
