<%@page import="boundary.ClumpMassesBean" %>
<%@page import="entity.ClumpMass" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%

    ClumpMassesBean bean = new ClumpMassesBean();

    bean.importClumpMasses();
    bean.importStats();

    Vector<ClumpMass> clumps = bean.getMasses();


    String[] stats = bean.getStats();


    for (String stat : stats) {
        System.out.println("clumps-mass.jsp: stats " + stat);
    }

    String[] titles = {"Median", "Average", "Standard Deviation", "Absolute deviation"};

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

            <div class="row">

				<% if(clumps != null){ %>

                <div class="col-md-6 col-sm-12 col-xs-12">

                    <div class="x_panel">

                        <div class="x_title">

                            <h2>Clumps mass</h2>

                            <ul class="nav navbar-right panel_toolbox">
                                <li>
                                    <a class="collapse-link"><i class="fa fa-chevron-up"></i>
                                    </a>
                                </li>

                                <li>
                                    <a class="close-link"><i class="fa fa-close"></i></a>
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
                                    <th style="width: 20%">Clump id</th>
                                    <th>Mass</th>
                                </tr>
                                </thead>

                                <% if (clumps != null) { %>

                                <tbody>

                                <% int i = 1; %>

                                <% for (ClumpMass clump : clumps) { %>

                                <tr>
                                    <td>#<%= i %>
                                    </td>
                                    <td><%= clump.getClumpId() %>
                                    </td>
                                    <td><%= clump.getTotalMass() %>
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
                
                <% } %>


				<% if (stats != null){ %>

                <div class="col-md-6 col-sm-6 col-xs-12">
                
                
                    <div class="x_panel">
                    
                        <div class="x_title">
                        
                            <h2>Statistics</small></h2>
                            
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                </li>
                                <li><a class="close-link"><i class="fa fa-close"></i></a>
                                </li>
                            </ul>
                            
                            <div class="clearfix"></div>
                            
                        </div>
                        
                        <div class="x_content">

                            <table class="table table-striped project" style="width:100%">
                            
                            		<thead>
                            		
                            			<th></th>
                            			
                            			<th>Mass</th>
                            		
                            		</thead>

                                <tbody>

                                <% int i = 0; %>

                                <% for (String stat : stats) { %>
                                <tr>

                                    <td><strong><%= titles[i] %></strong></td>
                                    <td><%= stat %></td>

                                </tr>

                                <% i++; %>

                                <% } %>

                                </tbody>

                            </table>

                        </div>
                    
                    </div>

                </div>

				<% } %>

            </div>
            <!-- /page content -->

            <%@include file="parts/footer.jsp" %>
        </div>
    </div>

    <%@include file="parts/scripts.jsp" %>

</body>
</html>
