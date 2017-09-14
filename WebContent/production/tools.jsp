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
                          	
                          	<td><%= tool.getMapName() %></td>
                          	
                          	<td>
                          	
                            		<% if(tool.getBands() != null){ %>
                            		
                            			<% for(Double band : tool.getBands()){ %>
                            			
                            				<%= band %>
                            			
                            			<% } %> 
                            		
                            		<% } %>
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
