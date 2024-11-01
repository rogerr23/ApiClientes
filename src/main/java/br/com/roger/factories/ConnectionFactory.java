package br.com.roger.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection() {

		try {

			var host = "jdbc:mysql://localhost:3306/bdApiClientes";
			var user = "root";
			var pass = "Rogerjordan23";
			return DriverManager.getConnection(host, user, pass);
		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
