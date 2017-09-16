package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.AdminUser;
import entity.CommonUser;
import entity.User;

public class UserDao {

	@SuppressWarnings("unused")
	private static DataSource dataSource;

	//Constructor
	public UserDao() {
		dataSource = new DataSource();
	}

	/**
	 * Find a user in the database by username and password
	 * @param username
	 * @param password
	 * @return User
	 * @throws SQLException
	 */

	public static User findByUsernameAndPassword(String username, String password) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		User user = null;
		ResultSet result = null;

		final String query = "SELECT * FROM \"user_milkyway\" WHERE \"user_id\" = '"+username+ "' AND \"password\" = '" +password+"';";

		try {

			DataSource d = new DataSource();
			connection = d.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

			result = statement.executeQuery(query);

			if (!result.first()) {

				return null;

			}else {

				String name = result.getString("name");
				String surname = result.getString("surname");
				String usernameLoaded = result.getString("user_id");
				String emailLoaded = result.getString("email");
				boolean is_admin = result.getBoolean("is_admin");
				System.out.println(is_admin);

				if (!is_admin) {

					user = new CommonUser(usernameLoaded, "", name, surname, emailLoaded);

				} else {

					user = new AdminUser(usernameLoaded, "", name, surname, emailLoaded);

				}

				result.close();
				statement.close();
				connection.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (result != null) {
				result.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}

		return user;
	}

	/**
	 * Register a new user if is not present in the database
	 * @param username
	 * @param password
	 * @param name
	 * @param surname
	 * @param email
	 * @return boolean
	 */

	public static boolean SignUpIfNotPresent(String username, String password, String name, String surname, String email) {

		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		final String query = "SELECT \"user_id\" FROM \"user_milkyway\" WHERE \"user_id\" = '"+username+"';";

		try {
			DataSource d = new DataSource();
			connection = d.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			result = statement.executeQuery(query);

			if (result == null) {
				System.out.println("UserDao.java: errore nel primo if");
				return false;

			}
			if(result.first())
				return false;
			else {
				String sql1 = "INSERT INTO \"user_milkyway\" VALUES ('"+username+"','"+name+"','"+surname+"',false,'"+email+"','"+password+"');";
				System.out.println("ho fatto la insert " + sql1);
				statement.executeUpdate(sql1);
			}
			result.close();
			statement.close();
			connection.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}
}