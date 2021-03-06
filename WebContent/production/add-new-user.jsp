<%@page import="boundary.SignUpBean" %>

<%

boolean check_is_admin = false;

try{
	check_is_admin = (boolean) request.getSession().getAttribute("is_admin");
}catch(Exception e ){
	e.printStackTrace();
}

if(!check_is_admin){
	response.sendRedirect("index.jsp");
}

%>

<%

String name = request.getParameter("name");
String surname = request.getParameter("surname");
String username = request.getParameter("username");
String email = request.getParameter("email");
String password = request.getParameter("password");

System.out.println("add-new-user.jsp: name: " + name);
System.out.println("add-new-user.jsp: username: " + username);

if(name!=null){
	SignUpBean signUpBean = new SignUpBean();

	signUpBean.setName(name);
	signUpBean.setSurname(surname);
	signUpBean.setUsername(username);
	signUpBean.setEmail(email);
	signUpBean.setPassword(password);
	
	boolean success = false;
	
	if(signUpBean.validate()){
		success = true;
		System.out.println("add-new-user.jsp: signup.validate() is TRUE, you are signed up");
	}else{
		System.out.println("add-new-user.jsp: signup.validate() is FALSE, you're not signed up");
	}	
	request.getSession().setAttribute("success", success);
}

%>

<!DOCTYPE html>
<html lang="en">
  <%@include file="parts/head.jsp" %>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        
        <%@include file="parts/header.jsp" %>
        
        <!-- page content -->
        <div class="right_col" role="main">
			<div class="x_panel">
                  <div class="x_title">
                    <h2>Add new user</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br />
                    <form method="post" action="" id="demo-form2" class="form-horizontal register-form form-label-left">
                    
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Name <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" name="name" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="surname">Surname <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="surname" name="surname" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>

					<div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">Username <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="username" name="username" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="email" id="email" name="email" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="confirm-email">Confirm Email <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="email" id="confirm-email" name="confirm_email" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">Password <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="password" id="password" name="password" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="confirm-password">Confirm Password <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="password" id="confirm-password" name="confirm_password" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <button class="btn btn-primary" type="button">Cancel</button>
						  <button class="btn btn-primary" type="reset">Reset</button>
                          <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                      </div>

                    </form>
                  </div>
                </div>
        </div>
        <!-- /page content -->

        <%@include file="parts/footer.jsp" %>
      </div>
    </div>

    <%@include file="parts/scripts.jsp"%>
    
    <%
    
    Boolean newSuccess = (Boolean) request.getSession().getAttribute("success");
                        		    
    if(newSuccess != null){
    	
    		System.out.print("newSuccess " + newSuccess);
    		
    		if(newSuccess){ %>
    		
    			<script type="text/javascript">
       	
   			new PNotify({
   				title: 'Success!',
   				text: 'New user has been sucessfully added to the database',
    				type: 'success',
    				styling: 'bootstrap3'
    			});
			</script>
    		
    		<% }else{ %>
    		
    			<script type="text/javascript">
       	
   			new PNotify({
   				title: 'Oh No!',
   				text: 'New user cannot be added to the database. This username already exists',
    				type: 'error',
    				styling: 'bootstrap3'
    			});
			</script>
    		
    		<% } %>
    		
    <% } %>
    
    <% session.removeAttribute("success"); %>
	
  </body>
</html>
