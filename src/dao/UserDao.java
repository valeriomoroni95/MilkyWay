package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.AdminUser;
import entity.CommonUser;
import entity.User;

public class UserDao {

    private static DataSource dataSource;

    //Constructor
    public UserDao() {

        dataSource = new DataSource();

    }

	public static User findByUsernameAndPassword(String username, String password) throws SQLException {
    	
    		System.out.println("UserDao.java: find user by username and password: " +  username + " " + password);

        Connection connection = null;
        Statement statement = null;
        User user = null;
        ResultSet result = null;

        final String query = "SELECT * FROM \"user\" WHERE \"user_id\" = '"+username+ "' AND \"password\" = '" +password+"';";
        
        System.out.println("UserDao.java: query: " +  query);

        try {
        	
            DataSource d = new DataSource();
            connection = d.getConnection();
            
            System.out.println("UserDao.java: connection from dataSource: " +  connection);
            
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            System.out.println("UserDao.java: statement: " +  statement);
            
            result = statement.executeQuery(query);
            
            if (!result.first()) {
            	
            		System.out.println("UserDao.java: result is NULL");

                return null;

            }else {
            	
            		System.out.println("UserDao.java: result is NOT NULL: " + result);

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
        	
        		System.out.println("UserDao.java: catch after try");

            e.printStackTrace();

        } finally {
        	
        		System.out.println("UserDao.java: finally");

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
        
        System.out.println("UserDao.java: user is" + user);
        
        return user;
    }
    
    public static boolean SignUpIfNotPresent(String username, String password, String name, String surname, String email, int is_admin) {
    	 
    	Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

         final String query = "SELECT \"user_id\", is_admin FROM \"user\" WHERE \"user_id\" = '"        
                 +username+ " ' AND \"is_admin\" = '"+ is_admin +"';";
         
         try {
             connection = dataSource.getConnection();
             statement = connection.createStatement();
             result = statement.executeQuery(query);
             
             if (result == null) {
                 return false;

             }
             if(result.first())
            	 return false;
             else {
                 String sql1 = "INSERT INTO user VALUES ('"+username+"','"+password+"','"+name+"','"+surname+"','"+email+"','"+is_admin+"')" +";";
                 statement.executeUpdate(sql1);
             }
             result.close();
             statement.close();
             connection.close();
             
         } catch (SQLException se) {
             // Errore durante l'apertura della connessione
             se.printStackTrace();
         } catch (Exception e) {
             // Errore nel loading del driver
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