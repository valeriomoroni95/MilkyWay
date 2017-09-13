<%

String uri = request.getRequestURI();

String pageName = uri.substring(uri.lastIndexOf("/")+1);

System.out.println("higal.jsp: page name " + pageName);

request.getSession().setAttribute("pageName" , pageName);

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
                <h3>Flux and Clump dimension import</h3>
              </div>

              <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                  </div>
                </div>
              </div>
            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Import flux and clump dimension (additional info) using a .csv file</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>

                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p>Drag multiple files to the box below for multi upload or click to select files. This is for demonstration purposes only, the files are not uploaded to any server.</p>
                    <form  class="dropzone" action="UploadFile.jsp" method="post" enctype="multipart/form-data"></form>
                    <br />
                    <br />
                    <br />
                    <br />
                  </div>
                </div>
              </div>
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
