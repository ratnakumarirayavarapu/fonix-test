<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/login" method="POST">
        source : <input name="source" type="text" /> <br/>
        destination : <input name="destination" type="text" /> <br/>
        mail-id : <input name="mail" type="text" /> <br/>
        <select name = "subscription">
	  		<option value="daily">daily</option>
	  		<option value="weekly">weekly</option>
	  		<option value="monthly">monthly</option>
	  		<option value="Uncapped">Uncapped</option>
		</select>
        <input type="submit" />
    </form>
</body>
</html>