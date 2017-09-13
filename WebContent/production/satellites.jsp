<%@page import="boundary.SatelliteBean" %>
<%@page import="entity.Satellite" %>
<%@page import="entity.Agency" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%

SatelliteBean satelliteBean = new SatelliteBean();
satelliteBean.importSatellitesList();
satelliteBean.importToolsList();

Vector<Satellite> satellites = satelliteBean.getSatellites();


%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <%@include file="parts/head.jsp" %>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        
        <%@include file="parts/header.jsp" %>
        
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>Satellites</h3>
              </div>

              <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                  </div>
                </div>
              </div>
            </div>
            
            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Satellites list</h2>
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

                    <!-- start project list -->
                    <table class="table table-striped projects">
                      <thead>
                        <tr>
                          <th style="width: 1%">#</th>
                          <th style="width: 20%">Satellite name</th>
                          <th>Agency</th>
                          <th>Tools</th>
                          <th>Status</th>
                          <th style="width: 20%">#Edit</th>
                        </tr>
                      </thead>
                      
                      <% if(satellites != null){ %>
                      
                      
                      <tbody>
                      	<% int i = 1; %>
                      	<% for(Satellite satellite : satellites){ %>
                                            
                      	<tr>
                         	<td>#<%= i %></td>
                          	<td>
                            		<a><%= satellite.getSatelliteName() %></a>
                            		<br />
                            		<small>First observation: <%= satellite.getSatelliteStart() %></small><br>
                            		
                            		<% 
                            		
                            		String end = satellite.getSatelliteEnd();
                            		String duration = satellite.getDuration();
                            		
                            		if(end.equals("null") || end == null){ %>
                            			<small>Mission not completed</small>
                            		<% } else{ %> 
                            		
                            			<small>Mission ended: <%= end %></small><br>
                            			<small>Duration: <%= duration %></small>
                            		
                            		<% } %>
                            		
                          	</td>
                          	
                          	<% if(satellite.getAgencies() != null){ %>
                          	
                          		<td>
                          		
                          		<% for(Agency agency : satellite.getAgencies() ){ %>
                          		
                          			<%= agency.getAgencyName() %>
                          			
                          		<% } %>
                          		
                          		</td>
                          	
                          	<% } %>
                          	
                          	<td>
                          	<% if(satellite.getTools() != null){ 
                          	
                          		for(String tool : satellite.getTools()){ %>
                          			
                          			<%= tool %>
                          			
                          		<% }
                          	
                          	 } %>
                          	 
                          	</td>
                          	
                          	<td>
                          		<% if(end.equals("null") || end == null){ %>
                            		<button type="button" class="btn btn-danger btn-xs">In progress</button>
                            		<% }else{ %>
                            		<button type="button" class="btn btn-success btn-xs">Success</button>
                            		<% } %>
                            		
                          	</td>
                          	<td>
                            		<a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>
                            		<a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                            		<a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                          		</td>
                        	</tr>
                        	
                        	<% i++; %>
                      
                      	<% } %>
                      
                      
                      </tbody>
                      
                      
                      <% } %>
                      
                    </table>
                    <!-- end project list -->
                    
                  </div>
                  
                 </div>
                 
                </div>

        <%@include file="parts/footer.jsp" %>
      </div>
    </div>

    <%@include file="parts/scripts.jsp"%>
	
  </body>
</html>
