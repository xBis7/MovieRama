<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>  
<html>

<head>
	<link rel="stylesheet" type="text/css" href="resources/style.css">
	<h>MovieRama</h>
	<h2>Sign up form</h2>
</head>

<body>

	<form action="addUser" method="POST" modelAttribute="user" >	
	<table>
		<tr>
			<td>Username:</td>
		</tr>
		<tr>
			<td><input type="text" name="username" /> </td>
        </tr>
        <tr>
			<td>Email:</td>
		</tr>
        <tr>
			<td><input type="text" name="email" /> </td>
		</tr>
		<tr>
			<td>Password:</td>
		</tr>
		<tr>
			<td><input type="password" name="password" /> </td>
		</tr>
		<tr>
			<td><input type="submit" value="SignUp" /> </td>
		</tr>
	</table> 
	</form>
</body>

</html>