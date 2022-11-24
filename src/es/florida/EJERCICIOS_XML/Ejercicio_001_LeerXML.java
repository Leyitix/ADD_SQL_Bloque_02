package es.florida.EJERCICIOS_XML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;


public class Ejercicio_001_LeerXML {

	// Realiza un programa que dado el fichero creado en el ejercicio anterior lo muestre por pantalla línea a línea.

	public static void main(String[] args) {
		
		// ATENCION A LAS IMPORTACIONES!!
		try {
			
			File fichero = new File("peliculas.xml");
			FileReader fr = new FileReader(fichero, StandardCharsets.UTF_8); //	para que lo pueda leer correctamente
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				System.out.println(linea);
				linea = br.readLine();
			}
			br.close();
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
