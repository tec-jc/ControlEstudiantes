package logicadenegocio;

import accesoadatos.EstudianteDAL;
import entitadesdenegocio.Estudiante;

import java.sql.SQLException;
import java.util.ArrayList;

public class EstudianteBL {
    // métodos de la clase BL que devuelven la funcionalidad de los métodos de la clase DAL
    public int guardar(Estudiante estudiante) throws SQLException {
        return EstudianteDAL.guardar(estudiante);
    }

    public int modificar(Estudiante estudiante) throws SQLException{
        return EstudianteDAL.modificar(estudiante);
    }

    public int eliminar(Estudiante estudiante) throws SQLException{
        return EstudianteDAL.eliminar(estudiante);
    }

    public ArrayList<Estudiante> obtenerTodos() throws SQLException{
        return EstudianteDAL.obtenerTodos();
    }

    public ArrayList<Estudiante> obtenerDatosFiltrados(Estudiante estudiante) throws SQLException{
        return EstudianteDAL.obtenerDatosFiltrados(estudiante);
    }
}
