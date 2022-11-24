package es.florida.EJERCICIOS_XML;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ejercicio_002_NumeroNodos {
	
	//	 Crea un programa que implemente un parser para gestionar el fichero XML y devuelva por 
	//	 pantalla el número de nodos (objetos) que haya en el fichero

	public static void main(String[] args) {

		int idUltimo = 0;
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse( new File("peliculas.xml"));
			NodeList nodeList = document.getElementsByTagName("pelicula");
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				Element element = (Element) node;
				String id = element.getAttribute("id");
				idUltimo = Integer.parseInt(id);
			}
			
			System.out.println("Número de nodos: " + idUltimo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
