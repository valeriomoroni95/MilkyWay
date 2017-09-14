<%@page import="boundary.ShowObjectBean" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%

ShowObjectBean showObjectBean = new ShowObjectBean();

showObjectBean.setBand(0.0);
showObjectBean.setMapName("HiGal");

showObjectBean.importMapNames();
//showObjectBean.importClumpDatas();
//showObjectBean.importSourceDatas();

Vector<String> maps = showObjectBean.getMapNames();

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
                <h3>Maps</h3>
              </div>

              <div class="title_right">
              
                <div class="col-md-8 col-sm-5 col-xs-12 form-group pull-right top_search">
                
                		<form>
						
						<div class="col-md-4">
						
							<% if (maps != null) { %>
							
                				<select name="map" class="form-control">
                				
                					<% for (String map : maps) { %>
                				
                					<option value="<%= map %>"><%= map %></option>
                					
                				<% } %>
                			
                				</select>
                				
                			<% } %>
                			
						</div>
						
                			<div class="col-md-4">
                			
                			<select name="band" class="form-control">
                			
                				<option value="null">All bands</option>
                				
                				<option value="70.0">70.0</option>
                				
                				<option value="160.0">160.0</option>
                				
                				<option value="250.0">250.0</option>
                				
                				<option value="350.0">350.0</option>
                				
                				<option value="500.0">500.0</option>
                				
                				<option value="3.6">3.6</option>
                				
                				<option value="4.5">4.5</option>
                				
                				<option value="5.8">5.8</option>
                				
                				<option value="8.0">8.0</option>
                				
                				<option value="24.0">24.0</option>
                			
                			</select>  
                			
                			</div>
                			
                			<div class="col-md-4">              			
                			
                				<input type="submit" value="search" class="btn btn-success">
                				
                			</div>
                			
                		
                		</form>
                
                </div>
              </div>

            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12">
                <div class="x_panel">

                  <div class="x_title">
                    <h2>Objects list</h2>
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
                        <th style="width: 20%">Object</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th style="width: 20%">#Edit</th>
                      </tr>
                      </thead>

                      <tbody>

                      	<tr>

                        		<td>#</td>
                        		
                        		<td><a></a></td>

                        		<td></td>

                        		<td></td>

                        		<td></td>
                        
                        		<td>
                          		<a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View</a>
                          		
                          		<a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit</a>
                          		
                          		<a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i>Delete </a>
                          		
                        		</td>

                      	</tr>

                      </tbody>

                    </table>
                    <!-- end project list -->

                  </div>

                </div>

              </div>

              <%@include file="parts/footer.jsp" %>
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
