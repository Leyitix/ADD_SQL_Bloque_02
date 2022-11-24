package es.florida.EJERCICIOS_XML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio_010_MostrarUnNodoPorId {
	
	//	1.	creamos el objeto pelicula
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
	
	//	2.	recuperamos todos los elementos del xml
	public static ListaPeliculas recuperarTodos() {
		@SuppressWarnings("unused")
		int idUltimo = 0;
		Pelicula pelicula;
		ListaPeliculas lista = new ListaPeliculas();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("peliculas.xml"));

			Element raiz = document.getDocumentElement();
			System.out.println("Contenido XML " + raiz.getNodeName() + ":");
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
				lista.anyadirPelicula(pelicula);
				idUltimo = Integer.parseInt(id);
			}
			
			return lista;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	//	2.	añadimos los elementos a una lista
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

	//	3.	recuperamos la pelicula correspondiente al id y devolvemos el resultado de tipo pelicula
	public static Pelicula recuperarPelicula(int id) {
		ListaPeliculas peliculas = new ListaPeliculas();
		peliculas =	recuperarTodos();
		Pelicula resultado = null;
		String identificador = String.valueOf(id);
		for (Pelicula pelicula : peliculas.getListaPeliculas()) {
			if (pelicula.getId().equals(identificador)) {
				resultado = pelicula;
				break;
			}
		}
		return resultado;
	}
	
	//	4.	mostramos el resultado
	public static void mostrarPelicula(int id) {
		Pelicula pelicula = recuperarPelicula(id);
		System.out.println("\nDetalles de la pelicula");
		System.out.println("ID: " + pelicula.getId());
		System.out.println("Titulo: " + pelicula.getTitulo());
		System.out.println("Director: " + pelicula.getDirector());
		System.out.println("Genero: " + pelicula.getGenero());
		System.out.println("Duración: " + pelicula.getDuracion());
		System.out.println("Estreno: " + pelicula.getEstreno());
	}

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Indica la pelicula que quieres ver: ");
		int idPelicula = teclado.nextInt();
		
		mostrarPelicula(idPelicula);
		
		teclado.close();
		
	}

}
