package practica.junit.seguridad;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Clase que se encarga de convertir la variable contraseña 
 * en un valor numerico guardado en un array de bytes a traves 
 * de un algoritmo (en este caso el SHA_256)
 * 
 * @Autor Jorge
 * 
 */

public class DigestManager {
	
	public static final String SHA_224 = "SHA-224";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";
    
    public static byte[] getMessageDigest(String _password) {
        byte[] digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            md.update(_password.getBytes());
            digest = md.digest();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } 
        return digest;
    }
    
   
    public static boolean equalsDigest(byte[] digesta, byte[] digestb){
        return MessageDigest.isEqual(digesta, digestb);
    }

}
