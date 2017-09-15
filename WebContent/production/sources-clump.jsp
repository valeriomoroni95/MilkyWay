<%@page import="boundary.ShowSourcesInClumpBean" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%

	String sClump = request.getParameter("clump");
	String sBands = request.getParameter("band");
	
	if(sClump != null){
		
		ShowSourcesInClumpBean bean = new ShowSourcesInClumpBean();
		
		bean.setClumpId(Integer.parseInt(sClump));
		bean.setBandRes(Double.parseDouble(sBands));
		
		bean.importSourcesInClump();
		
		Vector<String[]> sources = bean.getSourcesInClump();
		
		System.out.println("sources-clump.jsp: sources " + sources);
		
		request.getSession().setAttribute("sources", sources);
		
	}
	
    Vector<String[]> sourcesParam = (Vector<String[]>) request.getSession().getAttribute("sources");

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

                            <h2>Sources in MIPSGAL that belong to a specific clump</h2>

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

                                <div class="form-group">

                                    <label class="col-md-3 col-sm-3 col-xs-12 control-label">Bands</label>

                                    <div class="col-md-9 col-sm-9 col-xs-12">

                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="70.0" class="flat"> 70.0 (us)
                                            </label>
                                        </div>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="160.0" class="flat"> 160.0
                                                (us)
                                            </label>
                                        </div>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="250.0" class="flat"> 250.0
                                                (us)
                                            </label>
                                        </div>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="350.0" class="flat"> 350.0
                                                (us)
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="500.0" class="flat"> 500.0
                                                (us)
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="3.6" class="flat"> 3.6 (us)
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="4.5" class="flat"> 4.5 (us)
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="5.8" class="flat"> 5.8 (us)
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="8.0" class="flat"> 8.0 (us)
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="band" value="24.0" class="flat"> 24.0 (us)
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

			<% if(sourcesParam != null){ %>

            <div class="row">

                <div class="col-md-12">

                    <div class="x_panel">

                        <div class="x_title">
                            <h2>Sources list</h2>
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
