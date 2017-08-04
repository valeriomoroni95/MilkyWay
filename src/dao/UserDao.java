package dao;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.AdminUser;
import entity.CommonUser;
import entity.User;


public class UserDao {

	private static DataSource dataSource;
	
	//Constructor
	public UserDao(){
		dataSource = new DataSource();
	}
	
	public static User findByNameAndPassword (String username, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		User u = null;
		try {
			String select = "SELECT user_id, name, surname, is_admin, mail password FROM user  where user_id = '"
                    + username + "' AND password = '" + password + "';";
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(select);
			ResultSet rs = statement.executeQuery();
			if (rs == null)
				return null;
			else {
				String name = rs.getString("name");
	            String surname = rs.getString("surname");
	            String usernameLoaded = rs.getString("user_id");
	            String emailLoaded = rs.getString("mail");
	            boolean is_admin = rs.getBoolean("is_admin");
	            
	            System.out.println(is_admin);
	            
	            if(is_admin == false)
	            	u = new CommonUser(usernameLoaded, "", name, surname, emailLoaded);
	            if(is_admin == true)
	            	u = new AdminUser(usernameLoaded, "", name, surname, emailLoaded);
	            
	            rs.close();
	            statement.close();
	            connection.close();

		}
		
				
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
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
		return u;
	}
}

