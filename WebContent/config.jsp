<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configuration of Casual</title>
</head>
<body>
<%
Class searchType = (Class) request.getAttribute("search_type");
ArrayList<Class> searchOptions = (ArrayList) request.getAttribute("search_options");
boolean isCrawlerRunning = (Boolean) request.getAttribute("is_crawler_running");
%>
<form action="config" method="POST">
	Search type: <select name="search_type">
	<% if (searchOptions != null) {
			for (Class searchOption : searchOptions) {%>
			<option label="<%=searchOption.getSimpleName()%>" value="<%=searchOption.getName()%>" 
				<% if (searchType.equals(searchOption)) { %>
				selected="selected"
				<% } %> />
			<% }
		}%></select><br />
		Nível de Dispersão da Popularidade: <input name="popularity_dispersion" type="text" value="<%=request.getAttribute("popularity_dispersion") %>">
		<br />
	<button type="submit">Save changes</button>
	<br />
	<br />
	<% if (isCrawlerRunning) { %>
		<button name="is_crawler_running" type="submit" value="stop">Stop crawler</button>
	<% } else { %>
		<button name="is_crawler_running" type="submit" value="start">Start crawler</button>
	<% } %>
</form>
</body>
</html>