<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Profile | Bookboxd</title>
	</head>
	<body>
		<div align="center">
			<h1><b>${user.username}'s profile</b></h1>
				<p> <b> ${user.username}'s description</b> <br> ${user.about_desc} <br><br></p>
			<h2> ${user.username}'s lists </h2>
				<p> test <br><br></p>
			<h3> ${user.username}'s friends </h3>
				<p> ${friends.toString()} </p>
		</div>
	</body>
</html>