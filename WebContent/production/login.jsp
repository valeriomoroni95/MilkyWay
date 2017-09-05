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
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	final User user;
	
	if(username != null && username != null){
		System.out.println("login.jsp: log is NOT NULL: " + username + " " + password);
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
	/*String name = request.getParameter("name");
	String surname = request.getParameter("surname");
	String username = request.getParameter("username");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	
	System.out.println("login.jsp: name: " + name);
	System.out.println("login.jsp: username: " + username);
	if(name!=null){
	SignUpBean signUpBean = new SignUpBean();
	
	signUpBean.setName(name);
	signUpBean.setSurname(surname);
	signUpBean.setUsername(username);
	signUpBean.setEmail(email);
	signUpBean.setPassword(password);
	
	if(signUpBean.validate()){
		System.out.println("signup.jsp: signup.validate() is TRUE, you are signed up");
	}
	else{
		System.out.println("signup.jsp: signup.validate() is FALSE, you're not signed up");
	}
	}*/

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
            <form method="POST" action="">
              <h1>Login Form</h1>
              <div>
                <input type="text" name="username" class="form-control" placeholder="Username" required="required" />
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="Password" required="required" />
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
            <!--<form method="GET" action="">

              <h1>Create Account</h1>
              <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="Name" required="required"/>
              </div>
              <div class="form-group">
                <input type="text" class="form-control" name="surname" placeholder="Surname" required="required"/>
              </div>
              <div class="form-group item">
                <input type="text" class="form-control" name="username" placeholder="Username"  required="required"/>
              </div>
              <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="Email" required="required" />
              </div>
              <div class="form-group">
              	<input type="email" class="form-control" placeholder="Confirm Email"  required="required">
              </div>

              <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="Password" required="required" />
              </div>

              <div class="form-group">
                <input type="password" class="form-control" placeholder="Confirm Password" required="required" />
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
            </form>-->
          </section>
        </div>
      </div>
    </div>
    <%@ include file="parts/scripts.jsp" %>
  </body>
</html>
