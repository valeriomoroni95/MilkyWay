<%@page import="boundary.SatelliteBean" %>
<%@page import="controller.SatelliteController" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>


<%

	String name = request.getParameter("satellite_name");
	String start = request.getParameter("satellite_start");
	String end = request.getParameter("satellite_end");
	String[] agencies = request.getParameterValues("agency");
	String[] tools = request.getParameterValues("tool");
	
	Vector<String> vectorAgencies = new Vector<String>();
	Vector<String> vectorTools = new Vector<String>();
	
	if(agencies != null){
		for(String agency: agencies){
			vectorAgencies.addElement(agency);
			System.out.println("Agency: " + agency);
		}
	}
	
	if(tools != null){
		for(String tool: tools){
			vectorTools.addElement(tool);
			System.out.println("Tool: " + tool);
		}
	}
	
	SatelliteBean satelliteBean = new SatelliteBean();
	
	satelliteBean.setName(name);
	satelliteBean.setStart(start);
	satelliteBean.setEnd(end);
	satelliteBean.setAgencies(vectorAgencies);
	satelliteBean.setTools(vectorTools);
	
	if(satelliteBean.validate()){
		System.out.println("add_new_satellite.jsp: validate TRUE");
	}else{
		System.out.println("add_new_satellite.jsp: validate FALSE");
	}
		
%>



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
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false"><i class="fa fa-wrench"></i></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Settings 1</a>
                                </li>
                                <li><a href="#">Settings 2</a>
                                </li>
                            </ul>
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

                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="agency" value="Nasa" class="flat"> NASA
                                    </label>
                                </div>

                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="agency" value="Esa" class="flat"> ESA
                                    </label>
                                </div>

                            </div>
                        </div>
                        
                        
                        <div class="form-group">

                            <label class="col-md-3 col-sm-3 col-xs-12 control-label">Tools</label>

                            <div class="col-md-9 col-sm-9 col-xs-12">

                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="tool" value="PACS" class="flat"> PACS
                                    </label>
                                </div>

                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="tool" value="SPIRE" class="flat"> SPIRE
                                    </label>
                                </div>
                                
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="tool" value="IRAC" class="flat"> IRAC
                                    </label>
                                </div>
                                
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="tool" value="MIPS" class="flat"> MIPS
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
        <!-- /page content -->

        <%@include file="parts/footer.jsp" %>
    </div>
</div>

<%@include file="parts/scripts.jsp" %>

</body>
</html>
