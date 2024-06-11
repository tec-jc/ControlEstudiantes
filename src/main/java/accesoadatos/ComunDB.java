package accesoadatos;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class ComunDB {
    // cadena de conexión a la base de datos
    private static String cadenaConexion = "jdbc:mysql://localhost:3306/ControlEstudiantes?user=root&password=AdminRoot2024";

    // método que permite conectar con la base de datos
    public static Connection obtenerConexion() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection conexion = DriverManager.getConnection(cadenaConexion);
        return conexion;
    }

    // método que permite configurar el comando a ejecutar en la base de datos
    public static PreparedStatement crearPreparedStatement(Connection conexion, String sql) throws SQLException{
        PreparedStatement ps = conexion.prepareStatement(sql);
        return ps;
    }

    // método que permite ejecutar una consulta de tipo Select
    public static ResultSet obtenerResultSet(PreparedStatement ps) throws SQLException{
        ResultSet resultSet = ps.executeQuery();
        return resultSet;
    }
}
