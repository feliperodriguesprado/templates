package templates.java.datasource.project1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.junit.Test;

public class TestDataSource {

	@Test(expected = Exception.class)
	public void setFileDatabasePropertiesShouldThrowExceptionWhenInvalidPath() throws Exception {

		Logger.getGlobal().info(
				"Teste -> Ao definir arquivo de propriedades do banco de dados deve lançar exceção quando o caminho do arquivo for inválido");

		DataSource.getInstance().setFileDatabaseProperties("src/main");

		DataSource.getInstance().getConnection();
	}

	@Test
	public void checkConnectionDatabase() throws Exception {

		Logger.getGlobal().info("Teste -> Verificar conexão com o banco de dados");

		Connection connection = DataSource.getInstance().getConnection();

		assertNotNull(connection);

		printPeopleList(connection);

		DataSource.getInstance().commit(connection);
		DataSource.getInstance().closeDataSource();
	}

	@Test
	public void checkMinPoolSizeDataSource() throws Exception {

		Logger.getGlobal().info("Teste -> Verificar o tamanho mínimo do pool de conexções com o banco de dados");

		Connection connection = DataSource.getInstance().getConnection();

		assertEquals(3, getNumberActiveConnectionsInDatabase(connection));

		DataSource.getInstance().commit(connection);
		DataSource.getInstance().closeDataSource();
	}

	@Test
	public void checkAcquireIncrementDataSource() throws Exception {

		Logger.getGlobal().info("Teste -> Verificar incremento de conexções com o banco de dados");

		Connection connection = DataSource.getInstance().getConnection();
		connection = DataSource.getInstance().getConnection();
		connection = DataSource.getInstance().getConnection();
		connection = DataSource.getInstance().getConnection();

		assertEquals(6, getNumberActiveConnectionsInDatabase(connection));

		DataSource.getInstance().commit(connection);
		DataSource.getInstance().closeDataSource();
	}

	@Test
	public void commitInsertInDatabase() throws Exception {

		Logger.getGlobal().info("Teste -> Realizar commit de um INSERT no banco de dados");

		PreparedStatement preparedStatement;
		int rowsAffected, generatedKey = 0;
		ResultSet resultSet;
		String query = "insert into templates.people (first_name, last_name, birth_date) values ('Roberto', 'Pereira', '1985-07-26')";

		Connection connection = DataSource.getInstance().getConnection();

		try {
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected == 1) {
				resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					generatedKey = resultSet.getInt(1);
				}

				Logger.getGlobal().info("ID inserido = " + generatedKey);
				DataSource.getInstance().commit(connection);
			} else {
				Logger.getGlobal().severe("Erro ao realizar INSERT. Causa: nenhuma linha afetada");
			}

		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}

		connection = DataSource.getInstance().getConnection();

		// Lista tabela templates.people:
		printPeopleList(connection);

		// Compara quantidade de registros na tabela templates.people:
		assertEquals(4, getCountPeople(connection));

		// Exclui registro criado para esse teste:
		deletePeopleById(connection, generatedKey);

		DataSource.getInstance().commit(connection);
		DataSource.getInstance().closeDataSource();
	}

	@Test
	public void rollbackInsertInDatabase() throws Exception {

		Logger.getGlobal().info("Teste -> Realizar rollback de um INSERT no banco de dados");

		PreparedStatement preparedStatement;
		int rowsAffected, generatedKey = 0;
		ResultSet resultSet;
		String query = "insert into templates.people (first_name, last_name, birth_date) values ('Roberto', 'Pereira', '1985-07-26')";

		Connection connection = DataSource.getInstance().getConnection();

		try {
			preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected == 1) {
				resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					generatedKey = resultSet.getInt(1);
				}

				Logger.getGlobal().info("ID inserido = " + generatedKey);
				DataSource.getInstance().rollback(connection);
			} else {
				Logger.getGlobal().severe("Erro ao realizar INSERT. Causa: nenhuma linha afetada");
			}

		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}

		connection = DataSource.getInstance().getConnection();

		// Lista tabela templates.people:
		printPeopleList(connection);

		// Compara quantidade de registros na tabela templates.people:
		assertEquals(3, getCountPeople(connection));

		DataSource.getInstance().commit(connection);
		DataSource.getInstance().closeDataSource();
	}

	private int getNumberActiveConnectionsInDatabase(Connection connection) throws Exception {

		String query = "select count(pid) from pg_stat_activity where datname = 'postgres'";
		int numberActiveConnections = 0;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				numberActiveConnections = resultSet.getInt(1);
			}

			Logger.getGlobal().info("Número de conexões ativas = " + Integer.toString(numberActiveConnections));
			return numberActiveConnections;

		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}
	}

	private void printPeopleList(Connection connection) throws Exception {

		PreparedStatement preparedStatement;
		ResultSet resultSet;

		try {
			preparedStatement = connection.prepareStatement("select * from templates.people");
			resultSet = preparedStatement.executeQuery();

			System.out.println("Lista da tabela templates.people:");
			System.out.println("--------------------------------------------------");
			System.out.println("ID\tFIRST NAME\tLAST NAME\tBIRTH DATE");
			System.out.println("--------------------------------------------------");

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t" + resultSet.getString("first_name") + "\t\t"
						+ resultSet.getString("last_name") + "\t\t" + resultSet.getDate("birth_date"));
			}

		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}
	}

	private int getCountPeople(Connection connection) throws Exception {

		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int countPeople = 0;

		try {
			preparedStatement = connection.prepareStatement("select count(id) from templates.people");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				countPeople = resultSet.getInt(1);
			}

			return countPeople;

		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}
	}

	private void deletePeopleById(Connection connection, int id) throws Exception {

		PreparedStatement preparedStatement;
		int rowsAffected;
		String query = "delete from templates.people where id = ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setObject(1, id);
			rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected == 2) {
				Logger.getGlobal().severe("Erro ao realizar DELETE. Causa: nenhuma linha afetada");
			}
		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
			throw e;
		}
	}
}
