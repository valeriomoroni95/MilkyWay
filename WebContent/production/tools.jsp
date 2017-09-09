<%@page import="boundary.ToolBean" %>
<%@page import="entity.Tool" %>
<%@page import="java.util.*" %>

<%

ToolBean toolBean = new ToolBean();

toolBean.importTools();
toolBean.importBands();
toolBean.importMaps();

Vector<Tool> tools = toolBean.getTools();

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
                    <h2>Tools list</h2>
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
                          <th style="width: 20%">Tool Name</th>
                          <th>Map</th>
                          <th>Bands</th>
                          <th>Status</th>
                          <th style="width: 20%">#Edit</th>
                        </tr>
                      </thead>
                      
                      
                      <% if(tools != null){ %>
                      
                      	<tbody>
                      	
                      	<% int i = 1; %>
                      	
                      	<% for(Tool tool : tools){ %>
                      	<tr>
                          	<td>#<%= i %></td>
                          	<td>
                            		<a><%= tool.getToolName() %></a>
                            		<br />
                          	</td>
                          	
                          	<td><%= tool.getMapId() %></td>
                          	
                          	<td>
                          		<% System.out.println("tools.jsp: band list " + tool.getBandList()); %>
                          	
                            		<% if(tool.getBandList() != null){ %>
                            		
                            			<% for(Double band : tool.getBandList()){ %>
                            			
                            				<% System.out.println("tools.jsp: band " + band); %>
                            				
                            				<%= band %>
                            			
                            			<% } %> 
                            		
                            		<% } %>
                          	</td>
                          	<td>
                            		<button type="button" class="btn btn-success btn-xs">Success</button>
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
