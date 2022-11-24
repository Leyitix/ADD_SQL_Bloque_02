package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_002_MostrarTabla {

	// Realiza un programa que importe la librería necesaria para poder realizar una
	// conexión a
	// una base de datos MySQL, realice la conexión a la base de datos anterior y
	// muestre un
	// mensaje si se ha hecho o no con éxito.

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			System.out.println("Conexión correcta!");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM peliculas");
			System.out.format("%3s%30s%20s%15s%20s%2s", "id", "Titulo", "Director", "Puntuación", "Género", "\n");
			System.out.format("%3s%30s%20s%15s%20s%2s", "==", "======", "========", "==========", "======", "\n");
			while (rs.next()) {
				System.out.format("%3s%30s%20s%15s%20s%2s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), "\n");
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}

	}

}
