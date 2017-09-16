package tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DataSource;
import entity.AdminUser;
import entity.CommonUser;
import entity.User;

public class Login {
	
	
	public User findByUsernameAndPassword(String username, String password) throws SQLException {
    	

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
	
}