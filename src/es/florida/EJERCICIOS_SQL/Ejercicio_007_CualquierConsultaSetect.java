package es.florida.EJERCICIOS_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio_007_CualquierConsultaSetect {
	
	private static final String BD = "peliculas";

	public static void describirTabla() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BD, "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Describe "+ BD);

			while (rs.next()) {
				System.out.format("%20s", rs.getString(1));
			}
			System.out.println("");
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}
	}

	public static void consultaSelect(String consulta) throws ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BD, "root", "");
			Statement stmt = con.createStatement();;
			ResultSet rs = stmt.executeQuery(consulta);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCampos = rsmd.getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= numCampos; i++) { //	el indice tiene que empezar en uno
//					System.out.print(rs.getString(i) + " // ");
					System.out.format("%20s", rs.getString(i));
				}
				System.out.println("");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner teclado = new Scanner(System.in);
		String consulta = "";
		
		do {
			System.out.println("");
			System.out.println("INTRODUCE UNA CONSULTA (-salir- para terminar):");
			consulta = teclado.nextLine();
			describirTabla();
			System.out.println("");
			consultaSelect(consulta);
		} while (!consulta.equals("salir"));
	}

}
