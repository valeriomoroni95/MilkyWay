<%@page import="boundary.YoungSourceObjectBean" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%

	String sClump = request.getParameter("clump");
	
	System.out.println("young-sources.jsp: sClump " + sClump);
	
	if(sClump != null){
		
		System.out.println("young-sources.jsp: sClump != null " + sClump);
		
		request.getSession().setAttribute("clumpId", sClump);
		
		YoungSourceObjectBean bean = new YoungSourceObjectBean();
		
		bean.setClumpId(Integer.parseInt(sClump));
		
		bean.importYoungSourceObjectBean();
		
		Vector<String[]> sources = bean.getYoungSources();
		
		System.out.println("young-sources.jsp: vector sources " + sources);
		
		request.getSession().setAttribute("young_sources", sources);
		
	}
	
    Vector<String[]> sourcesParam = (Vector<String[]>) request.getSession().getAttribute("young_sources");
    
    String clumpIdParam = (String) request.getSession().getAttribute("clumpId");

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

                            <h2>Young Sources in MIPSGAL that belong to a specific clump</h2>

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

                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="clump">Clump id <span
                                            class="required">*</span></label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">

                                        <input type="text" id="clump" name="clump" class="form-control"
                                               required="required"
                                               placeholder="Clump id">
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

			<% if(sourcesParam != null){ %>

            <div class="row">

                <div class="col-md-12">

                    <div class="x_panel">

                        <div class="x_title">
                            <h2>Sources list for clump <%= clumpIdParam %></h2>
                            
                            <% request.getSession().removeAttribute("clumpId"); %>
                            
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
                                    <th style="width: 20%">Source mapCode</th>
                                    <th>Latitude</th>
                                    <th>Longitude</th>
                                </tr>
                                </thead>

                                <tbody>
                                
                                <% int i = 1; %>

								<% for(String[] source : sourcesParam){ %>

                               	<tr>
                                    	<td>#<%= i %></td>
                                    	
                                    	<% for(String k : source){ %>
                                    	
									<td><%= k %></td>
									
									<% } %>
									
								</tr>
								
								<% i++; %>
								
								<% } %>
								
								<% request.getSession().removeAttribute("young_sources"); %>

                                </tbody>

                            </table>
                            <!-- end project list -->

                        </div>

                    </div>

                </div>

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
