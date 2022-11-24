package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio_003_InsertarEnBBDD {

	// Implementa un método que permita leer por teclado los atributos para una
	// nueva entrada
	// en la tabla (puedes utilizar lo que ya has desarrollado en el bloque
	// anterior), cree la consulta
	// adecuada de inserción e introduzca los nuevos datos en la base de datos.
	// Comprueba
	// después que la inserción se ha realizado correctamente.
	
	public static class Pelicula {
		
		private String titulo, director, puntuacion, genero;
		
		Pelicula(){}
		
		Pelicula(String titulo, String director, String puntuacion, String genero) {
			this.titulo = titulo;
			this.director = director;
			this.puntuacion = puntuacion;
			this.genero = genero;
		}
		
		public String toString() {
			String info = "Titulo: " + titulo + " - Director: " + director + " - Puntuación: " + puntuacion + " - Genero: " + genero;
			return info;
			
		}
		
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public String getPuntuacion() {
			return puntuacion;
		}
		public void setPuntuacion(String puntuacion) {
			this.puntuacion = puntuacion;
		}
		public String getGenero() {
			return genero;
		}
		public void setGenero(String genero) {
			this.genero = genero;
		}
		
	}

	public static void mostrarTabla() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			System.out.println("Conexión correcta!");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM peliculas");
			System.out.format("%3s%30s%20s%15s%20s%2s", "id", "Titulo", "Director", "Puntuación", "Género", "\n");
			System.out.format("%3s%30s%20s%15s%20s%2s", "==", "======", "========", "==========", "======", "\n");
			while (rs.next()) {
				System.out.format("%3s%30s%20s%15s%20s%2s", rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), "\n");
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}

	}

	public static void insertarDatos() throws ClassNotFoundException {
		
		Scanner teclado = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			PreparedStatement psInsertar = con.prepareStatement("INSERT INTO peliculas (titulo, director, puntuacion, genero) VALUES (?,?,?,?)");
			
			System.out.println("¿Añadir una nueva pelicula (s/n)?");
			String respuesta = teclado.nextLine();
			while (respuesta.equals("s")) {
				System.out.println("Titulo: "); String titulo = teclado.nextLine();
				System.out.println("Director: "); String director = teclado.nextLine();
				System.out.println("Puntuación: "); String puntuacion = teclado.nextLine();
				System.out.println("Genero: "); String genero = teclado.nextLine();
				psInsertar.setString(1, titulo);
				psInsertar.setString(2, director);
				psInsertar.setString(3, puntuacion);
				psInsertar.setString(4, genero);
				int resultadoInsertar = psInsertar.executeUpdate();
				Pelicula nuevaPelicula = new Pelicula(titulo, director, puntuacion, genero);	// PODEMOS CREAR OBJETO PELICULA PARA MOSTRAR INFO POR CONSOLA
				if (resultadoInsertar > 0) {
					System.out.println("Pelicula guardada en base de datos: " + nuevaPelicula.toString());
				} else {
					System.out.println("No se ha podido insertar la nueva fila");
				}
				
				System.out.println("¿Añadir una nueva pelicula (s/n)?");
				respuesta = teclado.nextLine();
				
			}
			
			
			con.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}
		teclado.close();
	}

	public static void main(String[] args) {
		try {
			insertarDatos();
			mostrarTabla();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
