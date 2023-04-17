<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <% pageContext.forward("./WEB-INF/template/address_book/addrbook_control.jsp?action=list");%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>