package es.florida.EJERCICIOS_XML;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ejercicio_004_ImplementarClaseObjeto {

	// Introduce en el programa anterior un método que implemente la clase objeto
	// que has
	// elegido para el XML. Puede ser un objeto Java común (POJO, Plain Old Java
	// Object) con
	// constructor, setters y getters

	public static class Pelicula {

		private String id, titulo, director, genero, duracion, estreno;

		public Pelicula(String id, String titulo, String director, String genero, String duracion, String estreno) {
			this.id = id;
			this.titulo = titulo;
			this.director = director;
			this.genero = genero;
			this.duracion = duracion;
			this.estreno = estreno;
		}
		
		public String toString() {
			String infoCompleta = "Objeto pelicula -> Titulo: " + getTitulo() + " - Director: " + getDirector() + " - Genero " + getGenero() + " - Duración " + getDuracion() +" - Estreno " + getEstreno();
			return infoCompleta;
		}

		public String getId() {
			return id;
		}

		public String getTitulo() {
			return titulo;
		}

		public String getDirector() {
			return director;
		}

		public String getGenero() {
			return genero;
		}

		public String getDuracion() {
			return duracion;
		}

		public String getEstreno() {
			return estreno;
		}

		@SuppressWarnings("unused")
		public static void main(String[] args) {

			int idUltimo = 0;
			Pelicula pelicula;

			try {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document document = dBuilder.parse(new File("peliculas.xml"));

				Element raiz = document.getDocumentElement();
				System.out.println("Contenido XML: " + raiz.getNodeName() + ":");
				NodeList nodeList = document.getElementsByTagName("pelicula");

				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					Element eElement = (Element) node;

					String id = eElement.getAttribute("id");
					String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
					String director = eElement.getElementsByTagName("director").item(0).getTextContent();
					String genero = eElement.getElementsByTagName("genero").item(0).getTextContent();
					String duracion = eElement.getElementsByTagName("duracion").item(0).getTextContent();
					String estreno = eElement.getElementsByTagName("estreno").item(0).getTextContent();

					pelicula = new Pelicula(id, titulo, director, genero, duracion, estreno);
					idUltimo = Integer.parseInt(id);
					
					System.out.println(pelicula.toString());
					
				}
				System.out.println("");
				System.out.println("Número de nodos: " + idUltimo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
