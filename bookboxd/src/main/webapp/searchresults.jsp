<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>Bookboxd - Search Results</title>

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
				<!-- Add spacer, to align navigation to the right -->
				<div class="mdl-layout-spacer"></div>
				<div
					class="android-search-box mdl-textfield mdl-js-textfield mdl-textfield--expandable mdl-textfield--floating-label mdl-textfield--align-right mdl-textfield--full-width">
					<label class="mdl-button mdl-js-button mdl-button--icon"
						for="search-field"><i class="material-icons">search</i></label>
					<div class="mdl-textfield__expandable-holder">
						<input class="mdl-textfield__input" type="text" id="search-field">
					</div>
				</div>
				<!-- Navigation. We hide it in small screens. -->
				<nav class="mdl-navigation mdl-layout--large-screen-only">
					<a class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">Dashboard</a> <a
						class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">Books</a> <a
						class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">People</a> <a
						class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">Friends</a> <a
						class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">Lists</a> <a
						class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">Profile</a> <a
						class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">Login</a> <a
						class="mdl-navigation__link mdl-typography--text-uppercase"
						href="">Logout</a>
				</nav>
			</div>
		</header>
		<div class="mdl-layout__drawer">
			<span class="mdl-layout-title">Bookboxd</span>

			<nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="">Dashboard</a> <a
					class="mdl-navigation__link" href="">Books</a> <a
					class="mdl-navigation__link" href="">People</a> <a
					class="mdl-navigation__link" href="">Friends</a> <a
					class="mdl-navigation__link" href="">Lists</a> <a
					class="mdl-navigation__link" href="">Profile</a> <a
					class="mdl-navigation__link" href="">Login</a> <a
					class="mdl-navigation__link" href="">Logout</a>
			</nav>
		</div>
		<div class="android-content mdl-layout__content">
			<a name="top"></a>
			<div class="android-more-section">
				<div
					class="android-section-title mdl-typography--display-1-color-contrast">Search
					results for "${query}"</div>
				<div class="android-card-container mdl-grid">
					<c:forEach items="${searchresults}" var="item">
						<!-- Card begin -->
						<div
							class="mdl-cell mdl-cell--3-col mdl-cell--4-col-tablet mdl-cell--4-col-phone mdl-card mdl-shadow--3dp">
							<div class="mdl-card__media">
								<img src="https://i.redd.it/04fx5170hjz71.jpg">
							</div>
							<div class="mdl-card__title">
								<h4 class="mdl-card__title-text">${item.book_name}</h4>
							</div>
							<div class="mdl-card__title">
								<p class="mdl-card__title-text mdl-typography--subhead">
									<span class="fa fa-star checked"></span> &nbsp; ${item.average_rating}
								</p>
							</div>
							<div class="mdl-card__supporting-text">
								<span class="mdl-typography--font-light mdl-typography--subhead">Genre: ${item.genre}</span>
							</div>
							<div class="mdl-card__supporting-text">
								<span class="mdl-typography--font-light mdl-typography--subhead">${item.description}</span>
							</div>
							<div class="mdl-card__actions">
								<ul
									class="mdl-menu mdl-js-menu mdl-menu--top-left mdl-js-ripple-effect"
									for="add-book-to-list">
									<c:forEach items="${userLists}" var="list">
										<a class="mdl-menu__item"
											href="Lists?action=add&list_id=${list.list_id}&user_id=${list.user_id}&book_id=${item.book_id}">${list.list_name}</a>
									</c:forEach>
								</ul>
								<a
									class="android-link mdl-button mdl-js-button android-link-menu mdl-typography--text-uppercase"
									id="add-book-to-list">Add Book To List<i
									class="material-icons">chevron_right</i></a>
							</div>
							<div></div>
						</div>
						<!-- Card end -->
					</c:forEach>
				</div>
			</div>

			<footer class="android-footer mdl-mega-footer">
				<div class="mdl-mega-footer--top-section">
					<div class="mdl-mega-footer--left-section"></div>
					<div class="mdl-mega-footer--right-section">
						<a class="mdl-typography--font-light" href="#top">Back to Top<i
							class="material-icons">expand_less</i></a>
					</div>
				</div>
				<div class="mdl-mega-footer--middle-section">
					<p class="mdl-typography--font-light">
						Web template based upon the <a
							href="https://github.com/google/material-design-lite/tree/mdl-1.x/templates/android-dot-com">Android.com
							Material Design Lite template</a>. Licensed under the <a
							href="LICENSE.txt">Apache 2.0 license.</a>
					</p>
				</div>

				<div class="mdl-mega-footer--bottom-section">
					<a class="mdl-typography--font-light"
						href="https://github.com/davidlentsch/CSCI4830-Bookboxd">Bookboxd
						GitHub</a> <a class="mdl-typography--font-light"
						href="https://www.unomaha.edu">University of Nebraska Omaha</a>
				</div>
			</footer>
		</div>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>

</html>