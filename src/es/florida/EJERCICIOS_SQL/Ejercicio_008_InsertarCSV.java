package es.florida.EJERCICIOS_SQL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio_008_InsertarCSV {

	@SuppressWarnings("resource")
	public static void insertarCSV() throws ClassNotFoundException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
			String titulo, autor, anyo_nac, anyo_pub, editorial, num_pag;
			File ficheroCSV = new File("libros.csv");
			FileReader fr = new FileReader(ficheroCSV, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine();
			linea = br.readLine();

			while (linea != null) {
				String[] elementosLibro = linea.split(";");
				for (int i = 0; i < elementosLibro.length; i++) {
					if (elementosLibro[i].equals("")) {
						elementosLibro[i] = "N.C.";
					}
				}

				titulo = elementosLibro[0];
				autor = elementosLibro[1];
				anyo_nac = elementosLibro[2];
				anyo_pub = elementosLibro[3];
				editorial = elementosLibro[4];
				num_pag = elementosLibro[5];

				PreparedStatement psInsertar = con.prepareStatement(
						"INSERT INTO libros (titulo, autor, anyo_nac, anyo_pub, editorial, num_pag) VALUES (?,?,?,?,?,?)");
				psInsertar.setString(1, titulo);
				psInsertar.setString(2, autor);
				psInsertar.setString(3, anyo_nac);
				psInsertar.setString(4, anyo_pub);
				psInsertar.setString(5, editorial);
				psInsertar.setString(6, num_pag);
				int resultadoInsertar = psInsertar.executeUpdate();
				if (resultadoInsertar > 0) {
					System.out.println("Libro guardado en base de datos");
					System.out.println(psInsertar.toString());
				} else {
					System.out.println("No se ha podido insertar la nueva fila");
				}
				psInsertar.close();
				linea = br.readLine();
			}
			con.close();
		} catch (SQLException e) {
			System.err.println("Error en la conexión");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		try {
			insertarCSV();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
