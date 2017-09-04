package entity;

public class AdminUser implements User{
	
	private final static boolean is_admin = true;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
   
    
    public String getSurname() {
		return surname;
	}
    

	public AdminUser(String username, String password, String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
	}

	public String getName() {
		return name;
	}


	public boolean getAdmin() {
		return is_admin;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

}
