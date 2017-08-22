package dao;

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
    public UserDao() {

        dataSource = new DataSource();

    }

    public static User findByUsernameAndPassword(String username, String password) throws SQLException {

        Connection connection = null;

        PreparedStatement statement = null;

        User user = null;

        ResultSet result = null;

        final String query = "SELECT user_id, name, surname, is_admin, mail, password FROM user WHERE user_id = '"
                + username + "' AND password = '" + password + "';";

        try {

            connection = dataSource.getConnection();

            statement = connection.prepareStatement(query);

            result = statement.executeQuery();

            if (result == null) {

                return null;

            }else {

                String name = result.getString("name");

                String surname = result.getString("surname");

                String usernameLoaded = result.getString("user_id");

                String emailLoaded = result.getString("mail");

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
    
    public static boolean SignUpIfNotPresent(String username, String password, String name, String surname, String email, int is_admin) {
    	 
    	 Connection connection = null;
         PreparedStatement statement = null;
         ResultSet result = null;

         final String query = "SELECT user_id, is_admin FROM user where user_id = '"
                 + username + "' AND tipo = '"+ is_admin +"';";
         
         try {
             connection = dataSource.getConnection();
             statement = connection.prepareStatement(query);
             result = statement.executeQuery();
             
             if (result == null) {
                 return false;

             }
             if(result.first())
            	 return false;
             else {
                 String sql1 = "INSERT INTO user " + "VALUES ('"+ username +"','"+ password +"','"+ name +"','"+ surname +"','"+ email +"','"+ is_admin + "')" +";";
                 int rc = statement.executeUpdate(sql1);
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
    
    


