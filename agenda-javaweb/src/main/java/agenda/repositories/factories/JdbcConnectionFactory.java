package agenda.repositories.factories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnectionFactory {
  public static Connection getConnection() throws SQLException, IOException {
		InputStream is = JdbcConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties");
		if(is == null)
			throw new FileNotFoundException("Arquivo de configurações do BD não encontrado");
		Properties props = new Properties();
		props.load(is);
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Connection connection = DriverManager.getConnection(props.getProperty("connectionString"),props.getProperty("user"),props.getProperty("password"));
    return connection;
  }
}