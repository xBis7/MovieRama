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
		<p>Welcome back <a href="userMovies/${currentUserId}" >${currentUser}</a></p>
		<div id="userActions"><button id="logoutButton" type="logoutButton"><a href="logout" >Logout</a></button></div>
	</head>
	<body>
		<div id="sort">Sort by: <a href="sortByLikes">Likes</a> | <a href="sortByHates">Hates</a> | <a href="">Date</a></div>
		<p>
		<button id="newMovieButton" type="newMovieButton"><a href="newMovie"> New Movie </a></button>
		</p>
		<br/>
		<c:if test="${!empty dateSortedMovieList}">
		<c:forEach items="${dateSortedMovieList}" var="movie">
		
			<div id="movie">
				<h1>${movie.title}</h1>
				<p>Posted by 
				<c:if test = "${currentUser != movie.user.username}" >
					<a href="userMovies/${movie.user.userId}">${movie.user.username}</a>
				</c:if>
				<c:if test = "${currentUser == movie.user.username}" >
					<a href="userMovies/${movie.user.userId}">You</a>
				</c:if>, ${movie.date}</p>
				<p> ${movie.description} </p>
				<c:choose>
					<c:when test = "${currentUser == movie.user.username}">
						<p>${movie.likes} likes | ${movie.hates} hates</p>
					</c:when>
					<c:otherwise>	
							<c:set var="currentUserVoted" value="false" />
							<c:set var="positiveVote" value="false" />
							<c:if test="${!empty listOfVotes}">
								<c:forEach items="${listOfVotes}" var="vote">
									<c:if test="${currentUser == vote.user.username && movie.movieId == vote.movie.movieId}">
										<c:set var="currentUserVoted" value="true" />
										<c:if test="${vote.positive == true}">
											<c:set var="positiveVote" value="true" />
										</c:if>
									</c:if>	
								</c:forEach>
							</c:if>
									<c:choose>
									<c:when test="${currentUserVoted == true}">
									<c:if test="${!empty listOfVotes}">
									<c:forEach items="${listOfVotes}" var="vote">
										<c:if test="${currentUser == vote.user.username && movie.movieId == vote.movie.movieId}">
										<form action="changeVote/${vote.voteId}/${movie.movieId}" method="POST" modelAttribute="vote" >	
										<c:choose>
										<c:when test="${positiveVote == true}">
											<p>
											<input type="submit" name="voteType" value="${movie.likes} likes" disabled/>
											<input type="submit" name="voteType" value="${movie.hates} hates" />
											</p>
										</c:when>
										<c:otherwise>
											<p>
											<input type="submit" name="voteType" value="${movie.likes} likes" />
											<input type="submit" name="voteType" value="${movie.hates} hates" disabled/>
											</p>
										</c:otherwise>
										</c:choose>
									</form>
									</c:if>
									</c:forEach>
									</c:if>
									</c:when>
									<c:otherwise>
									<form action="vote/${movie.movieId}" method="POST" modelAttribute="vote" >
										<p>
										<input type="submit" name="voteType" value="${movie.likes} likes" />
										<input type="submit" name="voteType" value="${movie.hates} hates" />
										</p>
									</form>
									</c:otherwise>
									</c:choose>
								
						<c:if test="${!empty listOfVotes}">
							<c:forEach items="${listOfVotes}" var="vote">
								<c:if test="${currentUser == vote.user.username && movie.movieId == vote.movie.movieId}">
									<c:choose>
										<c:when test="${vote.positive == true}">
											<p>
												You like this movie | <a href="<c:url value='deleteVote/${vote.voteId}'/>">Unlike</a>
											</p>
										</c:when>
										<c:otherwise>
											<p>
												You hate this movie | <a href="<c:url value='deleteVote/${vote.voteId}'/>">Unhate</a>
											</p>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</c:if>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>	
		</c:if>
		
	</body>
	
</html>



