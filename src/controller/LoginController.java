package controller;

import java.sql.SQLException;

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
	    	
	        try {
	        	
				user = UserDao.findByUsernameAndPassword(username, password);
				
				System.out.println("LoginController.java: user is " + user);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
	        
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
