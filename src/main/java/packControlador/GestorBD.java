package packControlador;

import java.sql.*;

public class GestorBD {

    private static GestorBD mGestorBD;
    private final String host = "localhost";
    private final String puerto = "3306";
    private final String nombreBD = "buscaminas";
    private final String usuarioBD = "root";
    private final String contrasenaBD = "root";
    private final String url = "jdbc:mysql://" + host + ":" + puerto + "/" + nombreBD + "?allowPublicKeyRetrieval=true&useSSL=false&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection con;
    private Statement stmt;

    private GestorBD() {}

    public static GestorBD getGestorBD() {
        if (mGestorBD == null) {
            mGestorBD = new GestorBD();
        }
        return mGestorBD;
    }

    private Connection conn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuarioBD, contrasenaBD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("No se ha podido establecer la conexion con la base de datos");
        }
        return conn;
    }

    public ResultSet ejecutarConsulta(String consulta) {
        this.con= this.conn();
        try {
            stmt = con.createStatement();
            return stmt.executeQuery(consulta);
        } catch (SQLException e) {
            return null;
        }
    }

    public void ejecutarCambio(String pSentencia) {
        this.con = this.conn();
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(pSentencia);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cerrarConexion(){
        try {
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
