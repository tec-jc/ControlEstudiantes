import accesoadatos.EstudianteDAL;
import entitadesdenegocio.Estudiante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class EstudianteDALTest {
    @Test
    public void guardarTest() throws SQLException {
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo("TR23009");
        estudiante.setNombre("Juan Antonio");
        estudiante.setApellido("Tobar Rodriguez");
        estudiante.setCarrera("TIDS");

        int esperado = 1;
        int actual = EstudianteDAL.guardar(estudiante);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException {
        int esperado = 2;
        int actual = EstudianteDAL.obtenerTodos().size();
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modificarTest() throws SQLException{
        Estudiante estudiante = new Estudiante();
        estudiante.setId(2);
        estudiante.setCodigo("TR23009");
        estudiante.setNombre("Juan Antonio");
        estudiante.setApellido("Tobar Rodriguez");
        estudiante.setCarrera("TIE");

        int esperado = 1;
        int actual = EstudianteDAL.modificar(estudiante);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void eliminarTest() throws SQLException{
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1);

        int esperado = 1;
        int actual = EstudianteDAL.eliminar(estudiante);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException{
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo("TR23009");
        estudiante.setId(0);
        estudiante.setCarrera("");

        int actual = EstudianteDAL.obtenerDatosFiltrados(estudiante).size();
        Assertions.assertNotEquals(0, actual);
    }
}
