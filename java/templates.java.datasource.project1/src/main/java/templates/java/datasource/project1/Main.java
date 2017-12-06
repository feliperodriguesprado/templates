package templates.java.datasource.project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {

		System.out.println("Java Application DataSource Initialized.");

		Connection connection1 = DataSource.getInstance().getConnection();
		System.out.println("\nConnection 1:");
		getPeopleList(connection1);

		Connection connection2 = DataSource.getInstance().getConnection();
		System.out.println("\nConnection 2:");
		getPeopleList(connection2);
		
		Connection connection3 = DataSource.getInstance().getConnection();
		System.out.println("\nConnection 3:");
		getPeopleList(connection3);
		
		Connection connection4 = DataSource.getInstance().getConnection();
		System.out.println("\nConnection 4:");
		getPeopleList(connection4);
		
		DataSource.getInstance().commit(connection1);
		DataSource.getInstance().commit(connection2);
		DataSource.getInstance().commit(connection3);
		DataSource.getInstance().commit(connection4);

		Connection connection5 = DataSource.getInstance().getConnection();
		System.out.println("\nConnection 5:");
		getPeopleList(connection5);
		
		DataSource.getInstance().commit(connection5);

		System.exit(0);
	}

	public static void getPeopleList(Connection connection) {

		try {

			PreparedStatement preparedStatement = connection.prepareStatement("select * from templates.people");
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("--------------------------------------------------");
			System.out.println("ID\tFIRST NAME\tLAST NAME\tBIRTH DATE");
			System.out.println("--------------------------------------------------");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + "\t" + resultSet.getString("first_name") + "\t\t"
						+ resultSet.getString("last_name") + "\t\t" + resultSet.getDate("birth_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
