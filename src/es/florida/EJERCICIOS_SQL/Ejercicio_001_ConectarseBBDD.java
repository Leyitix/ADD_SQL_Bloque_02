package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ejercicio_001_ConectarseBBDD {

	//	Realiza un programa que importe la librería necesaria para poder realizar una conexión a 
	//	una base de datos MySQL, realice la conexión a la base de datos anterior y muestre un 
	//	mensaje si se ha hecho o no con éxito.

	// IMPORTAR FICHERO: proyecto -> buildpath -> libraries -> modulepath -> add
	// external jars -> .jar mysql

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			System.out.println("Conexión correcta!");
			con.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}

	}

}
