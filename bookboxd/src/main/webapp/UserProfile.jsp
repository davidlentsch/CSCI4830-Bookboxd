<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Profile | Bookboxd</title>
	</head>
	<body>
		<div align="left">
			<p><a href=dashboard.jsp>Dashboard</a>
		</div>
		<div align="center">
			<h1><b>${user.username}'s profile</b></h1>
				<p> <b> ${user.username}'s description</b> <br> ${user.about_desc} <br><br></p>
			<h3> ${user.username}'s lists </h3>
				<p> test <br><br></p>
			<h3> ${user.username}'s friends </h3>
				<p> ${friends.toString()} <br><br></p>
				<p><br><a href=Logout>Logout</a>
		</div>
	</body>
</html>