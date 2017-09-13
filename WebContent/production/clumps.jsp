<%@page import="boundary.ShowMassiveStarBean" %>
<%@page import="entity.Clump" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%


    ShowMassiveStarBean showMassiveStarBean = new ShowMassiveStarBean();

    showMassiveStarBean.importMassiveStarsList();

    Vector<Clump> clumps = showMassiveStarBean.getStars();

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

                <div class="col-md-12 col-sm-12 col-xs-12">

                    <div class="x_panel">

                        <div class="x_title">

                            <h2>These clumps can host a massive star</h2>

                            <ul class="nav navbar-right panel_toolbox">
                                <li>
                                    <a class="collapse-link"><i class="fa fa-chevron-up"></i>
                                    </a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li>
                                            <a href="#">Settings 1</a>
                                        </li>
                                        <li>
                                            <a href="#">Settings 2</a>
                                        </li>
                                    </ul>
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
                                    <th>Longitude</th>
                                    <th>Latitude</th>
                                    <th>Temperature</th>
                                    <th>Ratio</th>
                                    <th>Surface density</th>
                                    <th style="width: 20%">#Edit</th>
                                </tr>
                                </thead>

                                <% if (clumps != null) { %>

                                <tbody>

                                <% int i = 1; %>

                                <% for (Clump clump : clumps) { %>

                                <tr>
                                    <td>#<%= i %>
                                    </td>
                                    <td><%= clump.getClump_id() %>
                                    </td>
                                    <td><%= clump.getG_lon() %>
                                    </td>
                                    <td><%= clump.getG_lat() %>
                                    </td>
                                    <td><%= clump.getK_temp() %>
                                    </td>
                                    <td><%= clump.getRatio() %>
                                    </td>
                                    <td><%= clump.getSurf_dens() %>
                                    </td>
                                    <td>
                                        <a href="#" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View
                                        </a>
                                        <a href="#" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Edit </a>
                                        <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete
                                        </a>
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

            </div>

        </div>
        <!-- /page content -->

        <%@include file="parts/footer.jsp" %>
    </div>
</div>

<%@include file="parts/scripts.jsp" %>

</body>
</html>
