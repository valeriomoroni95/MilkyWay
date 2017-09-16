package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import entity.AdminUser;
import entity.User;

public class LoginTest {

	@Test
	public void TestLog() throws SQLException {
		
		Login log = new Login();
		User u = log.findByUsernameAndPassword("valeriomoroni", "valerio");
		User au = new AdminUser("valeriomoroni", "", "Valerio", "Moroni","valeriomoroni95@gmail.com");
		assertEquals(au.getUsername(), u.getUsername());
		assertEquals(au.getSurname(), u.getSurname());
		assertEquals(au.getName(), u.getName());
		assertEquals(au.getEmail(), u.getEmail());

	}

}
