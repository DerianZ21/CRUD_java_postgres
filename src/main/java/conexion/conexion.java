/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class conexion {
    Connection conectar = null;
    
    String usuario = "postgres";
    String contraseña = "d123456";
    String dataBase = "bdescuela";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+dataBase;
    
    public Connection iniciarConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
        
            JOptionPane.showMessageDialog(null, "Se conectó correctamente a la base de datos ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        }
        
        return conectar;
    }
}
 