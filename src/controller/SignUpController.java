package controller;

import boundary.SignUpBean;
import dao.UserDao;

public class SignUpController {
	
	private static SignUpController instance;
	
	@SuppressWarnings("unused")     //mai vero che la signup bean non è usata
	private SignUpBean sb;
	
	private boolean bool;
	
	
	public static synchronized SignUpController getInstance(SignUpBean sb) {
		
		if(instance == null) {
			instance = new SignUpController(sb);
		}
		return instance;
	}
	
	private SignUpController(SignUpBean sb) {
		this.sb = sb;
	}
	
	
	public boolean SignUp(String username, String password, String name, String surname, String email, boolean is_admin ) {
		try {
			bool=UserDao.SignUpIfNotPresent(username, password, name, surname, email);
			System.out.println("SignUpController.java: user can register? " + bool);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bool;
		
	}
}		
		
		
	