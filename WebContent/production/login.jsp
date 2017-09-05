<%@page import="boundary.LoginBean"%>
<%@page import="boundary.SignUpBean" %>
<%@page import="controller.LoginController" %>
<%@page import="entity.User" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<jsp:useBean id="loginBean" scope="session" class="boundary.LoginBean" />
<jsp:useBean id="registrationBean" scope="session" class="boundary.SignUpBean" />

<jsp:setProperty name="loginBean" property="*" />
<jsp:setProperty name="registrationBean" property="*" />

<% 
	String username_login = request.getParameter("username_login");
	String password_login = request.getParameter("password_login");
	
	final User user;
	
	if(username_login != null && password_login != null){
		System.out.println("login.jsp: log is NOT NULL: " + username_login + " " + password_login);
		loginBean.setUsername(username_login);
		loginBean.setPassword(password_login);
		if(loginBean.validate()){
			System.out.println("login.jsp: loginBean.validate() is TRUE, you are connected");
			user = loginBean.getUser();
			HttpSession s = request.getSession();
			s.setAttribute("isLoggedIn", user);
			s.setAttribute("name", user.getName());
			s.setAttribute("surname", user.getSurname());
			s.setAttribute("username", user.getUsername());
			System.out.println("login.jsp: Session get name" + s.getAttribute("isLoggedIn"));
			response.sendRedirect("index.jsp");
		}else{
			System.out.println("login.jsp: loginBean.validate() is FALSE, you are NOT connected");
		}
	}
%>

<%
	String name_register = request.getParameter("name_register");
	String surname_register = request.getParameter("surname_register");
	String username_register = request.getParameter("username_register");
	String email_register = request.getParameter("email_register");
	String password_register = request.getParameter("password_register");
	
	if(username_register!=null){
		SignUpBean signUpBean = new SignUpBean();
	
		signUpBean.setName(name_register);
		signUpBean.setSurname(surname_register);
		signUpBean.setUsername(username_register);
		signUpBean.setEmail(email_register);
		signUpBean.setPassword(password_register);
	
		if(signUpBean.validate()){
			System.out.println("signup.jsp: signup.validate() is TRUE, you are signed up");
		}else{
			System.out.println("signup.jsp: signup.validate() is FALSE, you're not signed up");
		}
	}

%>



<html lang="en">
  <%@ include file="parts/head.jsp"  %>
  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form method="POST" class="login-form">
              <h1>Login Form</h1>
              <div>
                <input type="text" name="username_login" class="form-control" placeholder="Username" required="required" />
              </div>
              <div>
                <input type="password" name="password_login" class="form-control" placeholder="Password" required="required" />
              </div>
              <div>
                <input type="submit" class="btn btn-default submit" value="Login">
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">New to site?
                  <a href="#signup" class="to_register"> Create Account </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1> MilkyWay</h1>
                  <p>&copy;2017 All Rights Reserved. MilkyWay is a web app developed by Roberto & Valerio & Luca.</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form class="register-form" method="POST" action="">

              <h1>Create Account</h1>
              <div class="form-group">
                <input type="text" class="form-control" name="name_register" placeholder="Name" required="required"/>
              </div>
              <div class="form-group">
                <input type="text" class="form-control" name="surname_register" placeholder="Surname" required="required"/>
              </div>
              <div class="form-group item">
                <input type="text" class="form-control" name="username_register" placeholder="Username"  required="required"/>
              </div>
              <div class="form-group">
                <input type="email" class="form-control" name="email_register" id="email_register" placeholder="Email" required="required" />
              </div>
              <div class="form-group">
              	<input type="email" class="form-control" name="email_register_confirm" placeholder="Confirm Email"  required="required">
              </div>

              <div class="form-group">
                <input type="password" class="form-control" name="password_register" id="password_register" placeholder="Password" required="required" />
              </div>

              <div class="form-group">
                <input type="password" class="form-control" name="password_register_confirm" placeholder="Confirm Password" required="required" />
              </div>

              <div class="form-group">
                <input type="submit" class="btn btn-default submit" value="Register">
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1>MilkyWay</h1>
                  <p>&copy;2017 All Rights Reserved. MilkyWay is a web app developed by Roberto & Valerio & Luca.</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
    <%@ include file="parts/scripts.jsp" %>
  </body>
</html>
