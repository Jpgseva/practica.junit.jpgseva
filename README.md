# practica.junit.jpgseva
Test Junit para probar el ingreso de credenciales en bbdd MySQL 

Se trata de introducir un usuario (email) y contraseña (codificada con algoritmo SHA2) en una base de datos MYSQL. Una vez introducidas probar si introduciendo tus credenciales (email) y contraseña estas son correctas segun el algoritmo.

Clases:

* Usuario
* Ejecutador
* EmptyPassword
* Gestion Usuarios
* DigestManager --> Algoritmo sha2
* SQLTest --> Test junit 

Ejecutador con menu de consola que tiene las opciones:
 
 1- Insertar usuario: pide email y contraseña y lo guarda en la base de datos
 2- Ver usuarios: permite ver los usuarios guardados en la base de datos
 3- Test de Validación: permite comprobar si el mail y contraseña introducidos corresponden 
 a un usuario guardado en la bbdd
 
 TEST JUNIT que se realizan:
 
 1- Cuando el usuario introduce una contraseña vacía, el método probado genera una excepción
 del tipo “EmptyPassword”.
  
 2- Cuando el usuario introduce credenciales incorrectas, el método probado devuelve false
  
 3- Cuando el usuario introduce credenciales correctas, el método probado devuelve true
