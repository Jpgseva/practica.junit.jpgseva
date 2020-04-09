package practica.junit.Ejecutador;


import java.util.ArrayList;
import java.util.Scanner;
import practica.junit.bean.Usuario;
import practica.junit.excepciones.EmptyPassword;
import practica.junit.gestionBBDD.GestionUsuarios;
import practica.junit.seguridad.DigestManager;

/*
 * 
 * Ejecutador con menu de consola que tiene las opciones:
 * 
 * 1- Insertar usuario: pide email y contraseña y lo guarda en la base de datos
 * 2- Ver usuarios: permite ver los usuarios guardados en la base de datos
 * 3- Test de Validación: permite comprobar si el mail y contraseña introducidos corresponden 
 * a un usuario guardado en la bbdd
 * 
 * @Autor Jorge
 * 
 */

public class Ejecutador extends GestionUsuarios{

	public static void main(String[] args) {
		
			
			Scanner entrada = new Scanner(System.in);
			String teclado;
			ArrayList <Usuario> lista = new ArrayList <Usuario>();
			Usuario usuario;
			byte[] contraseñaIntroducida;
			byte[] contraseñaEncontrada = null;
			boolean test = false;
			
			
			do {
				System.out.println("**************MENU**************");
				System.out.println("1 - Insertar usuario");
				System.out.println("2 - Ver usuarios");
				System.out.println("3 - TEST DE VALIDACION");
				System.out.println("9 - Salir");
				System.out.println();
				System.out.println("Introduzca opción: ");
				System.out.println("********************************");
				teclado = entrada.nextLine();
				while (!teclado.equals("8")) {
					if (teclado.equals("1")) {
						usuario = new Usuario();
						System.out.print("Introduzca tu email: ");
						usuario.setEmail(entrada.nextLine());
						System.out.print("Introduzca tu contraseña: ");
						contraseñaIntroducida = DigestManager.getMessageDigest(entrada.nextLine());
						usuario.setPassword(contraseñaIntroducida);
						insertarUsuario(usuario);
						System.out.println("********************************");
						System.out.println("Pulsa 8 para salir al menu");
						System.out.println("Pulsa 9 para salir de la aplicación");
						System.out.print("Introduzca opción: ");
						System.out.println("********************************");
						teclado = entrada.nextLine();
						if (teclado.equals("8")) {
							continue;
						} else if (teclado.equals("9")) {
							break;
						}
					} else if (teclado.equals("2")) {
						lista = leerUsuarios();
						for (Usuario aux : lista) {
							System.out.println("---------------USUARIOS----------------");
							System.out.println(aux.toString());
						}
						System.out.println("---------------------------------------");
						System.out.println();
						System.out.println("********************************");
						System.out.println("Pulsa 8 para salir al menu");
						System.out.println("Pulsa 9 para salir de la aplicación");
						System.out.print("Introduzca opción: ");
						teclado = entrada.nextLine();
						if (teclado.equals("8")) {
							continue;
						} else if (teclado.equals("9")) {
							break;
						}
					} else if (teclado.equals("3")) {
						do {
							System.out.println("---------------TEST--------------------");
							do {
								try {
									System.out.print("Introduce tu usuario y contraseña a comprobar");
									System.out.println("---------------------------------------");
									System.out.print("Introduzca tu email: ");
									String email = entrada.nextLine();
									test = buscarPorEmail(email);
									if (!test) {
										System.out.println("\n¡¡¡¡No existe un usuario con este email!!!\n");
										
									} else {
										System.out.println("\nUSUARIO CORRECTO!\n");
										contraseñaEncontrada = buscarContraseñaPorEmail(email);
										System.out.print("Introduzca tu contraseña: ");
										test = compararContraseña(entrada.nextLine(), contraseñaEncontrada);
										if (!test) {
											System.out.print("\n¡¡¡¡La contraseña introducida no es correcta!!!!\n");
										} else {
											System.out.println("********************************");
											System.out.print("\nTEST PASSADO!: La contraseña es correcta!\n");
										}
										
									}
									
								} catch (EmptyPassword e) {
									System.out.print("Error: " +  e);
									
								}
								
							} while (!test);	
						} while (contraseñaEncontrada.length == 0);
						
						System.out.println();
						System.out.println("********************************");
						System.out.println("Pulsa 8 para salir al menu");
						System.out.println("Pulsa 9 para salir de la aplicación");
						System.out.print("Introduzca opción: ");
						teclado = entrada.nextLine();
						if (teclado.equals("8")) {
							continue;
						} else if (teclado.equals("9")) {
							break;
						}	
					} else if (teclado.equals("9")) {
						System.out.print("Salida...");
						break;
					} else {
						System.out.println("Entrada incorrecta");
						teclado = "8";
						
					}
					entrada.close();
				}
								
			} while (!teclado.equals("9"));
			
	}

}
