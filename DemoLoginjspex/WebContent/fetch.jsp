<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp" /> 
<jsp:useBean id="std" class="advanced.java.program.Student" scope="session"/> 
ID 	 - <jsp:getProperty property="id" name="std"/><br>  
Name - <jsp:getProperty property="name" name="std"/><br>
Age  - <jsp:getProperty property="age" name="std"/><br>
Department - <jsp:getProperty property="dept" name="std"/><br>
</body>
</html>