/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareaarreglada;;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.SwingConstants;


/**
 *
 * @author dashs
 */
public class CAnimal {
    
    int codigo;
    String nombreAnimal;
    String tipoAnimal;
    String grupoAnimal;
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }

    public void setNombreAnimal(String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getGrupoAnimal() {
        return grupoAnimal;
    }

    public void setGrupoAnimal(String grupoAnimal) {
        this.grupoAnimal = grupoAnimal;
    }

    public void InsertarAnimal( JTextField paramAnimal,JTextField paramTipo,JTextField paramGrupo){//se recibe el nombre, el tipo y el grupo del animal

        setNombreAnimal(paramAnimal.getText());
        setTipoAnimal(paramTipo.getText());
        setGrupoAnimal(paramGrupo.getText());

        Conexion con = new Conexion();

        String consulta = ("INSERT INTO animales_generales (Animal,Tipo,Grupo) VALUES (?,?,?);");//se crea una consulta para ingresar al animal dentro de la tabla general

        try {
            CallableStatement cs = con.estableceConexion().prepareCall(consulta);//se establece la conexion con la base de datos

            cs.setString(1,getNombreAnimal());//se ingresan  los datos 
            cs.setString(2,getTipoAnimal());
            cs.setString(3,getGrupoAnimal());

            cs.execute();//se ejecuta la consulta y se ingresan los datos a mysql

            JOptionPane.showMessageDialog(null, "Se inserto correctamente el animal");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el animal"+e.toString());
        }

    }

    
    
    public void MostrarAnimales(JTable paramTablaTotalAnimales) {//Aqui recibe de parametros una tabla

        Conexion con = new Conexion();//se crea una conexion a la base de datos

        DefaultTableModel modelo = new DefaultTableModel();//se crea una modelo de tabla 

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAnimales.setRowSorter(OrdenarTabla);//se ordenaran los valores de la tabla

        String sql="";

        modelo.addColumn("ID");//se le dan columnas a la tabla modelo
        modelo.addColumn("Animal");
        modelo.addColumn("Tipo");
        modelo.addColumn("Grupo");

        paramTablaTotalAnimales.setModel(modelo);//la tabla que se muestra por pantalla toma los valores de la tabla modelo

        sql = "SELECT * FROM animales_generales";//se crea una consulta sql en la cual  se llama a la tabla general para mostrarla 

        String[] datos = new String[4];
        Statement st;


        try{
            st=(Statement) con.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);//se ejecuta la consulta en la query de mysql obteniendo los datos de esta

            while(rs.next()){
                datos[0] = rs.getString(1);//se obtienen los datos de la tabla 
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                modelo.addRow(datos);//se le agregan los datos a la tabla modelo
            }

            paramTablaTotalAnimales.setModel(modelo);//la tabla que se muestra por pantalla toma los valores de la tabla modelo actualizada

        }catch( Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
        }

    }
    public void MostrarListas(JTable paramTablaTotalAnimales, String nomGrupo) {//se recibe la tabla y el nombre del grupo

        Conexion con = new Conexion();//conexion a la base de datos

        DefaultTableModel modelo = new DefaultTableModel();//se crea una tabla vacia

        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAnimales.setRowSorter(OrdenarTabla);//se ordena la tabla

        String sql="";//se crea una consulta vacia

        //con if compararemos a el grupo recibido y entraremos en su apartado, en este se crearan los valores de su columnas y se creara su consulta sql, luego de eso se obtendra la informaicon del mysql y se enviaran a la tabla
        if("Mamíferos".equals(nomGrupo)){
            modelo.addColumn("Nombre");//se crean valores de el grupo seleccionado
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_Patas");

            sql = "SELECT * FROM mamíferos";//se crea una consulta para el grupo seleccionado
            paramTablaTotalAnimales.setModel(modelo);//se le dan las columnas a la tabla recibida

            String[] datos = new String[4];
            Statement st;

            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);//se realiza la consulta a el query en mysql y se obtiene l ainfromacion

                while(rs.next()){
                    datos[0] = rs.getString(1);//se obtienen los valores 
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);//se le asignan los valores a la tabla modelo
                }

                paramTablaTotalAnimales.setModel(modelo);//se ñe dan los valores de la tabla modelo a la tabla que se visualizara

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }

        } 
        if("Aves".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_alas");
            
            sql = "SELECT * FROM aves";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;

            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        } 
        if("Peces".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_Aletas");
            modelo.addColumn("Escamas");
            
            sql = "SELECT * FROM peces";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[5];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);
                        datos[4] = rs.getString(5);
                    

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Anfibios".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Piel");
            
            sql = "SELECT * FROM anfibios";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Reptiles".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Habitad");
            
            sql = "SELECT * FROM reptiles";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        
        if("Artrópodos".equals(nomGrupo)){
            
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_ Pares_ de_Patas");
            modelo.addColumn("Antenas");
            
            sql = "SELECT * FROM artrópodos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[5];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
            
        } 
        if("Moluscos".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tipo_de_Concha");
            
            sql = "SELECT * FROM moluscos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        } 
        if("Equinodermos".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("tipo");
            
            sql = "SELECT * FROM equinodermos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Gusanos".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tipo_de_Cuerpo");
            
            sql = "SELECT * FROM gusanos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Poríferos".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
           
            
            sql = "SELECT * FROM poríferos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                   

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Celentéreos".equals(nomGrupo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tentáculos");
            
            sql = "SELECT * FROM celentéreos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }

    }
    
    public void SeleccionarAnimal(JTable paramTablaAnimales, JTextField paramId,JTextField paramAnimales,JTextField paramTipos,JTextField paramGrupos){

        try{
            int fila = paramTablaAnimales.getSelectedRow();//se toman los valores de la fila seleccionada

            if(fila>=0){

                paramId.setText((paramTablaAnimales.getValueAt(fila, 0).toString()));//se obtienen los valores de la fila seleccionada y se le asignan a los jtext filed
                paramAnimales.setText((paramTablaAnimales.getValueAt(fila, 1).toString()));
                paramTipos.setText((paramTablaAnimales.getValueAt(fila, 2).toString()));
                paramGrupos.setText((paramTablaAnimales.getValueAt(fila, 3).toString()));

                }
            else{

                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }

            }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de seleccion"+e.toString());
         }
    }
    
    public void ModificarAnimales(JTextField paramCodigo,JTextField paramAnimal,JTextField paramTipo,JTextField paramGrupo){

        setCodigo(Integer.parseInt(paramCodigo.getText()));//se obtienen los datos de la fila clickeada
        setNombreAnimal(paramAnimal.getText());
        setTipoAnimal(paramTipo.getText());
        setGrupoAnimal(paramGrupo.getText());

        Conexion obcox = new Conexion();//se genera una instancia de conexion con la base de datos

        String consulta = "UPDATE animales_generales SET Animal = ?, Tipo = ?, Grupo = ? WHERE ID = ?;";// se cra una consulta sql en la cual se actualiza la fila con los datos obtenidos dependiendo totalmente de la id 

        try {

            CallableStatement cs = obcox.estableceConexion().prepareCall(consulta);// se establece conexion con la base de datos y se prepara la consulta a mysql
            cs.setString(1,getNombreAnimal());//se dan los valores
            cs.setString(2,getTipoAnimal());
            cs.setString(3,getGrupoAnimal());
            cs.setInt(4,getCodigo());

            cs.execute();//se modifican los valores de la tabla general en mysql

            JOptionPane.showMessageDialog(null, "Modificacion exitosa");

        } catch (SQLException f) {
            JOptionPane.showMessageDialog(null, "Error de modificacion"+f.toString());
        }


    }
    
    public void EliminarAnimal(JTextField paramCodigo){

        setCodigo(Integer.parseInt(paramCodigo.getText()));//obtenemos el id de la fila seleccionada

        Conexion oneli = new Conexion();//creamos una instancia de conexion

        String consulta = "DELETE FROM animales_generales WHERE ID = ?";//creamos una consulta en la cual eliminaremos una fila donde su ID sea el señalado

        try {
            CallableStatement cs = oneli.estableceConexion().prepareCall(consulta);//se prepara la consulta y se establece la conexion con la base de datos
            cs.setInt(1, getCodigo());//asigna el valor devuelto por getCodigo() al primer parámetro de la consulta SQL preparada en el objeto CallableStatement.

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se elimino correctamente el animal");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al  eliminar  el animal"+e.toString());
        }
    }
        
    public void InsertarCarac4(String Nombre, Double Peso,String Color, String Caracteristica_especial,String Grupo,JTable Tabla_grupo){

        Conexion con = new Conexion();//se crea una conexion  a la base de datos

        String Consulta = ("INSERT INTO "+Grupo+" VALUES (?,?,?,?);");//se define una consulta en la cual definiremos la integracion de 4 variables a su respectiva tabla

        try {
            CallableStatement cs = con.estableceConexion().prepareCall(Consulta);

            cs.setString(1, Nombre);
            cs.setDouble(2,Peso);
            cs.setString(3,Color);
            cs.setString(4, Caracteristica_especial);

            cs.execute();//se ejecuta y se integran los datos a la tabla




        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el animal"+e.toString());
        }

        CAnimal act = new CAnimal();
        act.ActualizarLista(Tabla_grupo,Grupo);//llamamos a la funcion  Actualizzar Lista dentro de Canimales, para que luego de integrar los datos se muestren por pantalla



    }
     public void InsertarCarac3(String Nombre, Double Peso,String Color,String Grupo,JTable Tabla_grupo){

        Conexion con = new Conexion();//se crea una conexion  a la base de datos

        String Consulta = ("INSERT INTO "+Grupo+" VALUES (?,?,?);");//se define una consulta en la cual definiremos la integracion de 3 variables a su respectiva tabla

        try {
            CallableStatement cs = con.estableceConexion().prepareCall(Consulta);

            cs.setString(1, Nombre);
            cs.setDouble(2,Peso);
            cs.setString(3,Color);

            cs.execute();//se ejecuta y se integran los datos a la tabla


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el animal"+e.toString());
        }
        CAnimal act = new CAnimal();
        act.ActualizarLista(Tabla_grupo,Grupo);//llamamos a la funcion  Actualizzar Lista dentro de Canimales, para que luego de integrar los datos se muestren por pantalla

    }

    public void InsertarCarac5(String Nombre, Double Peso,String Color, String Caracteristica_especial, Boolean Caracteristica_especial2,String Grupo,JTable Tabla_grupo){

        Conexion con = new Conexion();//se crea una conexion  a la base de datos

        String Consulta = ("INSERT INTO "+Grupo+" VALUES (?,?,?,?,?);");//se define una consulta en la cual definiremos la integracion de 5 variables a su respectiva tabla

        try {
            CallableStatement cs = con.estableceConexion().prepareCall(Consulta);

            cs.setString(1, Nombre);
            cs.setDouble(2,Peso);
            cs.setString(3,Color);
            cs.setString(4, Caracteristica_especial);
            cs.setBoolean(5, Caracteristica_especial2);



            cs.execute();//se ejecuta y se integran los datos a la tabla


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el animal"+e.toString());
        }
        CAnimal act = new CAnimal();
        act.ActualizarLista(Tabla_grupo,Grupo);//llamamos a la funcion  Actualizar Lista dentro de Canimales, para que luego de integrar los datos se muestren por pantalla

    }
   
    
        public String AnimalAzar(JTable Tabla, JLabel animal, JLabel tipo, JLabel grupo, JLabel lblImagen2, JSlider Peso, JComboBox Color, JComboBox Carac_1, JComboBox Carac_2, JLabel OCA, JLabel OCG,JSlider decimal) throws IOException{
               
        Conexion A_azar = new Conexion();// se crea conexion
        CallableStatement ps = null;
        ResultSet rs = null;
        
        Image image= null;
        URL url;
        
        Conexion con = new Conexion();        
        DefaultTableModel modelo = new DefaultTableModel();
  
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        Tabla.setRowSorter(OrdenarTabla);
        
        String sql="";
        String consulta2 = "SELECT * FROM animales_generales WHERE ID = ?";//se crea consulta sql en la cual seleccionaremos un animal cuando la id sea la generada por el numero aleatorio
        int num_Aleatorio = 0;
        String nomAnimal = " ";//variable en la cual guardaremos las especificaciones de animal
        String Tipo = " ";//variable en la cual guardaremos las especificaciones de animal
        String Grupo = " ";//variable en la cual guardaremos las especificaciones de animal
        

        try {


            Random random = new Random();
            num_Aleatorio = random.nextInt(129) + 1;//se crea un numero aleatorio entre 1 y 129

            if (A_azar != null) {
                ps = A_azar.estableceConexion().prepareCall(consulta2);//se hace la consulta a mysql con el numero aleatorio
                ps.setInt(1, num_Aleatorio);//se define una fila y columna para buscar la informacion
                rs = ps.executeQuery();//ejecutamos la consulta y obtenemos los datos del mysql
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    nomAnimal = rs.getString("Animal" );
                    Tipo = rs.getString("Tipo" );
                    Grupo = rs.getString("Grupo");   
                    // se busca dentro del mysql y guardamos el animal seleccionado dentro de las variables creadas
                    animal.setText("Animal seleccionado: "+nomAnimal);
                    tipo.setText("Tipo: "+Tipo);
                    grupo.setText("Grupo:  "+Grupo);
                } 
            }
            //se crean if para determinar en que tipo de grupo se debe entrar, lugo de eso con if se busca el animal seleconado y se proporcionan las caracteristicas especiales y su imagen al programa para que la persona pueda modificar los valores deseados antes de agregar el animal a su respectiva tabla, ademas al final de cada if de grupo se muestra la tabla de datos de esta
            if("Mamíferos".equals(Grupo)){
                modelo.addColumn("Nombre");//se crea el modelo de columnas de el grupo determiado
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Cantidad_de_Patas");
                OCG.setText("Mamíferos");//se  le asigna un valor al label oculto en JFrame Ag_Ani correspondiente al grupo animal

                if ("León".equals(nomAnimal)) {
                    Peso.setMinimum(150);//entramos en el animal seleccionado al azar y le asignamos los valores a los combobox y a los Jslider
                    Peso.setMaximum(250);
                    Peso.setValue(150);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    

                    
                    String[] Colorc = {"Amarillo dorado", "Blanco", "Marrón", "Dorado claro", "Dorado oscuro", "Rubio", "Canela", "Leonado", "Anaranjado", "Rojo", "Negro", "Plateado", "Caramelo", "Gris", "Atigrado", "Pardo", "Castaño", "Avellana", "Crema", "Marfil"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("León ");//se le de valor a el label oculto en JFrame Ag_Ani correspondiente al nombre del animal
                

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Lion_waiting_in_Namibia.jpg/250px-Lion_waiting_in_Namibia.jpg");//se llama a una url correspondiente a la imagen del animal
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }

                if ("Elefante".equals(nomAnimal)) {
                    
                    Peso.setMinimum(4000);
                    Peso.setMaximum(6000);
                    Peso.setValue(4000);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    
                    String[] Colorc = {"Gris", "Marrón", "Negro", "Blanco", "Beige", "Canela", "Gris azulado", "Plateado", "Gris oscuro", "Gris claro", "Ceniciento", "Grisáceo", "Marfil", "Gris pizarra", "Gris metálico", "Gris plateado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Elefante");
                    
                    
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Elephant.pair.750pix.jpg/250px-Elephant.pair.750pix.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Ballena".equals(nomAnimal)) {
                    Peso.setMinimum(50000);
                    Peso.setMaximum(200000);
                    Peso.setValue(50000);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Gris oscuro", "Gris claro", "Negro", "Blanco", "Azul", "Azul claro", "Gris azulado", "Gris plateado", "Grisáceo", "Plateado", "Azul marino", "Azul cielo", "Azul oscuro", "Gris pizarra", "Gris metálico"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"0"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Ballena");
                         
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/Eubalaena_glacialis_with_calf.jpg/250px-Eubalaena_glacialis_with_calf.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Delfín".equals(nomAnimal)) {
                    Peso.setMinimum(70);
                    Peso.setMaximum(90);
                    Peso.setValue(70);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Gris oscuro", "Gris claro", "Negro", "Blanco", "Gris azulado", "Gris plateado", "Grisáceo", "Azul", "Azul claro", "Azul oscuro", "Azul marino", "Azul cielo", "Gris pizarra", "Gris metálico", "Blanco y negro", "Gris y blanco", "Azul y blanco", "Gris y azul"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"0"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
         
                    OCA.setText("Delfín ");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Dolphins_gesture_language.jpg/220px-Dolphins_gesture_language.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Ratón".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    
                    decimal.setMinimum(3);
                    decimal.setMaximum(900);
                    decimal.setValue(3);
                    
                    String[] Colorc = {"Gris", "Blanco", "Negro", "Marrón", "Beige", "Gris claro", "Gris oscuro", "Gris plateado", "Grisáceo", "Canela", "Gris pardo", "Gris azulado", "Gris verdoso", "Crema", "Albino", "Atigrado", "Café", "Chocolate", "Gris ahumado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
               
                    OCA.setText("Ratón");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/%D0%9C%D1%8B%D1%88%D1%8C_2.jpg/250px-%D0%9C%D1%8B%D1%88%D1%8C_2.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Caballo".equals(nomAnimal)) {
                    Peso.setMinimum(380);
                    Peso.setMaximum(1000);
                    Peso.setValue(380);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Negro", "Blanco", "Bayo", "Castaño", "Alazán", "Gris", "Tordo", "Pío", "Palomino", "Dorado", "Cremello", "Perlino", "Isabela", "Ruano", "Overo", "Tobiano", "Appaloosa", "Sablé", "Pinto"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
     
                    OCA.setText("Caballo");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Hairz.jpg/275px-Hairz.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Perro".equals(nomAnimal)) {
                    Peso.setMinimum(2);
                    Peso.setMaximum(80);
                    Peso.setValue(2);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Negro", "Blanco", "Gris", "Dorado", "Atigrado", "Canela", "Crema", "Rojo", "Azul", "Café", "Plateado", "Tricolor", "Merle", "Albino", "Leonado", "Amarillo", "Negro y fuego", "Negro y blanco", "Negro y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Perro");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Poligraf_Poligrafovich.JPG/220px-Poligraf_Poligrafovich.JPG");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Lobo".equals(nomAnimal)) {
                    Peso.setMinimum(20);
                    Peso.setMaximum(80);
                    Peso.setValue(20);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Gris", "Negro", "Blanco", "Canela", "Marrón", "Gris oscuro", "Gris claro", "Gris plateado", "Grisáceo", "Negro y gris", "Negro y blanco", "Marrón y blanco", "Marrón rojizo", "Blanco y gris", "Canela y gris", "Negro y rojo", "Negro y marrón", "Gris y blanco", "Gris y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Lobo");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Canis_lupus_265b.jpg/250px-Canis_lupus_265b.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Vaca".equals(nomAnimal)) {
                    Peso.setMinimum(400);
                    Peso.setMaximum(800);
                    Peso.setValue(400);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco", "Negro", "Marrón", "Manchado blanco y negro", "Manchado blanco y marrón", "Manchado negro y marrón", "Tricolor", "Blanco y negro", "Blanco y marrón", "Negro y marrón", "Marrón claro", "Marrón oscuro", "Canela", "Gris", "Atigrado", "Rubio", "Rojo", "Café", "Grisáceo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Vaca ");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Vaca.jpg/397px-Vaca.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Oso".equals(nomAnimal)) {
                    Peso.setMinimum(150);
                    Peso.setMaximum(500);
                    Peso.setValue(150);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Negro", "Blanco", "Gris", "Canela", "Rubio", "Grisáceo", "Marrón oscuro", "Marrón claro", "Blanco y negro", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Negro y gris", "Negro y marrón", "Gris y marrón", "Canela y blanco", "Canela y negro", "Rubio y blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Oso");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Oso.jpg/674px-Oso.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Conejo".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(15);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco", "Negro", "Gris", "Marrón", "Blanco y negro", "Blanco y gris", "Blanco y marrón", "Negro y blanco", "Negro y marrón", "Gris y blanco", "Gris y negro", "Marrón y blanco", "Marrón y negro", "Atigrado", "Dorado", "Canela", "Grisáceo", "Cenizo", "Leonado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Conejo");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Oryctolagus_cuniculus_Tasmania_2.jpg/250px-Oryctolagus_cuniculus_Tasmania_2.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                    
                if ("Gorila".equals(nomAnimal)) {
                    Peso.setMinimum(150);
                    Peso.setMaximum(200);
                    Peso.setValue(150);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Negro", "Gris oscuro", "Gris plateado", "Gris claro", "Negro y gris", "Negro y plateado", "Gris y negro", "Gris y plateado", "Grisáceo", "Marrón", "Marrón oscuro", "Marrón claro", "Marrón rojizo", "Canela", "Marrón y negro", "Marrón y gris", "Gris y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Gorila");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Gorilla.jpg/250px-Gorilla.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Chimpancé".equals(nomAnimal)) {
                    Peso.setMinimum(40);
                    Peso.setMaximum(60);
                    Peso.setValue(40);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Negro", "Negro y marrón", "Gris oscuro", "Gris claro", "Grisáceo", "Marrón claro", "Marrón oscuro", "Canela", "Blanco", "Negro y gris", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Marrón y gris", "Gris y marrón", "Canela y blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Chimpancé");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Composite_image_of_male_chimpanzee_%28left%29_and_male_bonobo_%28right%29.jpg/240px-Composite_image_of_male_chimpanzee_%28left%29_and_male_bonobo_%28right%29.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Jirafa".equals(nomAnimal)) {
                    Peso.setMinimum(800);
                    Peso.setMaximum(1600);
                    Peso.setValue(800);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Amarillo", "Manchado marrón y amarillo", "Manchado marrón y blanco", "Manchado amarillo y blanco", "Marrón claro", "Marrón oscuro", "Gris", "Beige", "Blanco", "Atigrado", "Rubio", "Dorado", "Canela", "Grisáceo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Jirafa");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Giraffa_camelopardalis_angolensis.jpg/250px-Giraffa_camelopardalis_angolensis.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Hipopótamo".equals(nomAnimal)) {
                    Peso.setMinimum(1000);
                    Peso.setMaximum(4000);
                    Peso.setValue(1000);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Gris oscuro", "Gris claro", "Gris azulado", "Gris pizarra", "Gris verdoso", "Gris plateado", "Grisáceo", "Marrón oscuro", "Marrón claro", "Canela", "Negro", "Blanco", "Blanco y gris", "Gris y marrón", "Gris y blanco", "Marrón y blanco", "Marrón y gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Hipopótamo");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Hippo_pod_edit.jpg/250px-Hippo_pod_edit.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Canguro".equals(nomAnimal)) {
                    Peso.setMinimum(20);
                    Peso.setMaximum(80);
                    Peso.setValue(20);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Gris", "Grisáceo", "Canela", "Marrón claro", "Marrón oscuro", "Beige", "Blanco", "Negro", "Marrón rojizo", "Gris y blanco", "Marrón y blanco", "Negro y blanco", "Gris y negro", "Gris y marrón", "Canela y blanco", "Canela y gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Canguro");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Kangaroo_and_joey03.jpg/320px-Kangaroo_and_joey03.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Rinoceronte".equals(nomAnimal)) {
                    Peso.setMinimum(1000);
                    Peso.setMaximum(3000);
                    Peso.setValue(1000);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Gris oscuro", "Gris claro", "Grisáceo", "Gris plateado", "Negro", "Marrón oscuro", "Marrón claro", "Marrón rojizo", "Beige", "Blanco", "Negro y gris", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Marrón y gris", "Gris y marrón", "Blanco y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));


                    OCA.setText("Rinoceronte");
                    try {
                        
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Indian_Rhinoceros.jpg/250px-Indian_Rhinoceros.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Oveja".equals(nomAnimal)) {
                    Peso.setMinimum(50);
                    Peso.setMaximum(100);
                    Peso.setValue(50);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco", "Negro", "Marrón", "Gris", "Beige", "Canela", "Marrón claro", "Marrón oscuro", "Negro y blanco", "Marrón y blanco", "Gris y blanco", "Marrón y negro", "Gris y negro", "Blanco y marrón", "Blanco y gris", "Marrón y gris", "Negro y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));

                    OCA.setText("Oveja");
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Flock_of_sheep.jpg/320px-Flock_of_sheep.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));
                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Cerdo".equals(nomAnimal)) {
                    Peso.setMinimum(80);
                    Peso.setMaximum(300);
                    Peso.setValue(80);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Rosado", "Negro", "Blanco", "Marrón", "Gris", "Atigrado", "Manchado blanco y negro", "Manchado blanco y marrón", "Manchado negro y marrón", "Marrón claro", "Marrón oscuro", "Blanco y negro", "Blanco y marrón", "Negro y blanco", "Negro y marrón", "Gris y blanco", "Gris y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));   

                    OCA.setText("Cerdo");
                    try {
                        
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Pig_farm_Vampula_9.jpg/250px-Pig_farm_Vampula_9.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));

                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                sql = "SELECT * FROM mamíferos";//se crea una consulta sql para mostrar la tabla mamiferos
                Tabla.setModel(modelo);//se le da el modelo de mamiferos creado anteriormenre

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);//se ejecuta el codigo en la query de mysql obteniendo los datos

                    while(rs.next()){
                        datos[0] = rs.getString(1);//se muestran los datos correspondientes a mamiferos
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
            } 
            
            
            if("Aves".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Cantidad_de_alas");
                OCG.setText("Aves");
                
                if ("Ganso".equals(nomAnimal)) {
                    Peso.setMinimum(3);   // Rango de peso aproximado en kilogramos
                    Peso.setMaximum(6 );
                    Peso.setValue(3);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco", "Gris", "Gris oscuro", "Gris claro", "Negro", "Marrón", "Blanco y gris", "Blanco y negro", "Gris y blanco", "Gris y negro", "Marrón y blanco", "Marrón y gris", "Negro y blanco", "Negro y gris", "Blanco y marrón", "Marrón y negro", "Canela", "Dorado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Ganso");
                  

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://inaturalist-open-data.s3.amazonaws.com/photos/2317972/large.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Urraca".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0); 
                    decimal.setMinimum(150);
                    decimal.setMaximum(300);
                    decimal.setValue(150);
    
                    String[] Colorc = {"Negro", "Blanco", "Negro y blanco", "Azul", "Gris", "Grisáceo", "Azul y blanco", "Negro y azul", "Blanco y azul", "Gris y blanco", "Gris y negro", "Blanco y negro", "Negro y gris", "Azul y negro", "Azul y gris", "Blanco y gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Urraca");
            

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.allcitycanvas.com/wp-content/uploads/2021/02/mark-harvey-in-flight-h.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Cóndor".equals(nomAnimal)) {
                    Peso.setMinimum(7);  
                    Peso.setMaximum(15 );
                    Peso.setValue(7);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Negro", "Negro y blanco", "Negro y gris", "Gris oscuro", "Grisáceo", "Blanco y negro", "Blanco y gris", "Negro y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Cóndor");
                 

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://veterinariaelroble.cl/wp-content/uploads/2022/09/Andean-Condor-Flying-thumbnail.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Lechuza".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0 ); 
                    decimal.setMinimum(80);
                    decimal.setMaximum(170);
                    decimal.setValue(80);
                    
                    String[] Colorc = {"Blanco", "Marrón", "Gris", "Grisáceo", "Marrón claro", "Marrón oscuro", "Blanco y marrón", "Gris y blanco", "Marrón y blanco", "Gris y marrón", "Gris y negro", "Blanco y gris", "Marrón y gris", "Negro y blanco", "Negro y marrón", "Blanco y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Lechuza");
       

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/3/3e/Tyto_alba_close_up.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Golondrina".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(10);
                    decimal.setMaximum(20);
                    decimal.setValue(10);
                    
                    String[] Colorc = {"Negro", "Azul", "Gris", "Blanco", "Marrón", "Canela", "Grisáceo", "Azul y blanco", "Negro y blanco", "Marrón y blanco", "Marrón y gris", "Negro y azul", "Negro y marrón", "Azul y negro", "Gris y blanco", "Canela y blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Golondrina");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://static.diariofemenino.com/media/5557/c/el-gran-simbolismo-de-sonar-con-golondrinas-viva-la-libertad-lg.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Loro".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(300);
                    decimal.setMaximum(500);
                    decimal.setValue(300);
                    
                    String[] Colorc = {"Blanco", "Gris", "Gris oscuro", "Gris claro", "Negro", "Marrón", "Blanco y gris", "Blanco y negro", "Gris y blanco", "Gris y negro", "Marrón y blanco", "Marrón y gris", "Negro y blanco", "Negro y gris", "Blanco y marrón", "Marrón y negro", "Canela", "Dorado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Loro");
                

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://t1.ea.ltmcdn.com/es/posts/7/0/8/cuanto_vive_un_loro_25807_orig.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Koel".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0);
                    decimal.setMinimum(100);
                    decimal.setMaximum(300);
                    decimal.setValue(100);
                    
                    String[] Colorc = {"Verde", "Rojo", "Azul", "Amarillo", "Naranja", "Blanco", "Gris", "Morado", "Rosado", "Negro", "Marrón", "Turquesa", "Celeste", "Blanco y azul", "Verde y amarillo", "Rojo y azul", "Amarillo y azul", "Rojo y verde", "Gris y blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Koel");
             

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Asian_koel.jpg/1200px-Asian_koel.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Azulejo".equals(nomAnimal)) {
                   Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(10);
                    decimal.setMaximum(50);
                    decimal.setValue(10);
                    
                    String[] Colorc = {"Azul", "Blanco", "Gris", "Negro", "Naranja", "Marrón", "Verde", "Turquesa", "Plateado", "Azul y blanco", "Azul y naranja", "Blanco y negro", "Gris y blanco", "Marrón y blanco", "Negro y blanco", "Verde y azul"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Azulejo");
            

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://inaturalist-open-data.s3.amazonaws.com/photos/1082/large.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Secretario".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5 );
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Gris", "Blanco", "Negro", "Grisáceo", "Marrón", "Blanco y negro", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Gris y negro", "Negro y gris", "Blanco y gris", "Marrón y gris", "Marrón oscuro", "Gris oscuro", "Blanco y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Secretario");
         

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://4.bp.blogspot.com/-ZUoc8WVnj4s/VDGsTspdn-I/AAAAAAAAA80/GqjcR240iG8/s1600/secretario.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Garza".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0);
                    decimal.setMinimum(300);
                    decimal.setMaximum(400 );
                    decimal.setValue(300);

                    String[] Colorc = {"Blanco", "Gris", "Grisáceo", "Negro", "Azul", "Blanco y gris", "Blanco y negro", "Gris y blanco", "Gris y negro", "Negro y blanco", "Azul y blanco", "Azul y gris", "Gris y azul", "Blanco y azul", "Negro y azul"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Garza");
            

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.redobservadores.cl/wp-content/uploads/2018/09/Garza-chica-portada-Vicente-Pantoja.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Canario".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(10);
                    decimal.setMaximum(20 );
                    decimal.setValue(10);
                    
                    String[] Colorc = {"Amarillo", "Blanco", "Naranja", "Rojo", "Verde", "Azul", "Gris", "Negro", "Canela", "Dorado", "Plateado", "Blanco y amarillo", "Verde y amarillo", "Azul y blanco", "Naranja y blanco", "Rojo y negro", "Amarillo y verde"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Canario");
           

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.anipedia.net/imagenes/canarios-800x375.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Cisne".equals(nomAnimal)) {
                    Peso.setMinimum(7);
                    Peso.setMaximum(15 );
                    Peso.setValue(7);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco", "Negro", "Gris", "Grisáceo", "Blanco y negro", "Blanco y gris", "Negro y blanco", "Negro y gris", "Gris y blanco", "Gris y negro", "Blanco y marrón", "Negro y marrón", "Gris y marrón", "Marrón claro", "Marrón oscuro", "Blanco y dorado", "Negro y dorado", "Gris y dorado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Cisne");
   

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://hablemosdeempresas.com/wp-content/uploads/sites/2/2020/03/cisne-verde-portada.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Quebrantahuesos".equals(nomAnimal)) {
                    Peso.setMinimum(4);
                    Peso.setMaximum(7 );
                    Peso.setValue(4);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    String[] Colorc = {"Blanco", "Negro", "Marrón", "Gris", "Blanco y negro", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Gris y negro", "Negro y marrón", "Marrón y gris", "Blanco y marrón", "Marrón oscuro", "Gris oscuro", "Marrón claro", "Blanco y gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Quebrantahuesos  ");
             

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.publico.es/uploads/2017/02/02/58931c91b16a1.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Frailecillo".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0); 
                    decimal.setMinimum(200);
                    decimal.setMaximum(490 );
                    decimal.setValue(200);
                    
                    String[] Colorc = {"Negro", "Blanco", "Naranja", "Gris", "Amarillo", "Rojo", "Verde", "Azul", "Blanco y negro", "Naranja y blanco", "Gris y blanco", "Amarillo y blanco", "Rojo y blanco", "Verde y blanco", "Azul y blanco", "Negro y naranja", "Negro y gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Frailecillo");
              

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://i.pinimg.com/736x/fb/32/3c/fb323c8955668732e127b2e7b2481bea--natural-wonders-shorebirds.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Albatros".equals(nomAnimal)) {
                    Peso.setMinimum(6);
                    Peso.setMaximum(9 );
                    Peso.setValue(6);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco", "Negro", "Gris", "Blanco y negro", "Blanco y gris", "Negro y blanco", "Negro y gris", "Gris y blanco", "Gris y negro", "Blanco y marrón", "Negro y marrón", "Gris y marrón", "Marrón claro", "Marrón oscuro", "Blanco y crema", "Negro y crema", "Gris y crema"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Albatros");
                   

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.redobservadores.cl/wp-content/uploads/2020/06/cecja-negra.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                    if ("Herrerillo".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(5);
                    decimal.setMaximum(12 );
                    decimal.setValue(5);
                    
                    String[] Colorc = {"Azul", "Blanco", "Negro", "Gris", "Grisáceo", "Verde", "Amarillo", "Blanco y azul", "Azul y negro", "Gris y blanco", "Blanco y negro", "Negro y blanco", "Azul y blanco", "Azul y amarillo", "Gris y azul", "Gris y amarillo", "Verde y azul"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Herrerillo");
                 
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://t0.gstatic.com/licensed-image?q=tbn:ANd9GcQ0scAP0odYCoE93jhrXJcqWMp5OFuN8wDK81GcvzlYgV6HKbmhEE_Cn_MlUe9CzweN");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Carpintero".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(250);
                    decimal.setMaximum(400 );
                    decimal.setValue(250);
                    
                    String[] Colorc = {"Negro", "Blanco", "Marrón", "Gris", "Verde", "Amarillo", "Rojo", "Naranja", "Blanco y negro", "Negro y blanco", "Marrón y blanco", "Gris y blanco", "Verde y blanco", "Amarillo y negro", "Rojo y negro", "Naranja y negro", "Blanco y marrón", "Negro y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Carpintero");
                  
                    try {
                        // Dirección de la imagen
                        url = new URL("https://misanimales.com/wp-content/uploads/2021/01/pajaro-carpintero-pareja.jpg");

                        // Cargar la imagen y redimensionarla al tamaño del JLabel
                        BufferedImage originalImage = ImageIO.read(url);
                        Image resizedImage = originalImage.getScaledInstance(lblImagen2.getWidth(), lblImagen2.getHeight(), Image.SCALE_SMOOTH);

                        // Mostrar la imagen redimensionada en el JLabel
                        lblImagen2.setIcon(new ImageIcon(resizedImage));
                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Pavo real".equals(nomAnimal)) {
                    Peso.setMinimum(4);
                    Peso.setMaximum(7 );
                    Peso.setValue(4);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);

                    String[] Colorc = {"Azul", "Verde", "Turquesa", "Dorado", "Bronce", "Blanco", "Negro", "Marrón", "Gris", "Azul y verde", "Azul y blanco", "Verde y dorado", "Dorado y blanco", "Blanco y negro", "Negro y azul", "Azul y negro", "Verde y blanco", "Blanco y dorado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Pavo real");
             

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.lifeder.com/wp-content/uploads/2019/02/pavo-real.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Martín pescador".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0);
                    decimal.setMinimum(25);
                    decimal.setMaximum(40 );
                    decimal.setValue(25);
    
                    String[] Colorc = {"Azul", "Verde", "Blanco", "Naranja", "Marrón", "Negro", "Gris", "Azul y blanco", "Azul y naranja", "Verde y blanco", "Verde y naranja", "Blanco y negro", "Negro y blanco", "Gris y blanco", "Azul y verde", "Azul y marrón", "Verde y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Martín pescador");
              

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://animapedia.org/wp-content/uploads/2018/07/martin-pescador-pescando-e1532962465122.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Tucán".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(130);
                    decimal.setMaximum(700 );
                    decimal.setValue(130);
                    
                    String[] Colorc = {"Negro", "Blanco", "Amarillo", "Naranja", "Verde", "Azul", "Rojo", "Morado", "Gris", "Negro y blanco", "Negro y amarillo", "Blanco y amarillo", "Amarillo y naranja", "Verde y amarillo", "Azul y blanco", "Rojo y negro", "Morado y blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Tucán");
           

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/a/a5/Ramphastos_tucanus_inca_2.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Halcones".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Gris", "Blanco", "Negro", "Canela", "Dorado", "Grisáceo", "Blanco y marrón", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Negro y marrón", "Gris y marrón", "Blanco y negro", "Negro y gris", "Gris y negro", "Marrón y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Halcones");
          
                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.hogarmania.com/archivos/201705/aves-halcon-peregrino-1280x720x80xX.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Pinzón".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(10);
                    decimal.setMaximum(30 );
                    decimal.setValue(10);
                    
                    String[] Colorc = {"Amarillo", "Verde", "Rojo", "Azul", "Naranja", "Marrón", "Gris", "Negro", "Blanco", "Amarillo y negro", "Verde y amarillo", "Rojo y negro", "Azul y negro", "Naranja y negro", "Marrón y blanco", "Gris y blanco", "Negro y blanco", "Blanco y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Pinzón");
          

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/be/Fringilla_coelebs_%28chaffinch%29%2C_male.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Cuervo".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(3 );
                    Peso.setValue(1); 
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Negro", "Gris", "Blanco", "Negro y gris", "Negro y blanco", "Gris y blanco", "Blanco y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Cuervo");
                 

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://cdnb.20m.es/sites/47/2016/07/cuervocesar_bearb-620x583.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Vencejo".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(25);
                    decimal.setMaximum(45 );
                    decimal.setValue(25);
                           
                    String[] Colorc = {"Negro", "Gris", "Blanco", "Marrón", "Grisáceo", "Blanco y negro", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Negro y gris", "Gris y negro", "Blanco y gris", "Marrón y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Vencejo");
        

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://misanimales.com/wp-content/uploads/2018/04/vencejo-caracteristicas.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Flamenco".equals(nomAnimal)) {
                    Peso.setMinimum(2);
                    Peso.setMaximum(4 );
                    Peso.setValue(2);
                    
                    String[] Colorc = {"Rosa", "Blanco", "Gris", "Negro", "Naranja", "Rojo", "Blanco y rosa", "Rosa y gris", "Rosa y blanco", "Blanco y negro", "Gris y blanco", "Negro y blanco", "Rosa y naranja", "Rosa y rojo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Flamenco");
              

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.ngenespanol.com/wp-content/uploads/2022/07/flamenco-leyenda-ave-fenix.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }

                if ("Chotacabras Cárabo".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(50);
                    decimal.setMaximum(150 );
                    decimal.setValue(50);
                    
                    String[] Colorc = {"Marrón", "Gris", "Negro", "Blanco", "Grisáceo", "Marrón y gris", "Negro y blanco", "Gris y blanco", "Negro y gris", "Blanco y gris", "Marrón y blanco", "Marrón y negro", "Gris y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("ChotacabrasCárabo");
           

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://mundiaves.com/wp-content/uploads/2023/03/carabo-bataraz-strix-rufipes.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Guacamaya".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(2 );
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Azul", "Rojo", "Verde", "Amarillo", "Naranja", "Morado", "Blanco", "Negro", "Azul y amarillo", "Rojo y azul", "Verde y amarillo", "Amarillo y rojo", "Naranja y azul", "Morado y amarillo", "Blanco y azul", "Negro y rojo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Guacamaya");
                

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://sustentur.com.mx/wp-content/uploads/2022/06/Guacamaya-2-scaled.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Jilguero".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(10);
                    decimal.setMaximum(25 );
                    decimal.setValue(10);
                    
                    String[] Colorc = {"Amarillo", "Negro", "Blanco", "Gris", "Marrón", "Amarillo y negro", "Amarillo y blanco", "Negro y blanco", "Gris y blanco", "Marrón y blanco", "Amarillo y gris", "Amarillo y marrón", "Negro y gris", "Negro y marrón", "Blanco y gris", "Blanco y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Jilguero");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://img.bekiamascotas.com/articulos/portada/72000/72303.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Pingüino".equals(nomAnimal)) {
                    Peso.setMinimum(15);
                    Peso.setMaximum(25);
                    Peso.setValue(15);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Negro", "Blanco", "Gris", "Azul", "Negro y blanco", "Negro y gris", "Blanco y gris", "Azul y blanco", "Azul y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Pingüino");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://t2.ea.ltmcdn.com/es/posts/8/2/2/tipos_de_pinguinos_21228_600_square.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Gallina".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(3 );
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco", "Negro", "Rojo", "Gris", "Marrón", "Dorado", "Amarillo", "Plateado", "Blanco y negro", "Negro y rojo", "Blanco y rojo", "Gris y blanco", "Marrón y blanco", "Marrón y negro", "Dorado y blanco", "Amarillo y blanco", "Plateado y blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Gallina");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.gallinaponedora.com/wp-content/uploads/2018/05/Gallina-Rhode-Island-Red.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Quetzal".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    decimal.setMinimum(100);
                    decimal.setMaximum(250 );
                    decimal.setValue(100);
                    
                    String[] Colorc = {"Verde", "Azul", "Rojo", "Amarillo", "Dorado", "Blanco", "Verde y azul", "Verde y rojo", "Verde y amarillo", "Azul y rojo", "Azul y amarillo", "Rojo y amarillo", "Verde y dorado", "Azul y dorado", "Rojo y dorado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Quetzal");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://mymodernmet.com/wp/wp-content/uploads/2020/05/Quetzal-2.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Búho".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(4 );
                    Peso.setValue(1); 
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Gris", "Blanco", "Negro", "Grisáceo", "Marrón y blanco", "Negro y blanco", "Gris y blanco", "Negro y gris", "Blanco y gris", "Marrón y negro", "Gris y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Búho");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://t2.ea.ltmcdn.com/es/posts/1/8/5/tipos_de_buhos_24581_orig.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Avestruz".equals(nomAnimal)) {
                    Peso.setMinimum(63);
                    Peso.setMaximum(150 );
                    Peso.setValue(63);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Gris", "Negro", "Blanco", "Marrón y blanco", "Negro y blanco", "Gris y blanco", "Marrón y negro", "Gris y negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Avestruz");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.avesdechile.cl/0jpgn/139csq3.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Aguilucho".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0 );
                    Peso.setValue(0); 
                    
                    decimal.setMinimum(500);
                    decimal.setMaximum(999 );
                    decimal.setValue(500);
                    
                    String[] Colorc = {"Marrón", "Gris", "Negro", "Blanco", "Marrón y blanco", "Negro y blanco", "Gris y blanco", "Marrón y negro", "Gris y negro", "Blanco y negro", "Marrón y gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Aguilucho");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://seo.org/wp-content/uploads/2017/01/Aguilucho_palido-%C2%A9Dennis-Jacobsen-shutterstock_324101162.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Ñandú".equals(nomAnimal)) {
                    Peso.setMinimum(30);
                    Peso.setMaximum(50);
                    Peso.setValue(30);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón", "Gris", "Negro", "Blanco", "Marrón y blanco", "Negro y blanco", "Gris y blanco", "Marrón y negro", "Gris y negro", "Blanco y negro", "Marrón y gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Ñandú");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://t1.ea.ltmcdn.com/es/posts/5/2/7/nandu_tipos_caracteristicas_alimentacion_y_habitat_25725_orig.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Mosquitero".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(40);
                    decimal.setMaximum(80 );
                    decimal.setValue(40);
                    
                    String[] Colorc = {"Verde oliva", "Gris", "Blanco", "Amarillo", "Negro", "Blanco y gris", "Verde y amarillo", "Amarillo y blanco", "Negro y blanco", "Gris y amarillo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Mosquitero");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.canto-pajaros.es/images/mosquitero-comun.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Perico".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(30);
                    decimal.setMaximum(40 );
                    decimal.setValue(30);
                    
                    String[] Colorc = {"Verde", "Azul", "Amarillo", "Rojo", "Naranja", "Blanco", "Gris", "Negro", "Morado", "Rosado", "Marrón", "Turquesa", "Celeste", "Blanco y verde", "Verde y amarillo", "Azul y amarillo", "Rojo y azul", "Rosado y blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Perico");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://avesenchile.cl/wp-content/uploads/2022/02/perico-cordillerano-004.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Paloma".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(200);
                    decimal.setMaximum(400 );
                    decimal.setValue(200);
                    
                    String[] Colorc = {"Gris", "Blanco", "Negro", "Marrón", "Blanco y gris", "Gris y blanco", "Negro y blanco", "Marrón y blanco", "Negro y gris", "Gris y marrón", "Blanco y marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"2"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    
                    OCA.setText("Paloma");
                    OCG.setText("Aves");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://cdn.pixabay.com/photo/2017/07/18/18/24/dove-2516641_1280.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }

                
                
                sql = "SELECT * FROM aves";
                Tabla.setModel(modelo);

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
                 
            } 
            
            
            
            if("Peces".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Cantidad_de_Aletas");
                modelo.addColumn("Escamas");
                OCG.setText("Peces");
                Carac_2.setVisible(true);
                
                if ("Alfonsino".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(4);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Rojo", "Naranja", "Plateado", "Rosado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Aletas = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Aletas));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Alfonsino");
                    
                    

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Splendid_alfonsino_%28_Beryx_splendens_%29.jpg/240px-Splendid_alfonsino_%28_Beryx_splendens_%29.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Anchoveta".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(5);
                    decimal.setMaximum(20 );
                    decimal.setValue(5);
                    
                    String[] Colorc = { "Plateado", "Verde azulado", "Azul"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Anchoveta ");
            

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPKH_V1lNJPPRYUO2JiagU356VvCFpHLWgA46q_rwCPuC0a5uSw9pGPLRcr1apFBQXK3w&usqp=CAU");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Bacalao de profundidad".equals(nomAnimal)) {
                    Peso.setMinimum(25);
                    Peso.setMaximum(100);
                    Peso.setValue(25);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Gris oscuro", "Marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Bacalao de profundidad ");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://pescaymedioambiente.com/wp-content/uploads/2021/03/Eliana.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Besugo".equals(nomAnimal)) {
                    Peso.setMinimum(1 );
                    Peso.setMaximum(3);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {  "Rojo", "Naranja", "Plateado", "Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Besugo");
        

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://misanimales.com/wp-content/uploads/2022/11/besugo.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Caballa".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(2);
                    Peso.setValue(1);
                    
                    String[] Colorc = {"Verde azulado", "Plateado", "Negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Caballa");
        

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://pescadoazulyblanco.com/wp-content/uploads/2020/08/caballa.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Congrio dorado".equals(nomAnimal)) {
                    Peso.setMinimum(5);
                    Peso.setMaximum(15);
                    Peso.setValue(5);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Marrón claro", "Beige", "Amarillo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Congrio dorado");
         

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://sobremesa.es/upload/images/03_2020/5741_46-apertura-congrio.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Congrio negro".equals(nomAnimal)) {
                    Peso.setMinimum(5);
                    Peso.setMaximum(15);
                    Peso.setValue(5);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Negro", "Gris oscuro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Congrio negro");
            

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.depeces.com/wp-content/uploads/2015/08/congrio.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Jurel".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(2);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Plateado", "Verde azulado", "Azul", "Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Jurel");
      

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://congeladosdil.com/dynamic/img/jurel-beneficios-dieta-congelados-dil-3.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Lenguado".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(2);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Marrón", "Beige", "Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"6"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Lenguado");
       

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.mnhn.gob.cl/sites/www.mnhn.gob.cl/files/2021-04/Cabecera-%20Lenguado%20californiano%20%28Paralichthys%20californicus%29-%20%28c%29%20Peggy%20Romfh.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Merluza común".equals(nomAnimal)) {
                    Peso.setMinimum(5);
                    Peso.setMaximum(11);
                    Peso.setValue(5);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Plateado", "Gris", "Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Merluza común");
     

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.subpesca.cl/portal/616/articles-832_foto.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Merluza de cola".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(3);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Plateado", "Gris", "Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Merluza de cola");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.subpesca.cl/portal/616/articles-833_foto.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Merluza de tres aletas".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(2);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Plateado", "Gris", "Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Merluza de tres aletas");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.subpesca.cl/portal/616/articles-835_foto.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Merluza del sur".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(3);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Plateado", "Gris", "Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Merluza del sur");
 

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.subpesca.cl/portal/616/articles-834_foto.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Orange roughy".equals(nomAnimal)) {
                    Peso.setMinimum(2);
                    Peso.setMaximum(4);
                    Peso.setValue(2);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);    
                    
                    String[] Colorc = {  "Naranja", "Rojo", "Plateado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    
                    OCA.setText("Orange roughy");
    

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://i.pinimg.com/736x/02/41/2a/02412a748d49758bcafcd1176f145b10.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Pejegallo".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Plateado", "Gris", "Blanco", "Verde azulado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Pejegallo");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.depeces.com/wp-content/uploads/2019/01/reproduccion-del-pez-gallo.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                    if ("pez espada".equals(nomAnimal)) {
                    Peso.setMinimum(100);
                    Peso.setMaximum(500);
                    Peso.setValue(100);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Azul oscuro", "Negro", "Plateado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Pez espada ");
        

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://pescadordeportivo.files.wordpress.com/2020/05/pez-espada.png?w=700");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Puye".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(3);
                    decimal.setMaximum(35 );
                    decimal.setValue(3);
                    
                    String[] Colorc = { "Marrón", "Verde oscuro", "Beige"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Puye ");
        

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://live.staticflickr.com/1823/41735999940_fc31cc8308_b.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Raya volantín".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Marrón", "Gris oscuro", "Blanco", "Negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Raya volantín ");
                   

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://mimus.mx/wp-content/uploads/2021/04/acuerdan-proteger-1020x600.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Reineta".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(1);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(900 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Plateado", "Gris", "Blanco", "Verde azulado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Reineta ");
               

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://pezbonito.cl/wp-content/uploads/2020/06/reineta-600x601.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Salmón coho".equals(nomAnimal)) {
                    Peso.setMinimum(4);
                    Peso.setMaximum(8);
                    Peso.setValue(4);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Plateado", "Rojo", "Naranja"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Salmón coho");
              

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://image.salmonexpert.cl/1152854.webp?imageId=1152854&width=960&height=722&format=jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Salmón del atlántico".equals(nomAnimal)) {
                    Peso.setMinimum(2);
                    Peso.setMaximum(6);
                    Peso.setValue(2);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "Plateado", "Gris", "Verde oscuro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Salmón del Atlántico ");
           

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.depeces.com/wp-content/uploads/2017/05/salmones-en-el-mar.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Sardina austral".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(50);
                    decimal.setMaximum(200 );
                    decimal.setValue(50);
                    
                    String[] Colorc = { "Plateado", "Azul", "Verde azulado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Sardina austral ");
        

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.subpesca.cl/portal/616/articles-79857_foto.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Sardina común".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(50);
                    decimal.setMaximum(150 );
                    decimal.setValue(50);
                    
                    String[] Colorc = {  "Plateado", "Azul", "Verde azulado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Sardina común");
             

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.micebo.es/data/blog/366/images/_mini/post_grd/705/sardina.jpg?t=20220422b");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Sardina española".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(1);
                    Peso.setValue(0);
                    decimal.setMinimum(50);
                    decimal.setMaximum(100 );
                    decimal.setValue(50);
                    
                    String[] Colorc = {   "Plateado", "Azul", "Verde azulado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Patasc = {"7"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Patasc));
                    Boolean[] Escamas = {true,false};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Escamas));
                    
                    OCA.setText("Sardina española");
        

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.subpesca.cl/portal/616/articles-8252_foto.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                
                

                    sql = "SELECT * FROM peces";
                    Tabla.setModel(modelo);

                    String[] datos = new String[5];
                    Statement st;


                    try{
                        st=(Statement) con.estableceConexion().createStatement();
                        rs = st.executeQuery(sql);

                        while(rs.next()){
                                datos[0] = rs.getString(1);
                                datos[1] = rs.getString(2)+"Kg";
                                datos[2] = rs.getString(3);
                                datos[3] = rs.getString(4);
                                datos[4] = rs.getString(5);


                            modelo.addRow(datos);
                        }

                        Tabla.setModel(modelo);

                    }catch( Exception e){
                        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }

            }
            if("Anfibios".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Piel");
                OCG.setText("Anfibios");
                
                
                if ("Sapo común".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(0);
                    decimal.setMaximum(500 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"marrón", "verde", "gris", "negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));

                    
                    OCA.setText("Sapo común");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.montesdevalsain.es/images/Comun1_a.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Sapo gigante".equals(nomAnimal)) {
                    Peso.setMinimum(1 );
                    Peso.setMaximum(3);
                    Peso.setValue(1 );
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"marrón oscuro", "verde", "gris", "negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Sapo gigante");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://imagenes.elpais.com/resizer/1qW5m_fNc87mJxosW7LblMl0_2w=/414x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/UCZTB3A6J5BY7HFRJBXQWUC7CE.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Salamandra".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(5);
                    decimal.setMaximum(30 );
                    decimal.setValue(5);
                    
                    String[] Colorc = {"amarillo", "naranja", "rojo", "negro", "marrón", "verde", "gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Salamandra");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://static.inaturalist.org/photos/159805189/large.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Tritón".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(300);
                    decimal.setMaximum(380 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "verde", "marrón", "gris", "amarillo", "naranja"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                  
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Tritón");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://4.bp.blogspot.com/-AmPsfb_hh44/WESY-MHoV_I/AAAAAAAAMjU/TGN-EM6uWhAWLYR-08DTtkONSehbSk8pgCEw/s1600/triton-cocodrilo-himalaya.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Rana venenosa".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(5);
                    decimal.setMaximum(20 );
                    decimal.setValue(5);
                    
                    String[] Colorc = { "amarillo", "rojo", "azul", "naranja", "verde"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Rana venenosa");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.nationalgeographic.com.es/medio/2022/12/12/rana-1_66a9a5c8_221212161515_1280x720.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Rana de Nueva Zelanda".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "marrón", "verde", "gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Rana de Nueva Zelanda");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.oasysparquetematico.com/wp-content/uploads/2018/02/shutterstock_1124007.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Rana de Seychelles".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(0);
                    decimal.setMaximum(10 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "verde", "marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Rana de Seychelles");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://ichef.bbci.co.uk/news/640/amz/worldservice/live/assets/images/2013/09/03/130903104919_ranas_624x351_afp.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Rana arborícola".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"verde", "marrón", "amarillo", "rojo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Rana arborícola");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/8/8b/Australia_green_tree_frog_%28Litoria_caerulea%29_crop.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Rana flecha azul".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(5 );
                    decimal.setValue(1);
                    
                    String[] Colorc = { "azul", "negro", "naranja", "verde"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Rana flecha azul");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://mundocachorro.com/wp-content/uploads/2018/10/rana-azul.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Axolotl o ajolote (salamandra mexicana)".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(60);
                    decimal.setMaximum(110 );
                    decimal.setValue(60);
                    
                    String[] Colorc = { "rosa", "blanco", "marrón", "negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                   String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Axolotl o ajolote (salamandra mexicana)");
          

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://files.worldwildlife.org/wwfcmsprod/images/Axolotl_WWsummer2021/story_full_width/9p390zn81y_axolotl_WWsummer2021.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Cecilia".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(100);
                    decimal.setMaximum(600 );
                    decimal.setValue(100);
                    
                    String[] Colorc = {"marrón", "gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));                    
                    OCA.setText("Cecilia");
    

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://resizer.glanacion.com/resizer/IgIo4vszOILbvvk6kQiM7phQZqo=/310x0/filters:format(webp):quality(80)/cloudfront-us-east-1.images.arcpublishing.com/lanacionar/MJPWIWN4GNAOJGWMMCKR5FQFRE.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                if ("Pigmeos Salamandra pie plano".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(15);
                    decimal.setMaximum(50 );
                    decimal.setValue(15);
                    
                    String[] Colorc = { "marrón", "gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Pigmeos Salamandra pie plano");
             

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://inaturalist-open-data.s3.amazonaws.com/photos/106353427/original.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }
                 if ("Jalapa tritón falso".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(10);
                    decimal.setMaximum(20 );
                    decimal.setValue(10);
                    
                    String[] Colorc = {"marrón", "verde", "gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {"Babosa","No babosa"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Jalapa tritón falso");
                    

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://blog.creaf.cat/wp-content/uploads/2021/11/Triturus_marmoratus-scaled-e1659352042754.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
  
                }

                sql = "SELECT * FROM anfibios";
                Tabla.setModel(modelo);

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs= st.executeQuery(sql);

                    while(rs .next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
            }
            
            
            
            if("Reptiles".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Habitad");
                OCG.setText("Reptiles");
                
                if ("Iguana".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(4);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "verde","café","negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Iguana");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Kini_iguana.jpg/220px-Kini_iguana.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Tortugas".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(900);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "verde","marrón","café"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra","mar","ambos"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Tortugas");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Imagen_tortuga_2.jpg/523px-Imagen_tortuga_2.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                
                if ("Tuátara".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = { "verde","negro","gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Tuátara");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/52/Tuatara.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Anfisbenio".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(5);
                    decimal.setMaximum(500 );
                    decimal.setValue(5);
                    
                    String[] Colorc = {"rojo","marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Anfisbenio");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Amphisbaenia.JPG/800px-Amphisbaenia.JPG");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Anolis".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(300);
                    decimal.setMaximum(800 );
                    decimal.setValue(300);
                    
                    String[] Colorc = {"verde","azul"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Anolis");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Anolis_sagrei.jpg/800px-Anolis_sagrei.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Lagartija".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(6 );
                    decimal.setValue(1);
                    
                    String[] Colorc = {"marrón","veige"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Lagartija");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Brown_Anole_Lizard_ARPT-LZ-BA-1.jpg/250px-Brown_Anole_Lizard_ARPT-LZ-BA-1.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Chucuala".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(300);
                    decimal.setMaximum(500 );
                    decimal.setValue(300);
                    
                    String[] Colorc = {"negro","gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Chucuala");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Chukwalla_Sauromalus_obesus.jpg/250px-Chukwalla_Sauromalus_obesus.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Agama".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(30);
                    decimal.setMaximum(300 );
                    decimal.setValue(30);
                    
                    String[] Colorc = {"marrón","verde","azul"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Agama");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/AgamaSinaita01_ST_10_edit.jpg/240px-AgamaSinaita01_ST_10_edit.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Dragón de Komodo".equals(nomAnimal)) {
                    Peso.setMinimum(70);
                    Peso.setMaximum(100);
                    Peso.setValue(70);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    
                    String[] Colorc = {"gris","negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Dragón de Komodo");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Komodo_dragon_Varanus_komodoensis_Ragunan_Zoo_2.JPG/220px-Komodo_dragon_Varanus_komodoensis_Ragunan_Zoo_2.JPG");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Geco".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(50);
                    decimal.setMaximum(80 );
                    decimal.setValue(50);
                    
                    String[] Colorc = {"verde","marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Geco");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/2/25/Geco_comune.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Camaleón".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(10);
                    decimal.setMaximum(120 );
                    decimal.setValue(10);
                    
                    String[] Colorc = {"verde","amarillo","rojo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Camaleón");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Bradypodion_pumilum_Cape_chameleon_female_IMG_1767_%28cropped%29.jpg/250px-Bradypodion_pumilum_Cape_chameleon_female_IMG_1767_%28cropped%29.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Salamanquesa".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(3 );
                    decimal.setValue(1);
                    
                    String[] Colorc = {"marrón","gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Salamanquesa");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Salamanquesa.jpg/800px-Salamanquesa.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                if ("Cocodrilos".equals(nomAnimal)) {
                    Peso.setMinimum(100);
                    Peso.setMaximum(900);
                    Peso.setValue(100);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"marrón","verde"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra","mar","ambos"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Cocodrilos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.ngenespanol.com/wp-content/uploads/2023/03/cual-es-la-diferencia-entre-un-caiman-y-un-cocodrilo.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Escinco".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    
                    decimal.setMinimum(50);
                    decimal.setMaximum(60 );
                    decimal.setValue(50);
                    
                    String[] Colorc = {"gris","plateado","marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Escinco");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://arbolabc.nyc3.cdn.digitaloceanspaces.com/Science/animals/Images_for_sliders/Images_for_sliders/escinco/skink1.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Eslizón".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(20);
                    decimal.setMaximum(40 );
                    decimal.setValue(20);
                    
                    String[] Colorc = {"gris","plateado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Eslizón");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://sierradebaza.org/Fichas_fauna/00_Reptiles/09-03_eslizon/1.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Teyú".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(500);
                    decimal.setMaximum(1000);
                    decimal.setValue(500);
                    
                    String[] Colorc = {"marrón","beige"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Teyú");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://cdn.pixabay.com/photo/2016/02/11/00/27/gold-tegu-1192929_1280.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Lagarto".equals(nomAnimal)) {
                    Peso.setMinimum(180);
                    Peso.setMaximum(228);
                    Peso.setValue(180);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"marrón","verde"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Lagarto");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/cd/Lagarto_Ocelado_-_Svitlana_Baydak.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Calango verde".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"amarillo","verde"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Calango verde");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://as2.ftcdn.net/v2/jpg/00/23/62/35/1000_F_23623544_Nu2m2UlnDYfyTX2DLyFX1kofJWhAmUj0.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Monstruo de Gila".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(3);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"marrón","negro","naranjo"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"tierra"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Monstruo de Gila");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.anipedia.net/imagenes/monstruo-de-gila-800x375.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Serpientes".equals(nomAnimal)) {
                    Peso.setMinimum(5);
                    Peso.setMaximum(100);
                    Peso.setValue(5);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"amarillo","verde","negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Habitad = {"mar","tierra","ambos"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Habitad));
                    
                    OCA.setText("Serpientes");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://static.mashpilodge.com/wp-content/uploads/2018/09/mashpi-vivoras-bothriechis-schlegelii-1024x683.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                OCG.setText("Reptiles");

                sql = "SELECT * FROM reptiles";
                Tabla.setModel(modelo);

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
                }

            if("Artrópodos".equals(Grupo)){

                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Cantidad Pares Patas");
                modelo.addColumn("Antenas");
                OCG.setText("Artrópodos");
                Carac_2.setVisible(true);
                
                if ("Insectos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(125);
                    decimal.setValue(1);
                    
                    String[] Colorc = {"Negro","Rojo","Amarillo","Verde","Azul","Morado","Blanco","Gris","Plateado","Dorado","Naranja","Marrón"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    String[] cantidadParesPatas = {"3"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(cantidadParesPatas));
                    Boolean[] Antena = {true};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Antena));
                    OCA.setText("Insectos");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://okdiario.com/img/2022/05/31/insecto-655x368.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Arácnidos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(165);
                    decimal.setValue(1);
                    
                    
                    String[] Colorc = {"Negro","Marrón","Gris","Rojo","Amarillo","Verde","Azul","Blanco","Plateado","Dorado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    String[] cantidadParesPatas = {"4"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(cantidadParesPatas));
                    Boolean[] Antena = {true};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Antena));
                    OCA.setText("Arácnidos");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Spiders_Diversity.jpg/270px-Spiders_Diversity.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Miriápodos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(60);
                    decimal.setValue(1);
                    
                    String[] Colorc = {"Marrón","Negro","Rojo","Amarillo","Verde","Azul","Gris","Blanco"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    String[] cantidadParesPatas = {"15","20","30","50","150"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(cantidadParesPatas));
                    Boolean[] Antena = {true};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Antena));
                    OCA.setText("Miriápodos");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://i.bssl.es/faunatura/2010/08/miriapodos.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Crustáceos".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(20);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Rojo","Azul","Verde","Amarillo","Blanco","Naranja","Marrón","Morado","Gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    String[] cantidadParesPatas = {"5","6","7","8","9","10"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(cantidadParesPatas));
                    Boolean[] Antena = {true};
                    Carac_2.setModel(new DefaultComboBoxModel<>(Antena));
                    OCA.setText("Crustáceos");

                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.ejemplos.cc/wp-content/uploads/2019/01/ejemplos-de-crustaceos.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                
                sql = "SELECT * FROM artrópodos";
                Tabla.setModel(modelo);

                String[] datos = new String[5];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);
                        datos[4] = rs.getString(5);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }

            } 
            if("Moluscos".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Tipo_de_Concha");
                OCG.setText("Moluscos");
                
                if ("Cefalópodos".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(500);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Rojo","Azul","Verde","Amarillo","Blanco","Naranja","Marrón","Negro","Morado","Gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] TipoConcha = {"sin concha"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(TipoConcha));
                    
                    OCA.setText("Cefalópodos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.cannabismagazine.net/wp-content/uploads/2018/10/pulpo.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Bivalvos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(10);
                    decimal.setMaximum(700);
                    decimal.setValue(10);
                    String[] Colorc = {"Blanco","Marrón","Negro","Azul","Verde","Rosa","Naranja","Amarillo","Gris","Morado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] TipoConcha = {"Concha"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(TipoConcha));
                    
                    OCA.setText("Bivalvos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.euston96.com/wp-content/uploads/2018/04/Bivalvos.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Gasterópodos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(0);
                    decimal.setMaximum(1000);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco","Marrón","Negro","Gris","Amarillo","Naranja","Rosado","Verde","Azul","Morado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] TipoConcha = {"Concha"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(TipoConcha));
                    
                    OCA.setText("Gasterópodos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://i.ytimg.com/vi/YVQ44n7Hi9w/maxresdefault.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                

                sql = "SELECT * FROM moluscos";
                Tabla.setModel(modelo);

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
            } 
            if("Equinodermos".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("tipo");
                OCG.setText("Equinodermos");
                
                if ("Estrella de Mar".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(5);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    
                    String[] Colorc = {"Rojo","Naranja","Amarillo","Marrón","Verde","Azul","Morado","Rosado","Blanco","Negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] tip = {"estrella"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(tip));
                    
                    OCA.setText("Estrella de Mar");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://t1.ea.ltmcdn.com/es/posts/4/7/8/ciclo_de_vida_de_la_estrella_de_mar_23874_orig.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Erizo".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(100);
                    decimal.setMaximum(999);
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Marrón","Negro","Blanco","Gris","Verde","Azul","Morado","Rosa","Amarillo","Naranja"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] tip = {"Erizo"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(tip));
                    
                    OCA.setText("Estrella de mar azul");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://erizomascota.com/wp-content/uploads/2022/05/como-saber-la-edad-de-un-erizo.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                sql = "SELECT * FROM equinodermos";
                Tabla.setModel(modelo);

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);


                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
            }
            if("Gusanos".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Tipo_de_Cuerpo");
                OCG.setText("Gusanos");
                
                if ("Anélidos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(5 );
                    decimal.setValue(1);
                    
                    String[] Colorc = {"Rojo","Verde","Marrón","Gris","Amarillo","Naranja","Negro","Blanco","Azul","Morado"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] tipocuerpo = {"Vermiforme","Cilíndrico","Aplanado","Serpentiforme","Cónica","Segmentado","Setoso","Tubular"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(tipocuerpo));
                    
                    OCA.setText("Anélidos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://animalesde.net/wp-content/uploads/2018/02/animales-An%C3%A9lidos.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Nematodos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(10 );
                    decimal.setValue(1);
                    
                    String[] Colorc = {"Transparente","Blanco","Amarillo","Marrón","Gris","Negro"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] tipocuerpo = {"Filiforme","Cilíndrico","Alargado","Fusiforme","Filariforme","Vermiforme","Aguja","Hilo","Lombriz"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(tipocuerpo));
                    
                    OCA.setText("Nematodos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvcZY87ux52YK3UO1Mbfo9GorpkYOTrzlFFA&usqp=CAU");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Platelmintos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(5 );
                    decimal.setValue(1);
                    
                    String[] Colorc = {"Blanco","Amarillo","Naranja","Marrón","Rojo","Gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] tipocuerpo = {"Plano","Aplanado","Lámina","Folio","Cinta","Cestoda","Trematoda","Turbelarios","Escolex","Proglótide"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(tipocuerpo));
                    
                    OCA.setText("Platelmintos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://misanimales.com/wp-content/uploads/2019/06/platelmintos.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                
                sql = "SELECT * FROM gusanos";
                Tabla.setModel(modelo);

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
            }
            if("Poríferos".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                OCG.setText("Poríferos");
                
                if ("Esponjas".equals(nomAnimal)) {
                    Peso.setMinimum(1);
                    Peso.setMaximum(10000);
                    Peso.setValue(1);
                    decimal.setMinimum(0);
                    decimal.setMaximum(999 );
                    decimal.setValue(0);
                    
                    String[] Colorc = {"Blanco","Crema","Amarillo","Naranja","Rosado","Púrpura","Azul","Verde","Gris"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    
                    String[] Piel = {""};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Piel));
                    
                    OCA.setText("Esponjas");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://ih1.redbubble.net/image.1508410418.7411/pp,840x830-pad,1000x1000,f8f8f8.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }

                sql = "SELECT * FROM poríferos";
                Tabla.setModel(modelo);

                String[] datos = new String[3];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);


                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
            }
            if("Celentéreos".equals(Grupo)){
                modelo.addColumn("Nombre");
                modelo.addColumn("Peso");
                modelo.addColumn("Color");
                modelo.addColumn("Tentáculos");
                OCG.setText("Celentéreos");
                
                    if ("Medusas".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(200);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(999 );
                    decimal.setValue(1);
                    
                    String[] Colorc = {"Transparente", "Blanco", "Amarillo", "Naranja", "Rojo", "Rosa", "Morado", "Azul", "Verde", "Turquesa", "Gris", "Negro", "Marrón", "Plateado", "Dorado", "Fucsia", "Celeste", "Coral", "Lila", "Verde lima"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    String[] Tentaculos = {"Tentaculos"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Tentaculos));
                    
                    OCA.setText("Medusas");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.visitsealife.com/porto/media/yehhv3ch/encontre_06.jpg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                if ("Pólipos".equals(nomAnimal)) {
                    Peso.setMinimum(0);
                    Peso.setMaximum(0);
                    Peso.setValue(0);
                    decimal.setMinimum(1);
                    decimal.setMaximum(2 );
                    decimal.setValue(1);
                    
                    String[] Colorc = {"Transparente", "Blanco", "Amarillo", "Naranja", "Rojo", "Rosa", "Morado", "Azul", "Verde", "Turquesa", "Gris", "Negro", "Marrón", "Plateado", "Dorado", "Fucsia", "Celeste", "Coral", "Lila", "Verde lima"};
                    Color.setModel(new DefaultComboBoxModel<>(Colorc));
                    String[] Tentaculos = {"Sin Tentaculos"};
                    Carac_1.setModel(new DefaultComboBoxModel<>(Tentaculos));
                    
                    OCA.setText("Pólipos");


                    try {
                        //Direccion de la imagen.
                        url = new URL("https://www.autopista.es/uploads/s1/10/74/31/91/las-corrientes-conectan-cada-polipo-de-coral-como-si-fuera-un-solo-organismo.jpeg");
                        image = ImageIO.read(url).getScaledInstance(lblImagen2.getWidth(),lblImagen2.getHeight(), Image.SCALE_SMOOTH); 
                        //Colocar la imagen en el JLabel
                        lblImagen2.setIcon(new ImageIcon(image));


                    } catch (MalformedURLException ex) {
                        java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                
                
                sql = "SELECT * FROM celentéreos";
                Tabla.setModel(modelo);

                String[] datos = new String[4];
                Statement st;


                try{
                    st=(Statement) con.estableceConexion().createStatement();
                    rs = st.executeQuery(sql);

                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);

                        modelo.addRow(datos);
                    }

                    Tabla.setModel(modelo);

                }catch( Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
                }
            }

            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el número de filas: " + e.toString());
        }
        return null;

    }

    
    public double CalcularPesoProm(int caso) throws SQLException{
        //Preparamos todo lo necesario para hacer la consulta a la base de datos
        //El PreparedStatemente va a servir para enviar la consulta a la base de datos
        PreparedStatement stmt = null;
        //Hacemos la conexion
        Conexion conn = new Conexion();
        //El ResultSet nos ayudara a recibir los resultados de la consulta
        ResultSet rs = null;
        //promPeso será lo que vamos a retornar
        double promPeso= 0;
        try{
            String nombredeLaTabla = "";//Iniciamos un string vacio que acabando el switch tendra el nombre de alguna tabla
            switch (caso) {
                case 1:
                    nombredeLaTabla = "mamíferos";
                    break;
                case 2:
                    nombredeLaTabla = "peces";
                    break;
                case 3:
                    nombredeLaTabla = "reptiles";
                    break;
                case 4:
                    nombredeLaTabla = "aves";
                    break;
                case 5:
                    nombredeLaTabla = "anfibios";
                    break;
                case 6:
                    nombredeLaTabla = "artrópodos";
                    break;
                case 7:
                    nombredeLaTabla = "moluscos";
                    break;
                case 8:
                    nombredeLaTabla = "equinodermos";
                    break;
                case 9:
                    nombredeLaTabla = "gusanos";
                    break;
                case 10:
                    nombredeLaTabla = "poríferos";
                    break;
                case 11:
                    nombredeLaTabla = "celentéreos";
                    break;
                default:
                    throw new IllegalArgumentException("Opción no válida");
            }//El nombre de la tabla se va a definir en el switch
            //Aqui guardamos en un string la consulta SQL que haremos 
            String sql = "SELECT AVG(Peso) AS peso_promedio FROM "+nombredeLaTabla; //La consulta dice que se va a sacar el promedio de la columna Peso de la tabla "nombredeLaTabla"
            stmt = conn.estableceConexion().prepareStatement(sql);//Prepara la consulta
            rs = stmt.executeQuery();//La ejecuta
            //Este if ayudara a que podamos recorrer todas las filas
            if (rs.next()) {
                promPeso = rs.getDouble("peso_promedio");//Aqui se obtiene el peso promedio y se guarda 
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return promPeso;//Lo retornamos
    }
            

    
    public int obtenerCantidadFilas(String nombreTabla) {//Recibiremos el nombre de la tabla
        //cantidadFilas será lo que retornaremos
        int cantidadFilas = 0;
        //Preparamos lo necesario para hacer consultas a la base de datos
        PreparedStatement stmt = null;
        Conexion conn = new Conexion();
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) AS total FROM " + nombreTabla; //Esta sera la consulta SQL que mandaremos para que cuente todas las filas de x tabla
            stmt = conn.estableceConexion().prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                cantidadFilas = rs.getInt("total");
                //System.out.println("Mira la cantidad que te da para " + nombreTabla + ": " + cantidadFilas);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
             //Cerramos los recursos que ya no usamos.
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cantidadFilas;
        }
    
    public int BuscadorTablaGeneralGrupos(int op) throws SQLException{
        //Preparamos lo necesario para hacer consultas a la base de datos
        PreparedStatement stmt = null;
        Conexion conn = new Conexion();
        ResultSet rs = null;
        int ContadorGeneralGrupos= 0;//Este sera nuestro ContadorGeneralGrupos
        try {
            String nombreDelGrupo = "";
            switch (op) {
                case 1:
                    nombreDelGrupo = "Mamíferos";
                    break;
                case 2:
                    nombreDelGrupo = "Peces";
                    break;
                case 3:
                    nombreDelGrupo = "Reptiles";
                    break;
                case 4:
                    nombreDelGrupo = "Aves";
                    break;
                case 5:
                    nombreDelGrupo = "Anfibios";
                    break;
                case 6:
                    nombreDelGrupo = "Artrópodos";
                    break;
                case 7:
                    nombreDelGrupo = "Moluscos";
                    break;
                case 8:
                    nombreDelGrupo = "Equinodermos";
                    break;
                case 9:
                    nombreDelGrupo = "Gusanos";
                    break;
                case 10:
                    nombreDelGrupo = "Poríferos";
                    break;
                case 11:
                    nombreDelGrupo = "Celentéreos";
                    break;
                default:
                    throw new IllegalArgumentException("Opción no válida");
            }
            //Cuando salimos del switch nombreDelGrupo tendra un string con el nombre de algun grupo Si o Si
            String sql = "SELECT COUNT(*) AS Cantidad FROM animales_generales WHERE Grupo = '"+nombreDelGrupo+"'";//Cuenta todo lo de animales_generales donde encuentre que el grupo es igual a nombreDelGrupo
            stmt = conn.estableceConexion().prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ContadorGeneralGrupos = rs.getInt("Cantidad");//Nuestro ContadorGeneralGrupos obtendra la cantidad de veces que aparece el nombreDelGrupo en la tabla animales_generales
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
             //Cerramos los recursos que ya no usamos.
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return ContadorGeneralGrupos;
        }
   

    void AnimalAzar(JTable Tabla_grupo, JLabel anis, JLabel anitt, JLabel asig, JLabel Imagen, JSlider CB1, String itemSeleccionado2, String itemSeleccionado3, JComboBox<String> CB4) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int BuscadorAnimalTablaOrigen(int op, String nombreAnimal) throws SQLException{//Recibimos un int op y un string nombreAnimal
        //Preparamos lo necesario para hacer consultas a la base de datos
        PreparedStatement stmt = null;
        Conexion conn = new Conexion();
        ResultSet rs = null;
        int CMespecie= 0; //Iniciamos el int CMespecie
        try {
            String nombredeLaTabla = "";//iniciamos el string vacio para que se le ingrese algun grupo en el switch dependiendo de el op recibido
            switch (op) {
                case 1:
                    nombredeLaTabla = "mamíferos";
                    break;
                case 2:
                    nombredeLaTabla = "peces";
                    break;
                case 3:
                    nombredeLaTabla = "reptiles";
                    break;
                case 4:
                    nombredeLaTabla = "aves";
                    break;
                case 5:
                    nombredeLaTabla = "anfibios";
                    break;
                case 6:
                    nombredeLaTabla = "artrópodos";
                    break;
                case 7:
                    nombredeLaTabla = "moluscos";
                    break;
                case 8:
                    nombredeLaTabla = "equinodermos";
                    break;
                case 9:
                    nombredeLaTabla = "gusanos";
                    break;
                case 10:
                    nombredeLaTabla = "poríferos";
                    break;
                case 11:
                    nombredeLaTabla = "celentéreos";
                    break;
                default:
                    throw new IllegalArgumentException("Opción no válida");
            }
            //Cuando salimos del switch nombredeLaTabla tendra un string con el nombre de alguna tabla Si o Si
            String sql = "SELECT COUNT(*) AS Cantidad FROM " + nombredeLaTabla + " WHERE Nombre = '"+nombreAnimal+"'"; 
            stmt = conn.estableceConexion().prepareStatement(sql);//Aqui le mandamos una instruccion que dice asi: Cuenta todo lo de nombredeLaTabla donde encuentres que Nombre es igual a nombreAnimal
            rs = stmt.executeQuery();
            if (rs.next()) {
                CMespecie = rs.getInt("Cantidad");//El int CMespecie obtiene valor
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CAnimal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            //Cerramos los recursos que ya no usamos.
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return CMespecie;//Se retorna el valor de CMespecie
        }
    
    public void MostrarListasAnimalEspecifico(JTable paramTablaTotalAnimales, String nomTipo, String nombreAnimal) {
        
        Conexion con = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
  
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAnimales.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        if("Mamíferos".equals(nomTipo)){
            
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_Patas");
            
            sql = "SELECT * FROM mamíferos WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
            
        } 
        if("Aves".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_alas");
            
            sql = "SELECT * FROM aves WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        } 
        if("Peces".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_Aletas");
            modelo.addColumn("Escamas");
            
            sql = "SELECT * FROM peces WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[5];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);
                        datos[4] = rs.getString(5);
                    

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Anfibios".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Piel");
            
            sql = "SELECT * FROM anfibios WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Reptiles".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Habitad");
            
            sql = "SELECT * FROM reptiles WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        
        if("Artrópodos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("CantidadParesPatas");
            modelo.addColumn("Antenas");
            paramTablaTotalAnimales.setModel(modelo);
            String[] datos = new String[5];
            Statement st;
            sql = "SELECT * FROM artrópodos WHERE Nombre = '"+nombreAnimal+"'";

            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                    System.out.println("Llego aqui el animal que ingresaste fue: " +nombreAnimal);
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
            
        } 
        if("Moluscos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tipo_de_Concha");
            
            sql = "SELECT * FROM moluscos WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        } 
        if("Equinodermos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("tipo");
            
            sql = "SELECT * FROM equinodermos WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Gusanos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tipo_de_Cuerpo");
            
            sql = "SELECT * FROM gusanos WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Poríferos".equals(nomTipo)){
            modelo.addColumn("Nombres");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
           
            
            sql = "SELECT * FROM poríferos WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[3];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                   

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Celentéreos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tentáculos");
            
            sql = "SELECT * FROM celentéreos WHERE Nombre = '"+nombreAnimal+"'";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
    }
    
    
    
    
    public void ActualizarLista(JTable paramTablaTotalAnimales, String nomTipo) {
        
        Conexion con = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
  
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAnimales.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        if("Mamíferos".equals(nomTipo)){
            
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_Patas");
            
            sql = "SELECT * FROM mamíferos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
            
        } 
        if("Aves".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_alas");
            
            sql = "SELECT * FROM aves";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        } 
        if("Peces".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Cantidad_de_Aletas");
            modelo.addColumn("Escamas");
            
            sql = "SELECT * FROM peces";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[5];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2)+"Kg";
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);
                        datos[4] = rs.getString(5);
                    

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Anfibios".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Piel");
            
            sql = "SELECT * FROM anfibios";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Reptiles".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Habitad");
            
            sql = "SELECT * FROM reptiles";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        
        if("Artrópodos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("CantidadParesPatas");
            modelo.addColumn("Antenas");
            paramTablaTotalAnimales.setModel(modelo);
            String[] datos = new String[5];
            Statement st;
            sql = "SELECT * FROM artrópodos";

            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                    System.out.println("Llego aqui el animal que ingresaste fue: " +nombreAnimal);
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
            
        } 
        if("Moluscos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tipo_de_Concha");
            
            sql = "SELECT * FROM moluscos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        } 
        if("Equinodermos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("tipo");
            
            sql = "SELECT * FROM equinodermos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Gusanos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tipo_de_Cuerpo");
            
            sql = "SELECT * FROM gusanos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Poríferos".equals(nomTipo)){
            modelo.addColumn("Nombres");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
           
            
            sql = "SELECT * FROM poríferos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                   

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
        if("Celentéreos".equals(nomTipo)){
            modelo.addColumn("Nombre");
            modelo.addColumn("Peso");
            modelo.addColumn("Color");
            modelo.addColumn("Tentáculos");
            
            sql = "SELECT * FROM celentéreos";
            paramTablaTotalAnimales.setModel(modelo);
        
            String[] datos = new String[4];
            Statement st;


            try{
                st=(Statement) con.estableceConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2)+"Kg";
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);

                    modelo.addRow(datos);
                }

                paramTablaTotalAnimales.setModel(modelo);

            }catch( Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros" + e.toString());
            }
        }
    }



    
}
 