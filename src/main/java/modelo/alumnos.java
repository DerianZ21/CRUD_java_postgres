
package modelo;

import conexion.conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class alumnos {
    
   int id;
   String nombre;
   String apellido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    public void mostrarAlumnos(JTable paramTablaDatosAlumnos){
        conexion objConexion = new conexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql ="";
        
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        
        paramTablaDatosAlumnos.setModel(modelo);
        
        sql= "select  * from Alumnos;";
        String [] datos = new String[3];
        Statement st;
        
        try {
            st=objConexion.iniciarConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            paramTablaDatosAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:"+e.toString());
        }
    }  
    
    public void insertarAlumno(JTextField paramNombre, JTextField paramApellido){
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa el nombre y apellido del alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            setNombre(nombre);
            setApellido(apellido);
            conexion objConexion = new conexion();
        
            String consulta = "insert into alumnos (nombres, apellidos)  values(?, ?);";

            try {
                CallableStatement cs = objConexion.iniciarConexion().prepareCall(consulta);
                cs.setString(1, getNombre());
                cs.setString(2, getApellido());

                cs.execute();

                JOptionPane.showMessageDialog(null, "Se insertó correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR:"+e.toString());
            }
        }
    }
    
    public void seleccionarAlumno(JTable paramTablaAlumno, JTextField paramCodigo, JTextField paramNombre,  JTextField paramApellido){
        try {
            int fila = paramTablaAlumno.getSelectedRow();
            if(fila>=0){
                paramCodigo.setText(paramTablaAlumno.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTablaAlumno.getValueAt(fila, 1).toString());
                paramApellido.setText(paramTablaAlumno.getValueAt(fila, 2).toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error al seleccionar la fila");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:"+e.toString());
        }
    }
    
    public void modificarAlumno(JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido){
        setId(Integer.parseInt(paramCodigo.getText()));
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa el nombre y apellido del alumno.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            conexion objConexion = new conexion();

            String consulta = "UPDATE alumnos set nombres = ?, apellidos = ? where id = ?;";

            try {
                CallableStatement cs = objConexion.iniciarConexion().prepareCall(consulta);
                cs.setString(1, getNombre());
                cs.setString(2, getApellido());
                cs.setInt(3, getId());

                cs.execute();

                JOptionPane.showMessageDialog(null, "Se modificó correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR:"+e.toString());
            }
        }
    }
    
    public void eliminarAlumno(JTextField paramCodigo){
        setId(Integer.parseInt(paramCodigo.getText()));
        
        
        conexion objConexion = new conexion();
        
        String consulta = "delete from alumnos where alumnos.id=?;";
        
        try {
            CallableStatement cs = objConexion.iniciarConexion().prepareCall(consulta);
            
            cs.setInt(1, getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:"+e.toString());
        }
    }
    
    
}
