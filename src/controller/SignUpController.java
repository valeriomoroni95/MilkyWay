package controller;

public class SignUpController {
	private static SignUpController instance;
	
	public static SignUpController getInstance() {
		if(instance == null) {
			instance = new SignUpController();
		}
		return instance;
	}
	
	private SignUpController() {
		
	}
	
	public boolean SignUp(String username, String password, String name, String surname, String email, Boolean isadmin ) {
		
		//this need to be completed, I wrote the return just to avoid the error inside SignUpBean
		return true;
		
	}
}
