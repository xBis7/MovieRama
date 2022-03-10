<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>  
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="resources/style.css">
		<h>MovieRama</h>
		<h2>List of movies added by <a href="">${username}</a></h2>
	</head>
	<body>
		<c:if test="${!empty userMovieList}">
		<c:forEach items="${userMovieList}" var="movie">
		
			<div id="movie">
				<h1>${movie.title}</h1>
				<p>Posted by <a href="" >${movie.user.username}</a>, ${movie.date}</p>
				<p> ${movie.description} </p>
				<p> ${movie.likes} likes | ${movie.hates} hates</p>
			</div>
				
		</c:forEach>
		</c:if>	
	</body>

</html>