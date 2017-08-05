<%@page import="boundary.LoginBean"%>
<%@page import="boundary.RegistrationBean" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<jsp:useBean id="loginBean" scope="session" class="boundary.LoginBean" />
<jsp:useBean id="registrationBean" scope="session" class="boundary.RegistrationBean" />

<jsp:setProperty name="loginBean" property="*" />
<jsp:setProperty name="registrationBean" property="*" />

<% 
	if(request.getParameter("log")!=null){
		if(loginBean.validate()){
%>
	<!-- <jsp:forward page="Menu.jsp"/> ci sarà la pagina di menù principale se loggato con successo -->		
<%
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
            <form>
              <h1>Login Form</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required="required" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="required" />
              </div>
              <div>
                <input name="log" type="submit" class="btn btn-default submit" value="Login">
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">New to site?
                  <a href="#signup" class="to_register"> Create Account </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> MilkyWay</h1>
                  <p>&copy;2017 All Rights Reserved. MilkyWay is a web app developed by Roberto & Valerio.</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form" novalidate>
          <section class="login_content">
            <form action="" method="post" novalidate>

              <h1>Create Account</h1>
              <div class="form-group">
                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required"/>
              </div>
              <div class="form-group">
                <input type="text" class="form-control" name="surname" placeholder="Surname" required="required"/>
              </div>
              <div class="form-group item">
                <input type="text" class="form-control" name="username" placeholder="Username"  required="required"/>
              </div>
              <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required="required" />
              </div>

              <div class="form-group">
              	<input type="email" class="form-control" name="confirm_email" placeholder="Confirm Email"  required="required">
              </div>

              <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required" />
              </div>

              <div class="form-group">
                <input type="password" class="form-control" name="confirm_password" placeholder="Confirm Password" required="required" />
              </div>

              <div class="form-group">
                <input name="reg" class="btn btn-default submit" type="submit" value="Register">
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i>MilkyWay</h1>
                  <p>&copy;2017 All Rights Reserved. MilkyWay is a web app developed by Roberto & Valerio.</p>
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
