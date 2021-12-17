<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta name="description"
	content="A front-end template that helps you build fast, modern mobile web apps.">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Bookboxd - ${book.book_name}</title>

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="images/android-desktop.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Material Design Lite">
<link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="images/touch/ms-touch-icon-144x144-precomposed.png">
<meta name="msapplication-TileColor" content="#3372DF">

<link rel="shortcut icon" href="images/favicon.png">

<!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
<!--
    <link rel="canonical" href="http://www.example.com/">
    -->

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.cyan-light_blue.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="dashboard.css">
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
</style>
</head>
<body>
	<div
		class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
		<header
			class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
			<div class="mdl-layout__header-row">
				<span class="mdl-layout-title">Book</span>
				<div class="mdl-layout-spacer"></div>
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
					<form action="Search" method="GET">
					<label class="mdl-button mdl-js-button mdl-button--icon"
						for="searchBox"> <i class="material-icons">search</i>
					</label>
					<div class="mdl-textfield__expandable-holder">
						<input class="mdl-textfield__input" type="text" id="searchBox" name="query">
						<label class="mdl-textfield__label" for="searchBox">Enter your query...</label>
						<input type="submit" style="display: none" />
					</div>
					</form>
				</div>
			</div>
		</header>
		<div
			class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
			<header class="demo-drawer-header">
				<div class="demo-avatar-dropdown">
					<span>Welcome, ${user.username}</span>
				</div>
			</header>
			<nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
				<a class="mdl-navigation__link" href="ViewProfile?user_id=${user.user_id}">
					<i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">account_circle</i>Profile</a>
				<a class="mdl-navigation__link" href="friendsList.jsp">
					<i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Friends</a>
				<a class="mdl-navigation__link" href="FriendRequests">
					<i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">person_add</i>Friend Requests</a>
				<a class="mdl-navigation__link" href="Search?query=">
					<i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">book</i>Browse Books</a>
				<a class="mdl-navigation__link" href="Logout">
					<i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">logout</i>Logout</a>
				<div class="mdl-layout-spacer"></div>
				<a class="mdl-navigation__link"
					href="https://github.com/zklars/CSCI4830-Bookboxd"><i
					class="mdl-color-text--blue-grey-400 material-icons"
					role="presentation">info</i><span class="visuallyhidden">GitHub</span></a>
			</nav>
		</div>
		<main class="mdl-layout__content mdl-color--grey-100">
		<div class="mdl-grid demo-content">
			<div class="demo-charts mdl-cell--12-col mdl-grid mdl-navigation">
				<h3>${book.book_name}</h3>
				<h5 style="padding-left: 10px;">by <a href="Search?query=${book.author}">${book.author}</a></h5>
				<div style="padding-left: 25px;">
					<ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect"
						for="add-book-to-list">
						<c:forEach items="${userLists}" var="list">
							<a class="mdl-menu__item"
								href="Lists?action=add&list_id=${list.list_id}&user_id=${list.user_id}&book_id=${book.book_id}">${list.list_name}</a>
						</c:forEach>
					</ul>
					<a class="mdl-button mdl-js-button android-link-menu mdl-typography--text-uppercase"
						id="add-book-to-list">Add Book To List<i class="material-icons">chevron_right</i></a>
				</div>
				<br>
			</div>
			<div class="demo-graphs mdl-shadow--2dp mdl-color--white mdl-cell mdl-cell--8-col">
				<div class="mdl-card__title">
					<h2 class="mdl-card__title-text">Description:</h2>
				</div>
				<div class="mdl-card__supporting-text">
					${book.description}
				</div>
				<div class="mdl-card__title">
					<h2 class="mdl-card__title-text">Genre:</h2>
				</div>
				<div class="mdl-card__supporting-text">
					${book.genre}
				</div>
			</div>
			<div class="demo-cards mdl-cell mdl-cell--4-col mdl-cell--8-col-tablet mdl-grid mdl-grid--no-spacing">
				<div class="demo-updates mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col mdl-cell--4-col-tablet mdl-cell--12-col-desktop">
					<div class="mdl-card__media">
						<img src="${book.image_url}" style="width: 100%;">
					</div>
				</div>
				<div class="demo-separator mdl-cell--1-col"></div>
			</div>
			<div class="demo-charts mdl-cell--12-col mdl-grid mdl-navigation">
				<h3>Reviews</h3>
				<div style="padding-left: 40px;">
					<a class="mdl-button mdl-js-button mdl-js-ripple-effect" href="CreateReviewServlet?book_id=${book.book_id}">Create Review<i class="material-icons">chevron_right</i></a>
				</div>
			</div>
			<div class="mdl-cell--12-col mdl-grid mdl-navigation">
				<h4>Overall Rating:  &nbsp; &nbsp; &nbsp;<span class="fa fa-star checked"></span> &nbsp; ${averageRating}</h4>
			</div>
			<c:forEach items="${reviewList}" var="review">
				<div class="demo-graphs mdl-shadow--2dp mdl-color--white mdl-cell mdl-cell--12-col">
					<div class="mdl-card__title">
						<h2 class="mdl-card__title-text"><a href="ViewProfile?user_id=${review.user.user_id}">${review.user.username}</a>&nbsp; &nbsp; &nbsp;<span class="fa fa-star checked"></span> &nbsp; ${review.rating}</h2>
					</div>
					<div class="mdl-card__supporting-text">
						${review.comments}
					</div>
					<c:if test="${review.user_id == user.user_id}">
						<div class="mdl-card__actions">
							<ul class="mdl-menu mdl-js-menu mdl-menu--top-left mdl-js-ripple-effect" for="review${review.review_id}">
									<a class="mdl-menu__item"
										href="EditReviewServlet?review_id=${review.review_id}&book_id=${book.book_id}">Edit</a>
									<a class="mdl-menu__item"
										href="DeleteReviewServlet?review_id=${review.review_id}">Delete</a>
							</ul>
							<a class="android-link mdl-button mdl-js-button android-link-menu mdl-typography--text-uppercase"
								id="review${review.review_id}">Actions<i class="material-icons">chevron_right</i></a>
						</div>
					</c:if>
				</div>
			</c:forEach>
			<c:if test="${empty reviewList}">
				<div class="demo-graphs mdl-shadow--2dp mdl-color--white mdl-cell mdl-cell--12-col">
					<div class="mdl-card__title">
						<h2 class="mdl-card__title-text">No reviews to show.</h2>
					</div>
				</div>
			</c:if>
		</div>
		</main>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
