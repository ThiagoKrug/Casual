<%@page import="model.Relationship"%>
<%@page import="model.RelationshipLink"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Casual</title>
<style type="text/css">
h2 {
	margin-bottom: 0px;
	padding-bottom: 0px
}

p {
	margin-top: 0px;
	padding-top: 0px;
}

div {
	border: 1px solid #dddddd;
	padding: 10px 40px;
	margin: 30px 20px;
	max-width: 2000px;
	min-width: 100px;
}

div#search {
	text-align: center;
}

div#pagination {
	text-align: center;
}
</style>
</head>
<body>
	<div id="search">
		<form action="search" method="POST">
			Search <input name="search" />
			<button type="submit">Search casualities!</button>
		</form>
	</div>
	<div id="content">
	<%  String search = request.getParameter("search");
		String didYouMean = (String) request.getAttribute("didyoumean");
		boolean notFinded = (Boolean) request.getAttribute("notfound");
		if (didYouMean != null) { %>
			<h3>Você quis dizer: <a href="search?search=<%=didYouMean%>"><%=didYouMean%></a></h3>
		<% } else if (didYouMean == null && notFinded) { %>
			<p id=notFound >Sua pesquisa - <%=search%> - não encontrou nenhum documento correspondente.</p>
			<p id=notFoundText >Sugestões:</p>
			<ul>
				<li>Certifique-se de que todas as palavras estejam escritas corretamente.</li>
				<li>Tente palavras-chave diferentes.</li>
				<li>Tente palavras-chave mais genéricas.</li>
			</ul>
		<% }
		
		ArrayList<Relationship> relationships = (ArrayList) request.getAttribute("relationships");
		if (relationships != null) {
			for (Relationship relationship : relationships) { %>
				<h2><%=relationship.getPerson1().getName()%> >> <%=relationship.getPerson2().getName()%> - Score : <%=relationship.getScore()%> - Categoria: <%=relationship.getRelationshipLinks().get(0).getName() %></h2>
					<p><a href="<%=relationship.getRelationshipLinks().get(0).getLink()%>"><%=relationship.getRelationshipLinks().get(0).getLink()%></a></p>
		<%  }
		} %>
	</div>
	<div id=pagination>
		<% int pag = (Integer)request.getAttribute("pag");
		int total = (Integer)request.getAttribute("total");
		
		if (pag > 1) { %>
			<a href="search?search=<%=search%>&pag=<%=1%>">Primeira</a>
			<a href="search?search=<%=search%>&pag=<%=pag - 1%>">Anterior</a>
		<% }
		
		for (int i = 1; i <= total; i++) {
			if (i == pag) { %>
				<%=pag%>
			<% } else { %>
				<a href="search?search=<%=search%>&pag=<%=i%>"><%=i%></a>
			<% }
		}
		
		if (pag < total) { %>
			<a href="search?search=<%=search%>&pag=<%=pag + 1%>">Próxima</a>
			<a href="search?search=<%=search%>&pag=<%=total%>">Última</a>
		<% } %>
	</div>
</body>
</html>