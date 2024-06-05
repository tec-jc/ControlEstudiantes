package accesoadatos;

import entitadesdenegocio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstudianteDAL {
    // método que muestra todos los registros de la tabla
    public static ArrayList<Estudiante> obtenerTodos() throws SQLException {
        ArrayList<Estudiante> lista = new ArrayList<>();
        Estudiante estudiante;
        try{
            String sql = "SELECT Id, Codigo, Nombre, Apellido, Carrera FROM Estudiantes";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                estudiante = new Estudiante(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
                lista.add(estudiante);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }

    // método que permite guardar un nuevo registro
    public static int guardar(Estudiante estudiante) throws SQLException{
        int result = 0;
        try {
            String sql = "INSERT INTO Estudiantes(Codigo, Nombre, Apellido, Carrera) VALUES(?, ?, ?, ?)";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, estudiante.getCodigo());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setString(4, estudiante.getCarrera());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite modificar un registro existente
    public static int modificar(Estudiante estudiante) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE Estudiantes SET Codigo = ?,  Nombre = ?, Apellido = ?, Carrera = ? WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, estudiante.getCodigo());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setString(4, estudiante.getCarrera());
            ps.setInt(5, estudiante.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(Estudiante estudiante) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Estudiantes WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setInt(1, estudiante.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método para consultar mediante criterios
    public static ArrayList<Estudiante> obtenerDatosFiltrados(Estudiante estudiante) throws SQLException{
        ArrayList<Estudiante> lista = new ArrayList<>();
        Estudiante est;
        try{
            String sql = "SELECT id, codigo, nombre, apellido, carrera FROM Estudiantes WHERE id = ? or apellido like'%" + estudiante.getApellido() + "%' or carrera like'%" + estudiante.getCarrera() + "%'";
            Connection connection = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(connection, sql);
            ps.setInt(1, estudiante.getId());
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                est = new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                lista.add(est);
            }
            connection.close();
            ps.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
