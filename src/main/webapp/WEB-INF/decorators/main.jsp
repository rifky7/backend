<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- main.jsp //-->
<html>
<head>
	<title>MAIN - <decorator:title/></title>
	<link rel="stylesheet" href="${contextName}/assets/bootstrap/css/bootstrap.min.css">
	<script src="${contextName}/assets/jquery/jquery-3.3.1.min.js"></script>
	<script src="${contextName}/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="${contextName}/assets/js/default.js"></script>
	<style type="text/css">
		.navbar {
			background-color: #008080;
			position: fixed;
			width: 100%;
			z-index: 2;
			margin-top: -60px;
			font-family: cursive;
		}
		
		.text{
			color: white;
		}
	</style>
	<decorator:head/>
</head>
<body style="margin-top: 60px">
	<nav class="navbar navbar-expand-lg navbar-light" style="color: white;">
  		<a class="navbar-brand" href="${contextName}/home.html">TigaDayacademy</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
  		</button>
  		<div class="collapse navbar-collapse" id="navbarText" >
    		<ul class="navbar-nav mr-auto">
		    	<li class="nav-item">
		    		<a class="nav-link" href="${contextName}/home.html">Home <span class="sr-only">(current)</span></a>
		      	</li>
		      	<li class="nav-item active">
		        	<a class="nav-link" href="#">Data Mahasiswa</a>
		      	</li>
		      	<!-- <li class="nav-item">
		        	<a class="nav-link" href="#">Pricing</a>
		      	</li> -->
    		</ul>
    	<sec:authorize access="isAuthenticated()">
    	<sec:authentication property="principal.username"/>&nbsp;|&nbsp;
    		<a class="navbar-text" href="${contextName}/j_spring_security_logout">Logout</a>
    	</sec:authorize>
  		</div>
	</nav>
	<decorator:body/>
</body>
</html>