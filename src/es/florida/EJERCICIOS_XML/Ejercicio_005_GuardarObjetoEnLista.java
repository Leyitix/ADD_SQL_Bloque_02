package es.florida.EJERCICIOS_XML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ejercicio_005_GuardarObjetoEnLista {
	
	public static class ListaPeliculas{
		private List<Pelicula> lista = new ArrayList<Pelicula>();
		
		public ListaPeliculas() {}
		
		public void anyadirPelicula(Pelicula pelicula) {
			lista.add(pelicula);
		}
		
		public List<Pelicula> getListaPeliculas() {
			return lista;
		}
		
	}

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
	}

	public static void main(String[] args) {

		int idUltimo = 0;
		Pelicula pelicula;
		ListaPeliculas lista = new ListaPeliculas();

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("peliculas.xml"));

			Element raiz = document.getDocumentElement();
			System.out.println("Contenido XML: " + raiz.getNodeName() + ":");
			NodeList nodeList = document.getElementsByTagName("pelicula");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("");

				Element eElement = (Element) node;

				String id = eElement.getAttribute("id");
				System.out.println("ID película: " + id);

				String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
				System.out.println("Título: " + titulo);

				String director = eElement.getElementsByTagName("director").item(0).getTextContent();
				System.out.println("Director: " + director);

				String genero = eElement.getElementsByTagName("genero").item(0).getTextContent();
				System.out.println("Genero: " + genero);

				String duracion = eElement.getElementsByTagName("duracion").item(0).getTextContent();
				System.out.println("Duración: " + duracion);

				String estreno = eElement.getElementsByTagName("estreno").item(0).getTextContent();
				System.out.println("Estreno: " + estreno);

				pelicula = new Pelicula(id, titulo, director, genero, duracion, estreno);
				lista.anyadirPelicula(pelicula);
				idUltimo = Integer.parseInt(id);
			}
			System.out.println("");
			System.out.println("Número de nodos: " + idUltimo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
