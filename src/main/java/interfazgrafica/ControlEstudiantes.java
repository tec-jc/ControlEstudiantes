package interfazgrafica;

import entitadesdenegocio.Estudiante;
import logicadenegocio.EstudianteBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControlEstudiantes extends JFrame {
    // declaración de instancias para controles con nombre (generadas de forma automática)
    private JPanel jpPrincipal;
    private JTextField txtId;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JComboBox cbCarrera;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rdbId;
    private JRadioButton rdbApellido;
    private JRadioButton rdbCarrera;
    private JTextField txtCriterio;
    private JButton btnBuscar;
    private JTable tbEstudiantes;
    private ButtonGroup criterioBusqueda;

    // instancias propias (creadas por nosotros)
    ArrayList<Estudiante> listEstudiantes;
    Estudiante student;
    EstudianteBL estudianteBL = new EstudianteBL();

    // declaración del método main, permite que clase sea ejecutable
    public static void main(String[] args) throws SQLException {
        new ControlEstudiantes();
    }

    // Método constructor que llama a los métodos que inicializan la ventana y dan estado inicial al formulario.
    // También están las sobrescrituras para dar la funcionalidad a los botones.
    public ControlEstudiantes() throws SQLException{
        inicializar();
        actualizarForm();

        // funcionalidad del botón Nuevo
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                cbCarrera.setEnabled(true);
                txtCodigo.grabFocus();
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });

        // funcionalidad del botón Guardar
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                student = new Estudiante();
                student.setCodigo(txtCodigo.getText());
                student.setNombre(txtNombre.getText());
                student.setApellido(txtApellido.getText());
                student.setCarrera(cbCarrera.getSelectedItem().toString());
                try{
                    estudianteBL.guardar(student);
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del botón Salir
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        // funcionalidad del botón Cancelar
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del clic sobre la Tabla
        tbEstudiantes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = tbEstudiantes.getSelectedRow();
                txtId.setText(tbEstudiantes.getValueAt(fila, 0).toString());
                txtCodigo.setText(tbEstudiantes.getValueAt(fila, 1).toString());
                txtNombre.setText(tbEstudiantes.getValueAt(fila, 2).toString());
                txtApellido.setText(tbEstudiantes.getValueAt(fila, 3).toString());
                cbCarrera.setSelectedItem(tbEstudiantes.getValueAt(fila, 4));

                txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                cbCarrera.setEnabled(true);
                txtCodigo.grabFocus();

                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });

        // funcionalidad del botón Modificar
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                student = new Estudiante();
                student.setId(Integer.parseInt(txtId.getText()));
                student.setCodigo(txtCodigo.getText());
                student.setNombre(txtNombre.getText());
                student.setApellido(txtApellido.getText());
                student.setCarrera(cbCarrera.getSelectedItem().toString());
                try {
                    estudianteBL.modificar(student);
                    JOptionPane.showMessageDialog(null, "Se modificó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del botón Eliminar
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                student = new Estudiante();
                student.setId(Integer.parseInt(txtId.getText()));
                try{
                    estudianteBL.eliminar(student);
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del botón Buscar
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(txtCriterio.getText().equals("") || (!rdbId.isSelected() &&
                        !rdbApellido.isSelected() && !rdbCarrera.isSelected()) ){
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un criterio de búsqueda o escriba el valor a buscar");
                }

                student = new Estudiante();

                if(rdbId.isSelected()){
                    student.setId(Integer.parseInt(txtCriterio.getText()));
                    try{
                        llenarTabla(estudianteBL.obtenerDatosFiltrados(student));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbApellido.isSelected()){
                    student.setApellido(txtCriterio.getText());
                    try{
                        llenarTabla(estudianteBL.obtenerDatosFiltrados(student));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbCarrera.isSelected()){
                    student.setCarrera(txtCriterio.getText());
                    try{
                        llenarTabla(estudianteBL.obtenerDatosFiltrados(student));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    // método para inicializar la ventana
    void inicializar(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 700);
        setTitle("Control de Estudiantes");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        criterioBusqueda = new ButtonGroup();
        criterioBusqueda.add(rdbId);
        criterioBusqueda.add(rdbApellido);
        criterioBusqueda.add(rdbCarrera);

        setContentPane(jpPrincipal);
        setVisible(true);
    }

    // método para llenar la tabla con los datos obtenidos de la base de datos
    void llenarTabla(ArrayList<Estudiante> estudiantes){
        Object[] obj = new Object[5];
        listEstudiantes = new ArrayList<>();
        String[] encabezado = {"ID", "CÓDIGO", "NOMBRE", "APELLIDO", "CARRERA"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listEstudiantes.addAll(estudiantes);
        for(Estudiante item : listEstudiantes){
            obj[0] = item.getId();
            obj[1] = item.getCodigo();
            obj[2] = item.getNombre();
            obj[3] = item.getApellido();
            obj[4] = item.getCarrera();

            tm.addRow(obj);
        }
        tbEstudiantes.setModel(tm);
    }

    // método para dar estado inicial al formulario
    void actualizarForm() throws SQLException {
        txtId.setText("");
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        cbCarrera.setSelectedItem(0);

        txtId.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        cbCarrera.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        criterioBusqueda.clearSelection();
        txtCriterio.setText("");

        llenarTabla(estudianteBL.obtenerTodos());
    }
}
