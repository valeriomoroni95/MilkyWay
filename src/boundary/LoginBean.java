package boundary;

import entity.User;
import controller.LoginController;

public class LoginBean {
	private String username;
	private String password;
	private String name;
	private String surname;
	private User found;
	private String email;
	private boolean isadmin;

	public LoginBean() {
		this.username = "";
		this.password = "";
	}
	
	public void removeFound(){
		this.found = null;
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getPassword() {
		return this.password;
	}

	public void setName(String nome) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSurname(String cogn) {
		this.surname = surname;
	}

	public String getSurname() {
		return this.surname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean getAdmin() {
		return this.isadmin;
	}

	public boolean validate() {

		LoginController controller = LoginController.getInstance(this);
		
		found = controller.login(this.username, this.password);

		boolean res = false;

		if(found != null) {
			this.name = found.getName();
			this.surname = found.getSurname();
			res = true;
		}

		return res;
	}
	
}

	

