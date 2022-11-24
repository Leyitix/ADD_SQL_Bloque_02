package es.florida.EJERCICIOS_XML;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio_008_EliminarEntradaXML {
	
	// 1. creamos el objeto pelicula
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
			String infoCompleta = "Objeto pelicula -> Titulo: " + getTitulo() + " - Director: " + getDirector()
					+ " - Genero " + getGenero() + " - Duración " + getDuracion() + " - Estreno " + getEstreno();
			return infoCompleta;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public void setDuracion(String duracion) {
			this.duracion = duracion;
		}

		public void setEstreno(String estreno) {
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
	
	// 2. recuperamos todos los elementos del xml
	@SuppressWarnings("unused")
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
	
	// 3. añadimos los elementos a una lista
	public static class ListaPeliculas {
		private List<Pelicula> lista = new ArrayList<Pelicula>();
		public ListaPeliculas() {}

		public void anyadirPelicula(Pelicula pelicula) {
			lista.add(pelicula);
		}

		public List<Pelicula> getListaPeliculas() {
			return lista;
		}
	}
	
	// 4. recuperamos la pelicula correspondiente al id y devolvemos el resultado de
	// tipo pelicula
	public static Pelicula recuperarPelicula(int id) {
		ListaPeliculas lista = new ListaPeliculas();
		lista = recuperarTodos();
		Pelicula resultado = null;
		String identificador = String.valueOf(id);
		for (Pelicula pelicula : lista.getListaPeliculas()) {
			if (pelicula.getId().equals(identificador)) {
				resultado = pelicula;
				break;
			}
		}
		return resultado;
	}
	
	// 5. eliminar el resultado
	@SuppressWarnings("resource")
	public static void eliminarPelicula(int id) {
		Scanner teclado = new Scanner(System.in);
		
		Pelicula pelicula = recuperarPelicula(id);
		ListaPeliculas lista = new ListaPeliculas();
		lista = recuperarTodos();
		String identificador = String.valueOf(id);
		
		System.out.println(pelicula.toString());
		
		System.out.println("¿Estas seguro que deseas eliminar esta pelicula (s/n)?");
		String respuesta = teclado.next();
		
		if (!respuesta.equals("s"))	return;
		int indice = 0;
		for (Pelicula p : lista.getListaPeliculas()) {
			if (p.getId().equals(identificador)) {
				lista.getListaPeliculas().remove(indice);
				break;
			}
			indice++;
		}
		writeXmlFile(lista);
	
	}
	
	// 6. guardar en xml
	public static void writeXmlFile(ListaPeliculas lista) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.newDocument();

			Element raiz = document.createElement("peliculas");
			document.appendChild(raiz);

			for (Pelicula p : lista.getListaPeliculas()) {
				Element pelicula = document.createElement("pelicula");

				String id = String.valueOf(p.getId());
				pelicula.setAttribute("id", id);
				raiz.appendChild(pelicula);

				Element titulo = document.createElement("titulo");
				titulo.appendChild(document.createTextNode(String.valueOf(p.getTitulo())));
				pelicula.appendChild(titulo);

				Element director = document.createElement("director");
				director.appendChild(document.createTextNode(String.valueOf(p.getDirector())));
				pelicula.appendChild(director);

				Element genero = document.createElement("genero");
				genero.appendChild(document.createTextNode(String.valueOf(p.getGenero())));
				pelicula.appendChild(genero);

				Element duracion = document.createElement("duracion");
				duracion.appendChild(document.createTextNode(String.valueOf(p.getDuracion())));
				pelicula.appendChild(duracion);

				Element estreno = document.createElement("estreno");
				estreno.appendChild(document.createTextNode(String.valueOf(p.getEstreno())));
				pelicula.appendChild(estreno);
			}

			// Guardar documento en disco
			// Crear serializador
			TransformerFactory tF = TransformerFactory.newInstance();
			Transformer transformer = tF.newTransformer();

			// Darle formato al documento
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(document);

			try {
				// escribimos el resultado en el fichero
				FileWriter fw = new FileWriter("peliculas2.xml"); // EN ESTE CASO RECOMIENDO VOLCAR SOBRE UNO NUEVO
				StreamResult result = new StreamResult(fw);
				transformer.transform(source, result);
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Indica la pelicula que quieres eliminar: ");
		int idPelicula = teclado.nextInt();
		eliminarPelicula(idPelicula);
		teclado.close();

	}

}
