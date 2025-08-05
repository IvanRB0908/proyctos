import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormularioAlumnos extends javax.swing.JFrame {
 Connection con;
  PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel modelo;
    
    public FormularioAlumnos() {
        initComponents();
        conectar();
        cargarTabla();
        
    btnAgregar.addActionListener(e -> agregarAlumno());
    btnActualizar.addActionListener(e -> actualizarAlumno());
    btnEliminar.addActionListener(e -> eliminarAlumno());
    btnLimpiar.addActionListener(e -> limpiarCampos());
    }
 public void conectar() {
        try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnos_db", "root", "fernando1234");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }
    }

  public void cargarTabla() {
    String[] columnas = {"ID", "Nombre", "Apellido", "Edad", "Correo", "Carrera"};
    modelo = new DefaultTableModel(null, columnas);
    tablaAlumnos.setModel(modelo);

    try {
        ps = con.prepareStatement("SELECT * FROM alumnos");
        rs = ps.executeQuery();
        while (rs.next()) {
            Object[] fila = {
                rs.getInt("Id"),
                rs.getString("Nombre"),
                rs.getString("Apellido"),
                rs.getInt("Edad"),
                rs.getString("Correo"),
                rs.getString("Carrera")
            };
            modelo.addRow(fila);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
    }
}

    public void agregarAlumno() {
    String nombre = txtNombre.getText();
    String apellido = txtApellido.getText();
    String edadTexto = txtEdad.getText();
    String correo = txtCorreo.getText();
    String carrera = txtCarrera.getText();

    if (nombre.isEmpty() || apellido.isEmpty() || edadTexto.isEmpty() || correo.isEmpty() || carrera.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor llena todos los campos.");
        return;
    }

    try {
        int edad = Integer.parseInt(edadTexto);

        ps = con.prepareStatement("INSERT INTO alumnos (nombre, apellido, edad, correo, carrera) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setInt(3, edad);
        ps.setString(4, correo);
        ps.setString(5, carrera);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Alumno agregado correctamente.");
        cargarTabla();
        limpiarCampos();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Edad debe ser un número válido.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al agregar: " + e.getMessage());
    }
}

    public void actualizarAlumno() {
    int fila = tablaAlumnos.getSelectedRow();
    if (fila >= 0) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edadTexto = txtEdad.getText();
        String correo = txtCorreo.getText();
        String carrera = txtCarrera.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || edadTexto.isEmpty() || correo.isEmpty() || carrera.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor llena todos los campos.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadTexto);
            int id = (int) modelo.getValueAt(fila, 0);

            ps = con.prepareStatement("UPDATE alumnos SET nombre=?, apellido=?, edad=?, correo=?, carrera=? WHERE id=?");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, correo);
            ps.setString(5, carrera);
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alumno actualizado correctamente.");
            cargarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad debe ser un número valido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Selecciona un alumno en la tabla para actualizar.");
    }
}

    public void eliminarAlumno() {
        int fila = tablaAlumnos.getSelectedRow();
        if (fila >= 0) {
            try {
                ps = con.prepareStatement("DELETE FROM alumnos WHERE id=?");
                ps.setInt(1, (int) modelo.getValueAt(fila, 0));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.");
                cargarTabla();
                limpiarCampos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un alumno para eliminar.");
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");
        txtCarrera.setText("");
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtCarrera = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        txtNombre.setBorder(null);

        txtApellido.setBorder(null);
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

        txtEdad.setBorder(null);

        txtCorreo.setForeground(new java.awt.Color(204, 204, 204));
        txtCorreo.setBorder(null);

        txtCarrera.setBorder(null);

        btnAgregar.setBackground(new java.awt.Color(0, 51, 153));
        btnAgregar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("AGREGAR");

        btnActualizar.setBackground(new java.awt.Color(0, 51, 153));
        btnActualizar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR");

        btnEliminar.setBackground(new java.awt.Color(0, 51, 153));
        btnEliminar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");

        btnLimpiar.setBackground(new java.awt.Color(0, 51, 153));
        btnLimpiar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR");

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel1.setText("NOMBRE");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel4.setText("APELLIDO");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel5.setText("EDAD");

        jLabel6.setBackground(new java.awt.Color(102, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel6.setText("CORREO");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 14)); // NOI18N
        jLabel7.setText("CARRERA");

        tablaAlumnos.setBackground(new java.awt.Color(204, 255, 255));
        tablaAlumnos.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaAlumnos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(86, 86, 86)
                                        .addComponent(btnActualizar)
                                        .addGap(92, 92, 92)
                                        .addComponent(btnEliminar)
                                        .addGap(96, 96, 96)
                                        .addComponent(btnLimpiar)))
                                .addGap(42, 42, 42))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(184, 184, 184))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

  public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioAlumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
