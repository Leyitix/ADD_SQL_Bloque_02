package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio_004_ActualizarCampo {

	// Implementa un método que permita, dado un id de la tabla, actualizar alguno
	// de sus campos
	// (el nuevo valor del campo se introducirá por teclado).

	public static class Pelicula {

		private String titulo, director, puntuacion, genero;

		Pelicula() {
		}

		Pelicula(String titulo, String director, String puntuacion, String genero) {
			this.titulo = titulo;
			this.director = director;
			this.puntuacion = puntuacion;
			this.genero = genero;
		}

		public String toString() {
			String info = "Titulo: " + titulo + " - Director: " + director + " - Puntuación: " + puntuacion
					+ " - Genero: " + genero;
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

	public static void actualizarCampo() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Scanner teclado = new Scanner(System.in);

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			System.out.println("Introducir un id para modificar datos: ");
			String idModificar = teclado.nextLine();

			System.out.println("Titulo: ");
			String titulo = teclado.nextLine();
			System.out.println("Director: ");
			String director = teclado.nextLine();
			System.out.println("Puntuación: ");
			String puntuacion = teclado.nextLine();
			System.out.println("Genero: ");
			String genero = teclado.nextLine();

			PreparedStatement psActualizar = con.prepareStatement(
					"UPDATE peliculas SET titulo = '" + titulo + "', director = '" + director + "', puntuacion = '"
							+ puntuacion + "', genero = '" + genero + "' WHERE id = " + idModificar);

			System.out.println("¿Desea realmente eliminar la entrada (s/n)?");
			String confirmar = teclado.nextLine();
			int resultadoActualizar = 0;
			if (confirmar.equals("s")) {
				resultadoActualizar = psActualizar.executeUpdate();
			}

			if (resultadoActualizar > 0) {
				Pelicula nuevaPelicula = new Pelicula(titulo, director, puntuacion, genero);
				System.out.println("Pelucula actualizada en base de datos: " + nuevaPelicula.toString());
			} else {
				System.out.println("No se ha podido actualizar la fila");
			}
			con.close();
			teclado.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			actualizarCampo();
			mostrarTabla();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
