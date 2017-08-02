package boundary;

import entity.User;
import controller.LoginController;

public class LoginBean {
	private String username;
	private String password;
	private String nome;
	private String cognome;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setCognome(String cogn) {
		this.cognome = cogn;
	}

	public String getCognome() {
		return this.cognome;
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
		// Controllo sintattico
		boolean res1=true,res2=false;
		if (this.username.equals("") || this.password.equals("")) {
			res1=false;
		}
		
		
		LoginController controller = LoginController.getInstance(this);
		found = controller.login(this.username, this.password);
		if(found != null) {
			this.nome = found.getNome();
			this.cognome = found.getCognome();
			res2 = true;
		}
		
		if(res1==true && res2==true)
			return true;
		return false;
	}
	
}

	

