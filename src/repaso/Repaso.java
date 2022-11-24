package repaso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Repaso {

	private static final String BD = "peliculas";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner teclado = new Scanner(System.in);
		String sentencia = "";
		String editar = "";
		
		System.out.println("¿Quieres insertar, actualizar o eliminar un registro? (s/n)");
		editar = teclado.nextLine();
		
		while (editar.equals("s")) {
			System.out.println("");

			System.out.println("INTRODUCE UNA SENTENCIA (-salir- para terminar):");
			sentencia = teclado.nextLine();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BD, "root", "");
				PreparedStatement ps = con.prepareStatement(sentencia);
				
				System.out.println("¿Desea realmente ejecutar la sentencia (s/n)?");
				String confirmar = teclado.nextLine();
				int resultado = 0;
				
				if (confirmar.equals("s")) {
					resultado = ps.executeUpdate();
					if (resultado == 1) {
						System.out.println("Se ha modificado " + resultado + " fila");
					} else if(resultado == 0) {
						System.out.println("No se ha modificado ninguna fila");
					} else {
						System.out.println("Se han modificado " + resultado + " filas");
					}
				}
				con.close();
				
				System.out.println("¿Quieres insertar, actualizar o eliminar un registro? (s/n)");
				editar = teclado.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

}
