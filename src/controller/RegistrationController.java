package controller;

import dao.UserDao;

public class RegistrationController {
	private static RegistrationController instance;

    public static RegistrationController getInstance() {
        if (instance == null)
            instance = new RegistrationController();
        return instance;
    }

    private RegistrationController() {
    }

  
   /* public boolean registration(String username, String nomeutente, String cognomeutente, String password, String email,int type) {
        boolean u = UserDao.registerIfNotPresent(username,nomeutente,cognomeutente,password,email,type);
        
        return u;
    }*/
}
