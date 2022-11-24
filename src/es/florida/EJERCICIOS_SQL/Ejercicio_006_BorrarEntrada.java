package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio_006_BorrarEntrada {

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
	
	public static void eliminarFila() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Scanner teclado = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			System.out.println("Introducir id para borrar entrada: ");
			String idBorrar = teclado.nextLine();
			PreparedStatement psBorrar = con.prepareStatement("DELETE FROM peliculas WHERE id= " + idBorrar);
			
			System.out.println("¿Desea realmente eliminar la entrada (s/n)?");
			String confirmar = teclado.nextLine();
			int resultadoBorrar = 0;
			if (confirmar.equals("s") ) {
				resultadoBorrar = psBorrar.executeUpdate();				
			} 
			
			if (resultadoBorrar > 0) {
				System.out.println("Pelicula eliminada en base de datos");
			} else {
				System.out.println("No se ha podido eliminada la fila");
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
			eliminarFila();
			mostrarTabla();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
