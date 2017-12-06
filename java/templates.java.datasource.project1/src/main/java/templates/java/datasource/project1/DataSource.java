package templates.java.datasource.project1;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

	private static final DataSource INSTANCE = new DataSource();
	private ComboPooledDataSource dataSource;
	private String filePathDatabaseProperties = "src/main/resources/database/database.properties";

	private DataSource() {
	}

	public static DataSource getInstance() {
		return INSTANCE;
	}

	public Connection getConnection() {

		Connection connection = null;

		dataSource = getDataSource();

		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;

	}

	public void commit(Connection connection) {

		try {
			if (connection != null && !connection.isClosed()) {
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ComboPooledDataSource getDataSource() {

		if (dataSource == null) {

			try {

				Properties propertiesDataBase = getPropertiesDataBase();

				dataSource = new ComboPooledDataSource();
				dataSource.setDriverClass(propertiesDataBase.getProperty("driverClass"));
				dataSource.setJdbcUrl(propertiesDataBase.getProperty("jdbcUrl"));
				dataSource.setUser(propertiesDataBase.getProperty("user"));
				dataSource.setPassword(propertiesDataBase.getProperty("password"));
				dataSource.setMinPoolSize(Integer.parseInt(propertiesDataBase.getProperty("minPoolSize")));
				dataSource.setAcquireIncrement(Integer.parseInt(propertiesDataBase.getProperty("acquireIncrement")));
				dataSource.setMaxPoolSize(Integer.parseInt(propertiesDataBase.getProperty("maxPoolSize")));

			} catch (IOException | PropertyVetoException e) {
				e.printStackTrace();
			}

		}

		return dataSource;
	}

	private Properties getPropertiesDataBase() throws IOException {

		Properties propertiesDataBase;
		FileInputStream fileInputStream;

		try {
			propertiesDataBase = new Properties();
			fileInputStream = new FileInputStream(filePathDatabaseProperties);
			propertiesDataBase.load(fileInputStream);
			return propertiesDataBase;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void setFilePathDatabaseProperties(String filePathDatabaseProperties) {
		this.filePathDatabaseProperties = filePathDatabaseProperties;
	}

}
