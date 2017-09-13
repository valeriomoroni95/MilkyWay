<%@page import="boundary.ShowClumpInfoBean" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%
    String clumpId = request.getParameter("clump_id");
    if (clumpId != null) {
    		Integer iClumpId = (Integer) Integer.parseInt(clumpId);
    		ShowClumpInfoBean showClumpInfoBean = new ShowClumpInfoBean();
        showClumpInfoBean.setClumpId(iClumpId);
        showClumpInfoBean.importClumpsInfo();
        Vector<String[]> clumpInfo = showClumpInfoBean.getClumpInfo();
        
        
        request.getSession().setAttribute( "clumpInfo", clumpInfo);
        
        if(clumpInfo != null){
        		
        		
        		for(int i = 1; i< clumpInfo.size(); i++){
        			String[] index = clumpInfo.get(i);
        			for(int k = 0; k<3; k++){
        				String element = index[k];
        				System.out.println(element);
        			}
        		}
		}
    }
        
    Vector<String[]> clumpInfoParam = (Vector<String[]>) request.getSession().getAttribute("clumpInfo");

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

                            <h2>Clump search</h2>

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

                            <form class="form-horizontal form-label-left" method="POST" action="">

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="clump_id">Clump id
                                        <span class="required">*</span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input type=text id="clump_id" name="clump_id"
                                               class="form-control col-md-7 col-xs-12" required>
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

			<% 
			
			if(clumpInfoParam != null){ 
			
				String[] mainClumpInfo = clumpInfoParam.get(0);
	    			String id = mainClumpInfo[0];
	    			String lat = mainClumpInfo[1];
	    			String lon = mainClumpInfo[2];
			
			%>

            <div class="row">

                <div class="col-md-12 col-sm-12 col-xs-12">

                    <div class="x_panel">

                        <div class="x_title">

                            <h2>Clump result for: <strong>Clump</strong> <%= id %>, <strong>Latitude</strong> <%= lat %>, <strong>Longitude</strong> <%= lon %></h2>

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
                                    <th>Band</th>
                                    <th>Value</th>
                                    <th>Error</th>
                                </tr>
                                </thead>

                                <tbody>
								<% 
								int i;
								
								for(i = 1; i< clumpInfoParam.size(); i++){ %>
								
                                <tr>
                                    <td>#<%= i %></td>
                                    
                                    <% String[] index = clumpInfoParam.get(i); %>
                                    
                                    <% for(int k= 0; k < 3; k++){ %>
                                    			
                                    		<td><%= index[k] %></td>
                                    
                                    <% } %>
                                    
                                </tr>
                                
                                <% } %>

                                </tbody>

                            </table>
                            <!-- end project list -->
                        
                        </div>

                    </div>

                </div>
                
            </div>

			<% 
			
			request.getSession().removeAttribute("clumpInfo");
			
			} %>

        </div>
        <!-- /page content -->

        <%@include file="parts/footer.jsp" %>
    </div>
</div>

<%@include file="parts/scripts.jsp" %>

</body>
</html>
