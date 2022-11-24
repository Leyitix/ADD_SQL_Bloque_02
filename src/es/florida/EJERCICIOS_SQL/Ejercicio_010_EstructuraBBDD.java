package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio_010_EstructuraBBDD {

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/peliculas", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("show TABLES");

			while (rs.next()) {
				System.out.format(rs.getString(1));
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}


	}

}
