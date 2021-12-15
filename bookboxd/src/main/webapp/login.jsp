<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->

<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Bookboxd - Login</title>

<!-- Page styles -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.min.css">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.blue-deep_purple.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="styles.css">
<style>
#view-source {
	position: fixed;
	display: block;
	right: 0;
	bottom: 0;
	margin-right: 40px;
	margin-bottom: 40px;
	z-index: 900;
}

.checked {
	color: orange;
}
</style>
</head>

<body>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header class="mdl-layout__header">
			<div class="mdl-layout__header-row">
				<!-- Title -->
				<span class="mdl-layout-title">Bookboxd</span>
			</div>
		</header>
		<div class="mdl-layout__drawer">
			<span class="mdl-layout-title">Bookboxd</span>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="index.html">Home</a>
			</nav>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="register.jsp">Register</a>
			</nav>
		</div>
		<div class="android-content mdl-layout__content">
			<a name="top"></a>
			<div class="android-more-section mdl-typography--text-center">
				<div
					class="android-section-title mdl-typography--display-1-color-contrast">Bookboxd
					Login</div>
				<!-- Simple Textfield -->
				<form action="Login" method="post">
					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="text" name="username">
						<label class="mdl-textfield__label" for="username">Username</label>
					</div>
					<br>
					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="password"
							name="password"> <label class="mdl-textfield__label"
							for="password">Password</label>
					</div>
					<br>
					<br>
					<p>${errorMessage}</p>
					<br>
					<button
						class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"
						type="submit">Login</button>
				</form>
			</div>
		</div>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>

</html>