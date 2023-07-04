package tareaarreglada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {

    String url = "jdbc:mysql://localhost:3306/animales";
    String user = "root";
    String password = "1234567";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection conectar;
    public Connection estableceConexion(){
        try{
            Class.forName(driver);
            conectar=DriverManager.getConnection(url, user, password);
            //JOptionPane.showMessageDialog(null,"Conexion establecida con BD");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error de conexion con BD");
            }
        return conectar;
        }
    }
