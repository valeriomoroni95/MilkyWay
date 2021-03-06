<%@page import="boundary.ShowObjectBean" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%
    
	ShowObjectBean showObjectBean = new ShowObjectBean();

	showObjectBean.importMapNames();
	Vector<String> maps = showObjectBean.getMapNames();

	String sMap = request.getParameter("map");
	String sBand = request.getParameter("band");
	
	System.out.println("objects-map.jsp: sBand " + sBand);

	if(sMap != null && sBand != null){
		
		//Set session parameter to display the search header
		request.getSession().setAttribute("mapName", sMap);
		request.getSession().setAttribute("band", sBand);
		
		//Set paramtere in object
		showObjectBean.setBand(Double.parseDouble(sBand));
	    showObjectBean.setMapName(sMap);
		
	    //Import datas from bean
	    showObjectBean.importClumpDatas();
	    showObjectBean.importSourceDatas();
	    
	    //Get data vectors
	    Vector<String[]> clumps = showObjectBean.getClumps();
	    Vector<String[]> sources = showObjectBean.getSources();
		
	    //Set session to display list of object
	    request.getSession().setAttribute("clumps", clumps);
	    request.getSession().setAttribute("sources", sources);
	    
	}
	
	//Get objects data from session
	Vector<String[]> clumpsParam = (Vector<String[]>) request.getSession().getAttribute("clumps");
	Vector<String[]> sourcesParam = (Vector<String[]>) request.getSession().getAttribute("sources");
	
	//Creat new vector for merging the two above
	Vector<String[]> objects = new Vector<String[]>();
	
	if(clumpsParam != null){
		for(String[] clump : clumpsParam){
			objects.addElement(clump);
		}
	}
	
	if(sourcesParam != null){
		for(String[] source : sourcesParam){
			objects.addElement(source);
		}
	}
	
	//Vector size
	int size = objects.size();
	System.out.println("Vector size: " + size);
	
	//Init pages variable
	int pages;
	
	//Check if divisble by 50
	if(size % 50 == 0){
		pages = size / 50;
	}else{
		pages = (size + 1) / 50;
	}
	
	System.out.println("Pages: " + pages);
		
	int currentPage;
	
	String mapNameParam = (String) request.getSession().getAttribute("mapName");
	String bandParam = (String) request.getSession().getAttribute("band");

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

                            <h2>Object in map</h2>

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


                                    <div class="col-md-6">

                                        <% if (maps != null) { %>

                                        <select name="map" class="form-control">
											
                                            <% for (String map : maps) { %>

                                            <option value="<%= map %>"><%= map %></option>

                                            <% } %>

                                        </select>

                                        <% } %>

                                    </div>

                                    <div class="col-md-6">

                                        <select name="band" class="form-control">

                                            <option value="0.0">All bands</option>

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
			
			<% if(clumpsParam != null || sourcesParam != null ){ %>
			
            <div class="row">
                <div class="col-md-12">
                    <div class="x_panel">

                        <div class="x_title">
                            <h2>Objects list in map <%= mapNameParam %> for band <% if(bandParam.equals("0.0")) { %> all <% }else{ %> <%= bandParam %> <% } %> </h2>
                            
                            <% 
                            
                            request.getSession().removeAttribute("mapName");
                            request.getSession().removeAttribute("band");
                            		
                            	%>
                            
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
                                    <th>Band resolution</th>
                                    <th>Value</th>
                                    <th>Error</th>
                                </tr>
                                </thead>

                                <tbody>

								<% if(objects != null){ %>
									
									<% int i = 1; %>
									
									<% for(String[] object : objects){ %>
									
									<tr>
									
										<% if((mapNameParam.equals("HiGal") && bandParam.equals("0.0"))) { %>
											
											<% if((i-1) % 5 == 0){ %>

												<td>#<%= (i-1)/5 + 1 %></td>

											<% }else{ %>
											
												<td></td>
											
											<% } %>
											
										<% }else if(mapNameParam.equals("Glimpse") && bandParam.equals("0.0")){ %>
									
											<% if((i-1) % 4 == 0){ %>

												<td>#<%= (i-1)/4 + 1 %></td>

											<% }else{ %>
											
												<td></td>
											
											<% } %>
									
                                    		<% }else{ %>
                                    		
                                    			<td>#<%= i %></td>
                                    		
                                    		<% } %>
                                    		
										<% for(String k : object){ %>

                                    		<td><%= k %></td>
                                    		
                                    		<% } %>
                                    		
                                    		<% i++; %>
                                    		
                                    		</tr>
                                    		
                                    <% } request.getSession().removeAttribute("clumps"); request.getSession().removeAttribute("sources"); %>

                                <% } %>
                                
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
