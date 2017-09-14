<%@page import="boundary.ShowClumpsInAreaBean" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%
    String sLat = request.getParameter("latitude");
    String sLon = request.getParameter("longitude");
    String sLenght = request.getParameter("lenght");
    String sIs_circle = request.getParameter("is_circle");

    if (sLat != null && sLon != null && sLenght != null) {
        Double lat = Double.parseDouble(sLat);
        Double lon = Double.parseDouble(sLon);
        Double lenght = Double.parseDouble(sLenght);
        boolean is_circle;
        if (sIs_circle == null) {
            is_circle = false;
        } else {
            is_circle = Boolean.parseBoolean(sIs_circle);
        }

        ShowClumpsInAreaBean showClumpsInAreaBean = new ShowClumpsInAreaBean();

        showClumpsInAreaBean.setLatitude(lat);
        showClumpsInAreaBean.setLongitude(lon);
        showClumpsInAreaBean.setLenght(lenght);
        showClumpsInAreaBean.setCircle(is_circle);
        showClumpsInAreaBean.importClumpsInArea();

        Vector<String[]> clumps = showClumpsInAreaBean.getClumpsInArea();

        request.getSession().setAttribute("clumps", clumps);
                
        /*for (String[] clump : clumps) {
            System.out.println("objects-area.jsp: clump " + clump);
            for (String i : clump) {
                System.out.println("objects-area.jsp: i " + i);
            }
        }*/

    }

    Vector<String[]> clumpsParam = (Vector<String[]>) request.getSession().getAttribute("clumps");


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

                            <h2>Object in region/area</h2>

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

                            <form class="form-horizontal" method="POST" action="">

                                <div class="form-group">

                                    <div class="col-md-3">

                                        <input type="text" name="latitude" class="form-control" required="required"
                                               placeholder="Latitude">

                                    </div>

                                    <div class="col-md-3">

                                        <input type="text" name="longitude" class="form-control" required="required"
                                               placeholder="Longitude">

                                    </div>

                                    <div class="col-md-3">

                                        <input type="text" name="lenght" class="form-control" required="required"
                                               placeholder="Lenght, radius or cateto">

                                    </div>

                                    <div class="col-md-2">

                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="is_circle" value="true" class="flat"> Is
                                                circle?
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

            </div>

            <% if (clumpsParam != null) { %>

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
                                    <th style="width: 20%">Object id</th>
                                    <th>Latitude</th>
                                    <th>Longitude</th>
                                    <th>Distance</th>
                                </tr>
                                </thead>

                                <tbody>

                                <% int k = 1; %>

                                <% for (String[] clump : clumpsParam) { %>

                                <tr>
                                    <td>#<%= k %>
                                    </td>

                                    <% for (String i : clump) { %>

                                    <td><%= i %>
                                    </td>

                                    <% } %>

                                </tr>

                                <% k++; %>

                                <% }
                                    request.getSession().removeAttribute("clumps"); %>

                                </tbody>

                            </table>
                            <!-- end project list -->

                        </div>

                    </div>

                </div>

                <%@include file="parts/footer.jsp" %>

            </div>

            <% } %>

        </div>

    </div>
    <!-- /page content -->

    <%@include file="parts/footer.jsp" %>
</div>
</div>

<%@include file="parts/scripts.jsp" %>

</body>
</html>