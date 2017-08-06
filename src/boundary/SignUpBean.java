package boundary;

import controller.SignUpController;

public class SignUpBean {
	private String username;
	private String name;
	private String surname;
	private String password;
	private String repassword;
	private String email;
	private boolean is_Admin;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setNomeutente(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
		
		SignUpController controller = SignUpController.getInstance();
		if( !controller.SignUp(this.username,this.name,this.surname, this.password, this.email, this.is_Admin)){
			
			return false;
		}
		
		return true;
	}
	
}
