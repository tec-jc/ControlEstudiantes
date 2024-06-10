package interfazgrafica;

import entitadesdenegocio.Estudiante;
import logicadenegocio.EstudianteBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControlEstudiantes extends JFrame {
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

    // instancias propias
    ArrayList<Estudiante> listEstudiantes;
    Estudiante student;
    EstudianteBL estudianteBL = new EstudianteBL();

    public static void main(String[] args) throws SQLException {
        new ControlEstudiantes();
    }

    public ControlEstudiantes() throws SQLException{
        inicializar();
        actualizarForm();
    }

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

        llenarTabla(estudianteBL.obtenerTodos());
    }
}
