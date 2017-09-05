package boundary;

import controller.SignUpController;

public class SignUpBean {
	private String username;
	private String name;
	private String surname;
	private String password;
	private String email;
	private boolean is_admin;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean validate(){
		
		SignUpController controller = SignUpController.getInstance(this);
		System.out.println("SignUpBean.java: Controller: " + controller);
		if( !controller.SignUp(this.username,this.password,this.name, this.surname, this.email, this.is_admin)){
			System.out.println("SignUpBean.java: l'utente non può registrarsi: errore nella validate: " +this.username);
			return false;
		}
		System.out.println("la validate sta ritornando true");
		return true;
		
	}
	
}
