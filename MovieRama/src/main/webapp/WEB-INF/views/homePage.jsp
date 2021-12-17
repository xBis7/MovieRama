<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="resources/style.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<h>MovieRama</h>
	<div id="userActions"><a href="login">Login</a> or <button id="signUpButton" type="signUpButton"><a href="signUp">Sign Up</a></button></div>
</head>
<body>
<div id="sort">Sort by: <a href="sortByLikesAnonym">Likes</a> | <a href="sortByHatesAnonym">Hates</a> | <a href="sortByDateAnonym">Date</a></div>
<br/>
<p>
<c:if test="${!empty listOfMovies}">
<c:forEach items="${listOfMovies}" var="movie">

	<div id="movie">
		<h1>${movie.title}</h1>
		<p>Posted by <a href="userMovies/${movie.user.userId}">${movie.user.username}</a>, ${movie.date}</p>
		<p> ${movie.description} </p>
		<p> ${movie.likes} likes | ${movie.hates} hates</p>
	</div>
		
</c:forEach>
</c:if>
</p>

</body>
</html>

