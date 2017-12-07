/**
 *  Java DataSource
    Copyright (C) 2017 felipeprado

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package templates.java.datasource.project1;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

	private static final DataSource INSTANCE = new DataSource();
	private static final String FILE_PATH_DATABASE_PROPERTIES = "src/main/resources/database/database.properties";
	private ComboPooledDataSource dataSource;
	private FileInputStream fileInputStreamDatabaseProperties;

	private DataSource() {
	}

	public static DataSource getInstance() {
		return INSTANCE;
	}

	public Connection getConnection() throws Exception {

		Connection connection;

		dataSource = getDataSource();

		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}

	}

	public void closeDataSource() {
		dataSource.close();
		dataSource = null;
	}

	public void commit(Connection connection) {

		try {
			if (connection != null && !connection.isClosed()) {
				connection.commit();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection connection) {
		
		try {
			if (connection != null && !connection.isClosed()) {
				connection.rollback();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private ComboPooledDataSource getDataSource() throws Exception {

		Properties propertiesDataBase;

		if (dataSource == null) {

			propertiesDataBase = getPropertiesDataBase();

			try {

				dataSource = new ComboPooledDataSource();
				dataSource.setDriverClass(propertiesDataBase.getProperty("driverClass"));
				dataSource.setJdbcUrl(propertiesDataBase.getProperty("jdbcUrl"));
				dataSource.setUser(propertiesDataBase.getProperty("user"));
				dataSource.setPassword(propertiesDataBase.getProperty("password"));
				dataSource.setMinPoolSize(Integer.parseInt(propertiesDataBase.getProperty("minPoolSize")));
				dataSource.setAcquireIncrement(Integer.parseInt(propertiesDataBase.getProperty("acquireIncrement")));
				dataSource.setMaxPoolSize(Integer.parseInt(propertiesDataBase.getProperty("maxPoolSize")));

			} catch (PropertyVetoException e) {
				Logger.getGlobal().severe(e.getMessage());
				throw e;
			}
		}

		return dataSource;

	}

	private Properties getPropertiesDataBase() throws Exception {

		Properties propertiesDataBase = new Properties();

		try {

			if (fileInputStreamDatabaseProperties == null) {

				Logger.getGlobal().warning(
						"Arquivo de propriedades do banco de dados não informado. Será utilizado o arquivo padrão");

				fileInputStreamDatabaseProperties = new FileInputStream(FILE_PATH_DATABASE_PROPERTIES);

			}

			propertiesDataBase.load(fileInputStreamDatabaseProperties);

			fileInputStreamDatabaseProperties.close();
			fileInputStreamDatabaseProperties = null;

			return propertiesDataBase;

		} catch (IOException e) {

			Logger.getGlobal().severe(e.getMessage());
			throw e;

		}

	}

	public void setFileDatabaseProperties(String filePathDatabaseProperties) throws Exception {

		try {

			fileInputStreamDatabaseProperties = new FileInputStream(filePathDatabaseProperties);

		} catch (IOException e) {

			Logger.getGlobal().severe(e.getMessage());
			throw e;

		}

	}

}
