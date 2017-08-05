package boundary;


public class RegistrationBean {
	private String usernamereg;
	private String namereg;
	private String surnamereg;
	private String passwordreg;
	private String repasswordreg;
	private String emailreg;
	private boolean is_Admin;
	
	public String getUsernamereg() {
		return usernamereg;
	}
	public void setUsernamereg(String usernamereg) {
		this.usernamereg = usernamereg;
	}
	public String getNamereg() {
		return namereg;
	}
	public void setNomeutentereg(String namereg) {
		this.namereg = namereg;
	}
	public String getSurnamereg() {
		return surnamereg;
	}
	public void setSurnamereg(String surnamereg) {
		this.surnamereg = surnamereg;
	}
	public String getRepasswordreg() {
		return repasswordreg;
	}
	public void setRepasswordreg(String repasswordreg) {
		this.repasswordreg = repasswordreg;
	}
	public String getPasswordreg() {
		return passwordreg;
	}
	public void setPasswordreg(String passwordreg) {
		this.passwordreg = passwordreg;
	}
	public String getEmailreg() {
		return emailreg;
	}
	public void setEmailreg(String emailreg) {
		this.emailreg = emailreg;
	}
	
	/*public boolean validate(){
		
		
		if(this.usernamereg.equals("")|| this.namereg.equals("") || this.surnamereg.equals("")
			|| this.passwordreg.equals("") || !this.passwordreg.equals(this.repasswordreg)|| this.emailreg.equals("")){
			return false;
		}
		if(!this.emailreg.contains("@") || !this.emailreg.contains(".")){
			return false;
			
		}
		
		if(this.passwordreg.length() < 9){
			return false;
		}
		
		
		//controlla che nel database non ci sia un utente con lo stesso nome e stesso tipo 
		RegistrationController controller = RegistrationController.getInstance();
		if( !controller.registration(this.usernamereg,this.namereg,this.surnamereg, this.passwordreg, this.emailreg, this.is_Admin)){
			
			return false;
		}
		
		return true;
	}*/
	
}
