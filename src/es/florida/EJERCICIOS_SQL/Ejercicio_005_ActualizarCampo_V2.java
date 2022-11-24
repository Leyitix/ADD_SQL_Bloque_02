package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio_005_ActualizarCampo_V2 {

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
	
	@SuppressWarnings("resource")
	public static void actualizarCampo() throws ClassNotFoundException {
		String oldTitulo = null, oldDirector = null, oldPuntuacion = null, oldGenero = null;

		Scanner teclado = new Scanner(System.in);

		System.out.println("Indica el id de la pelicula que deseas modificar: ");
		String identificador = teclado.nextLine();

		System.out.println("Indica el campo	que deseas modificar: ");
		String campo = teclado.nextLine();

		int id = Integer.parseInt(identificador);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			Statement stmt = con.createStatement();

			System.out.println("¿Seguro que deseas actualizar la pelicula (s/n)?");
			String respuesta = teclado.nextLine();

			if (respuesta.equals("s")) {
				PreparedStatement psActualizar = con.prepareStatement(
						"UPDATE peliculas SET titulo=?, director=?, puntuacion=?, genero=? WHERE id=?");

				ResultSet rs = stmt.executeQuery("SELECT * FROM peliculas WHERE id=" + id);

				while (rs.next()) {

					oldTitulo = rs.getString(2);
					oldDirector = rs.getString(3);
					oldPuntuacion = rs.getString(4);
					oldGenero = rs.getString(5);

					if (campo.equals("titulo")) {
						System.out.println("Titulo: ");
						String titulo = teclado.nextLine();
						psActualizar.setString(1, titulo);
					} else {
						psActualizar.setString(1, oldTitulo);
					}

					if (campo.equals("director")) {
						System.out.println("Director: ");
						String director = teclado.nextLine();
						psActualizar.setString(2, director);
					} else {
						psActualizar.setString(2, oldDirector);
					}

					if (campo.equals("puntuacion")) {
						System.out.println("Puntuación: ");
						String puntuacion = teclado.nextLine();
						psActualizar.setString(3, puntuacion);
					} else {
						psActualizar.setString(3, oldPuntuacion);
					}

					if (campo.equals("genero")) {
						System.out.println("Año publicación: ");
						String genero = teclado.nextLine();
						psActualizar.setString(4, genero);
					} else {
						psActualizar.setString(4, oldGenero);
					}

					psActualizar.setInt(5, id);
					psActualizar.executeUpdate();
				}

			} else {
				System.out.println("No se ha editado el registro");
			}
			
			stmt.close();
			con.close();

		} catch (Exception e) {
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
