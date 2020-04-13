package practica.junit.gestionBBDD;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import practica.junit.bean.Usuario;
import practica.junit.excepciones.EmptyPassword;
import practica.junit.seguridad.DigestManager;

/*
 * Clase que gestiona la conexion a MySQL asi como las consultas
 * necesarias para el ejecutador
 *
 * SUSTITUIR LOS CARACTERES (****) POR TU USUARIO, CONTRASEÑA Y NOMBRE DE LA 
 * BASE DE DATOS MYSQL
 * 
 * @Autor Jorge
 * 
 */

public class GestionUsuarios {
	    
	    
	    public static Connection con = null;
	    private static ResultSet resultado;
	    private static final String USUARIO_BBDD = "****";
	    private static final String CONTRASENHA_BBDD = "******";
	   
	    
	   
	    // Realiza el guardado de usuario 
	    
	    public static void insertarUsuario(Usuario usuario) {
	    	
	        try {
	        	openConnection();
                String sql = "INSERT INTO usuariossha2 (email,contraseña1) VALUES (?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, usuario.getEmail());
                ps.setBytes(2, usuario.getPassword());
                ps.executeUpdate();
                System.out.println("GUARDADO :  " + usuario.toString());
                closeConnection();
	        } catch (Exception e) {
                System.out.println("Error " + e);
	        } 
		    	
	    }
	    
	    // encuentra la contraseña a partir del email 
	    
	    public static byte[] buscarContraseñaPorEmail(String email) throws EmptyPassword {
	    	byte[] contraseña = null;
	        try {
	        	openConnection();
                String sql = "SELECT contraseña FROM usuariossha2 WHERE email=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                resultado = ps.executeQuery();
                if (resultado.next()) {
                	contraseña = resultado.getBytes("contraseña");
                } 
                closeConnection();
                closeConnection();
	        } catch (Exception e) {
                System.out.println("Error " + e);
	        }
			return contraseña; 
		    	
	    }
	    
	    // Mira si existe el email en la bbdd
	    
	    public static boolean buscarPorEmail(String email) throws EmptyPassword  {
	    	boolean encontrado = false;
	        try {
	        	openConnection();
                String sql = "SELECT * FROM usuariossha2 WHERE email=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                resultado = ps.executeQuery();
                if (resultado.next()) {
                	encontrado = true;
                	System.out.println("El email " + email + " existe en la base de datos");
                	
                } 
                closeConnection();
                closeConnection();
	        } catch (Exception e) {
                System.out.println("Error " + e);
	        }
			return encontrado; 
		    	
	    }
	    
	    // Realiza la lectura de usuarios
	    
	    public static ArrayList<Usuario> leerUsuarios() {
	    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	    	Usuario usuario = null;
	    	
	        try {
	        	openConnection();
                String sql = "SELECT * FROM usuariossha2";
                PreparedStatement ps = con.prepareStatement(sql);
                resultado = ps.executeQuery();
                if (resultado.next()) {
                	usuario = new Usuario();
                    usuario.setEmail(resultado.getString("email"));
                    usuario.setPassword(resultado.getBytes("contraseña"));
                	usuarios.add(usuario);
                } else {
                	System.out.println("Sin registros ");
                }
                closeConnection();
	        } catch (Exception e) {
                System.out.println("Error " + e);
	        } 
	        
	        return usuarios;
		    	
	    }
	    
// Compara la contraseña introducida por la contraseña del usuario
	    
	    public static boolean compararContraseña(String contraseña, byte[] contraseñaEncontrada) throws EmptyPassword {
	    	
	    	boolean resultado = false;
	    	byte[] contraseñaIntroducida = DigestManager.getMessageDigest(contraseña);
	    	if (contraseña.equals("")) {
	    		throw new EmptyPassword();
	    	}
	        try {
	        	resultado = DigestManager.equalsDigest(contraseñaIntroducida, contraseñaEncontrada);
	        } catch (Exception e) {
                System.out.println("Error " + e);
	        } 
	        
	        return resultado;
		    	
	    }
	    
	   // Abre una conexion a la base de datos SQL
	    
	    private static void openConnection() {
	    	System.out.println("Conectando..." );
	        try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/*****?serverTimezone=UTC", USUARIO_BBDD, CONTRASENHA_BBDD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error " + e);
			}
	    }
	    // Cierra la conexión 
	    
	    public static void closeConnection() throws SQLException{
	        con.close();
	    }
	   
	    
	}



