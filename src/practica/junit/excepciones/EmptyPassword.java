package practica.junit.excepciones;

public class EmptyPassword extends Exception{

	/**
	 * 
	 * Excepcion EmptyPassword diseñada para que se ejecute cuando 
	 * se ingresa una contraseña vacia
	 * 
	 * @Autor Jorge
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public EmptyPassword() {
		super();
	}

	
	@Override
    public String getMessage(){
		
		String mensaje = "No se ha encontrado la contraseña en la base de datos";
		
		return mensaje;
		
		
	}
	
	

}
