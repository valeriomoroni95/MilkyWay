<%

boolean is_admin = false;

try{
	is_admin = (boolean) request.getSession().getAttribute("is_admin");
}catch(Exception e ){
	e.printStackTrace();
}

%>

<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="index.jsp" class="site_title"> <span>MilkyWay</span></a>
        </div>

        <div class="clearfix"></div>

        <!-- menu profile quick info -->
        <div class="profile clearfix">
            <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>Welcome,</span>
                <h2>${name} ${surname} </h2>
            </div>
        </div>
        <!-- /menu profile quick info -->

        <br/>

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
        
            <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                
                    <li><a><i class="fa fa-user"></i> Users <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="users.jsp">All users</a></li>
                            
                            <% if( is_admin == true) { %>
                                                     
                            	<li><a href="add-new-user.jsp">Add new</a></li>
                            	
                            	<% } %>
                        </ul>
                    </li>
                    
                    <li><a><i class="fa fa-space-shuttle"></i> Satellites <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="satellites.jsp">All satellites</a></li>
                            
                            <% if( is_admin == true) { %>
                           
                            <li><a href="add-new-satellite.jsp">Add new</a></li>
                            
                            <% } %>
                            
                        </ul>
                    </li>
                    
                    <li><a><i class="fa fa-map-marker"></i> Objects <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="objects-map.jsp">Objects in map</a></li>
                            <li><a href="objects-area.jsp">Objects in area</a></li>
                        </ul>
                    </li>

					<li><a><i class="fa fa-star"></i> Clumps <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                        
                            <li><a href="clumps.jsp">Clumps can host massive stars</a></li>
                            
                            <li><a href="sources-clump.jsp">Sources in MIPSGAL that belong to specific clump</a></li>
                            
                            <li><a href="young-sources.jsp">Young Sources in MIPSGAL that belong to specific clump</a></li>
                            
                        </ul>
                    </li>
                    
                    <% if( is_admin == true) { %>
                    
                    <li><a><i class="fa fa-upload"></i> Imports <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="mips.jsp">Mips</a></li>
                            <li><a href="higal.jsp">HiGal</a></li>
                            <li><a href="higal-info.jsp">HiGal info</a></li>
                            <li><a href="glimpse.jsp">Glimpse</a></li>
                        </ul>
                    </li>
                    
                    <% } %>
                    
                    <li><a><i class="fa fa-wrench"></i> Tools <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="tools.jsp">All tools</a></li>
                            
                            <% if( is_admin == true) { %>
                            <li><a href="add-new-tool.jsp">Add new tool</a></li>
                            <% } %>
                            
                        </ul>
                    </li>
                    
                    
                    
                </ul>
            </div>
          
        </div>
        <!-- /sidebar menu -->

        <!-- /menu footer buttons -->
        <div class="sidebar-footer hidden-small">
            <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a>
        </div>
        <!-- /menu footer buttons -->
    </div>
</div>