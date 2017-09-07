package entity;

public class CommonUser implements User{

	private final static boolean is_admin = false;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
   
    
    public String getSurname() {
		return surname;
	}
    

	public CommonUser(String username, String password, String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.setUsername(username);
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


	@Override
	public String getEmail() {
		return this.email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
