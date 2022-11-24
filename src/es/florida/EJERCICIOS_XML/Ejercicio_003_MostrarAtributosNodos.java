package es.florida.EJERCICIOS_XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio_003_MostrarAtributosNodos {
	
	//	 Amplía el programa anterior para que además recorra los nodos uno a uno y muestre por 
	//	 pantalla sus atributos.

	public static void main(String[] args) {

		int idUltimo = 0;
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse( new File("peliculas.xml"));
			NodeList nodeList = document.getElementsByTagName("pelicula");
			
			Element raiz = document.getDocumentElement();
			System.out.println("Conternido XML: " + raiz.getNodeName() + ":");
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println(""); // separamos cada nodo
				Element eElement = (Element) node;
				
				String id = eElement.getAttribute("id");
				System.out.println("ID pelicula: " + id);
				
				String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
				System.out.println(" -> Titulo: " + titulo);
				
				String director = eElement.getElementsByTagName("director").item(0).getTextContent();
				System.out.println(" -> Director: " + director);
				
				String genero = eElement.getElementsByTagName("genero").item(0).getTextContent();
				System.out.println(" -> Genero: " + genero);
				
				String duracion = eElement.getElementsByTagName("duracion").item(0).getTextContent();
				System.out.println(" -> Duración: " + duracion);
				
				String estreno = eElement.getElementsByTagName("estreno").item(0).getTextContent();
				System.out.println(" -> Estreno: " + estreno);
				
				idUltimo = Integer.parseInt(id);
			}
			
			System.out.println("Número de nodos: " + idUltimo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
