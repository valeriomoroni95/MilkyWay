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
}
