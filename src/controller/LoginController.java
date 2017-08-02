package controller;

import boundary.LoginBean;
import dao.UserDao;
import entity.User;

public class LoginController {
	 private static LoginController instance;
	 private LoginBean lb;
	 private User user = null;

	    public static synchronized LoginController getInstance(LoginBean lb) {
	        if (instance == null)
	            instance = new LoginController(lb);
	        return instance;
	    }
	    
	    public User getUser(){
	    	if(this.user == null)
	    		return null;
	    	return user;
	    }
	    
	    public static LoginController getInstance() {
	        if (instance == null)
	            instance = new LoginController();
	        return instance;
	    }

	    private LoginController() {
	 
	    }
	    
	    private LoginController(LoginBean lb) {
	    	this.lb = lb;
	    }

	  
	    public User login(String username, String password) {
	        //user = UserDao.findByNameAndPassword(username, password);
	        return user;
	        
	    }
	    
	    public void removeUser(){
	    	this.user = null;
	    	lb.setUsername("");
	    	lb.setPassword("");
	    	lb.setName("");
	    	lb.setSurname("");
	    	lb.removeFound();
	    }
}
