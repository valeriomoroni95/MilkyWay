<%@page import="boundary.SatelliteBean" %>
<%@page import="boundary.ToolBean" %>
<%@page import="entity.Agency" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%

boolean check_is_admin = false;

try{
	check_is_admin = (boolean) request.getSession().getAttribute("is_admin");
}catch(Exception e ){
	e.printStackTrace();
}

if(!check_is_admin){
	response.sendRedirect("index.jsp");
}

%>


<%

	SatelliteBean satelliteBean = new SatelliteBean();
	satelliteBean.importToolsList();
	satelliteBean.importAgenciesList();

	Vector<Agency> agenciesVectorObject = satelliteBean.getAgencies();
	Vector<String> toolsVectorString = satelliteBean.getAllTools();

	String name = request.getParameter("satellite_name");
	String start = request.getParameter("satellite_start");
	String end = request.getParameter("satellite_end");
	String[] agencies = request.getParameterValues("agency");
	String[] tools = request.getParameterValues("tool");
		
	boolean success = false;
	
	if(name != null && start != null){
		
		Vector<String> vectorAgencies = new Vector<String>();
		Vector<String> vectorTools = new Vector<String>();
		
		if(agencies != null){
			for(String agency: agencies){
				vectorAgencies.addElement(agency);
			}
		}
		
		if(tools != null){
			for(String tool: tools){
				vectorTools.addElement(tool);
			}
		}
		
		satelliteBean.setName(name);
		satelliteBean.setStart(start);
		
		if(end != ""){
			satelliteBean.setEnd(end);
		}else{
			satelliteBean.setEnd(null);
		}
		
		satelliteBean.setAgencyIds(vectorAgencies);
		satelliteBean.setTools(vectorTools);

		if(satelliteBean.validate()){
			success=true;
			System.out.println("add_new_satellite.jsp: validate TRUE");
		}else{
			System.out.println("add_new_satellite.jsp: validate FALSE");
		}
		request.getSession().setAttribute("success", success);
	}

%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<%@include file="parts/head.jsp" %>
<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <%@include file="parts/header.jsp" %>
        
        <!-- page content -->
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Add new satellite</h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <br/>
                    <form class="form-horizontal form-label-left new-satellite-form" method="POST" action="">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="satellite-name">Satellite Name<span
                                    class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="satellite-name" name="satellite_name" class="form-control col-md-7 col-xs-12" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="satellite-start">First
                                observation<span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="date" id="satellite-start" name="satellite_start" required="required"
                                       class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="satellite-end">Mission
                                ended</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="date" id="satellite-end" name="satellite_end"
                                       class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group">

                            <label class="col-md-3 col-sm-3 col-xs-12 control-label">Agency</label>

                            <div class="col-md-9 col-sm-9 col-xs-12">

								<% 
								
									for(Agency agencyObj : agenciesVectorObject) {
										
										int agencyId = agencyObj.getAgencyId();
										String agencyName = (String) agencyObj.getAgencyName();
								
								%>
								
									<div class="checkbox">
                                    		<label>
                                        		<input type="checkbox" name="agency" value="<%= agencyId %>" class="flat"> <%= agencyName %>
                                    		</label>
                                		</div>
								
								<% } %>

                            </div>
                        </div>
                        
                        <div class="form-group">

                            <label class="col-md-3 col-sm-3 col-xs-12 control-label">Tools</label>

                            <div class="col-md-9 col-sm-9 col-xs-12">

								<% for(String tool : toolsVectorString){ %>
									
									<div class="checkbox">
                                    		<label>
                                        		<input type="checkbox" name="tool" value="<%= tool %>" class="flat"> <%= tool %>
                                    		</label>
                                		</div>
									
								<% } %>

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
        <!-- /page content -->

        <%@include file="parts/footer.jsp" %>
    </div>
</div>

<%@include file="parts/scripts.jsp" %>

<%
    
    Boolean newSuccess = (Boolean) request.getSession().getAttribute("success");
                        		    
    if(newSuccess != null){
    	
    		System.out.print("newSuccess " + newSuccess);
    		
    		if(newSuccess){ %>
    		
    			<script type="text/javascript">
       	
   			new PNotify({
   				title: 'Success!',
   				text: 'A brand new satellite has been sucessfully added to the database',
    				type: 'success',
    				styling: 'bootstrap3'
    			});
			</script>
    		
    		<% }else{ %>
    		
    			<script type="text/javascript">
       	
   			new PNotify({
   				title: 'Oh No!',
   				text: 'New satellite cannot be added to the database. This satellite already exists',
    				type: 'error',
    				styling: 'bootstrap3'
    			});
			</script>
    		
    		<% } %>
    		
    <% } %>
    
    <% session.removeAttribute("success"); %>

</body>
</html>
