<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>

<!-- main.jsp //-->
<html>
<head>
	<title>Login - <decorator:title/></title>
	<link rel="stylesheet" href="${contextName}/assets/bootstrap/css/bootstrap.min.css">
	<script src="${contextName}/assets/jquery/jquery-3.3.1.min.js"></script>
	<script src="${contextName}/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="${contextName}/assets/js/default.js"></script>
	<style type="text/css">
		.navbar {
			background-color: #008080;
			color: white;
			position: fixed;
			width: 100%;
			z-index: 2;
			margin-top: -60px;
			font-family: cursive;
		}
		body {
			margin-top: 60px;
			background-image: url("https://png.pngtree.com/thumb_back/fw800/back_pic/04/48/50/00585a3568a0a7d.jpg");
		    background-size: cover;
		    background-attachment: fixed;
		    background-repeat: no-repeat;
		    
		}
	</style>
	<decorator:head/>
</head>
<body>
	<decorator:body/>
</body>
</html>