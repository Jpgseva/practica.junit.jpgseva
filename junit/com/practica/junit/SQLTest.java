/**
 * 
 */
package com.practica.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practica.junit.gestionBBDD.GestionUsuarios;
import practica.junit.excepciones.EmptyPassword;

/**
 * 
 * TEST que se realizan:
 * 
 * 1- Cuando el usuario introduce una contraseña vacía, el método probado genera una excepción
 * del tipo “EmptyPassword”.
 * 
 * 2- Cuando el usuario introduce credenciales incorrectas, el método probado devuelve false
 * 
 * 3- Cuando el usuario introduce credenciales correctas, el método probado devuelve true
 * 
 * @author Jorge
 *
 */
class SQLTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	boolean credencialOk;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIntroducirContraseñaVacia() {
		//Verificar que al intentar introducir una contraseña vacia
		// genera una excepción	del tipo “EmptyPassword”
		try {
			String email = "******@hotmail.com";
			String contraseña = "";
			assertThrows(EmptyPassword.class, () -> GestionUsuarios.compararContraseña(contraseña, GestionUsuarios.buscarContraseñaPorEmail(email)));
			
		} catch (Exception e) {
			fail("EmptyPassword");
			
		}
		
		
	}
	
	@Test
	void credencialesIncorrectas() {
		try {
			String credencialIncorrecta = "******@gmail.com"; 
			assertFalse(GestionUsuarios.buscarPorEmail(credencialIncorrecta));
			
		} catch (Exception e) {
			fail("Fallo en la credencial --> boolean " + credencialOk);
		}
		
	}
	
	@Test
	void credencialesCorrectas() {
		try {
			String credencialCorrecta = "*******@hotmail.com";
			assertTrue(GestionUsuarios.buscarPorEmail(credencialCorrecta));
			
		} catch (Exception e) {
			fail("Fallo en la credencial --> boolean " + credencialOk);
		}
		
	}

}
