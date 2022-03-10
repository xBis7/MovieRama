<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>  
<html>

<head>
	<link rel="stylesheet" type="text/css" href="resources/style.css">
	<h>MovieRama</h>
	<h2>New Movie</h2>
</head>

<body>

	<form action="addMovie" method="POST" modelAttribute="movie">
	<table>
		<tr>
			<td>Title: </td>
		</tr>
		<tr>
			<td><input type="text" name="title" /> </td>
        </tr>
        <tr>
			<td>Description: </td>
		</tr>
        <tr>
			<td><textarea rows="10" cols="50" name="description"></textarea> </td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /> </td>
		</tr>
	</table> 
	</form>
</body>

</html>