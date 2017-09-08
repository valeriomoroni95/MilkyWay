<%@page import="boundary.ToolBean" %>
<%@page import="entity.Map" %>
<%@page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%


	ToolBean toolBean = new ToolBean();
	toolBean.importMaps();

	Vector<Map> maps = toolBean.getMaps();
	
%>

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
                    <h2>Add new tool</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#">Settings 1</a>
                          </li>
                          <li><a href="#">Settings 2</a>
                          </li>
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br />
                    <form method="post" action="" id="demo-form2" class="form-horizontal form-label-left" novalidate>
                    
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Name <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <input type="text" id="first-name" name="name" required="required" class="form-control col-md-7 col-xs-12">
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Map</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
                        
                          	<% if(maps != null){ %>
                          		
                          		<select class="form-control">
                          		
                        				<% for(Map map : maps){ %>
                        				
                        					<option><%= map.getMapName() %></option>
                        				
                        				<% } %>
                        				
                        			</select>
                        			
                        		<% } %>
                          
                        </div>
                      </div>
                      
                      <div class="form-group">
                      
                      	<label class="col-md-3 col-sm-3 col-xs-12 control-label">Bands</label>
                      	
                      	<div class="col-md-9 col-sm-9 col-xs-12">
                      	
                      		<div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 70.0 (us)
                            </label>
                          </div>
                      
                      <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 160.0 (us)
                            </label>
                          </div>
                          
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 250.0 (us)
                            </label>
                          </div>
                          
                          
                        <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 350.0 (us)
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 500.0 (us)
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 3.6 (us)
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 4.5 (us)
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 5.8 (us)
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 8.0 (us)
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" class="flat"> 25.0 (us)
                            </label>
                          </div>
                      	
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
	
  </body>
</html>
