package practica.junit.bean;

/*
 * Clase Usuario que contiene los atrivutos del la tabla SQL 
 * que se pretende guardar
 *
 * @Param email, password
 * 
 * @Autor Jorge
 */

public class Usuario {
	
	private String email;
	private byte[] password;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String email, byte[] password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [ identificador=" + email  + ", password=" + password + "]";
	}
	
	

}
