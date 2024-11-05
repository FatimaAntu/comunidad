package Utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Util {

	public static Connection dameConexion() {
		Connection conn = null;
		
				try { // registro el driver de connection
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			// Establezco la conexion con la BBDD
	    try {
	    
	        conn = DriverManager.getConnection
	        		("jdbc:mysql://localhost:3306/comunidad?useSSL=false",
	        				"root","");
	  
	    } catch (SQLException ex) {
	        ex.printStackTrace() ;
	        System.out.println("SQLException : " + ex.getMessage());
	    }
		return conn;
	}
}
	/**
	 * 
	 * @param archivoScript , direccion y nombre del archivo de script a ejecutar
	 */
