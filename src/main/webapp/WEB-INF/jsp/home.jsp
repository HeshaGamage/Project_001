<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome - Prime Reel</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" type="text/css" href="css/movie.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<header>
    <h1>Welcome to Prime Reel!</h1>
    <form action="${pageContext.request.contextPath}/auth/logout" method="post" style="margin: 0;">

        <button type="submit" class="btn">Logout</button>
    </form>
</header>
<main>
    <p class="tagline">This is the Prime Reel home page. Explore movies, series, and more!</p>

    <div class="movie-list">
        <!-- Loop through the movie list and display each movie card -->
        <c:forEach var="movie" items="${movies}">
            <div class="movie-card">
                <a href="movie/${movie.id}">
                    <img src="${movie.posterUrl}" alt="${movie.title}" class="movie-poster"/>
                </a>
                <div class="movie-card-content">
                    <h2>${movie.title}</h2>
                    <p>${movie.description}</p>
                    <div class="movie-actions">
                        <c:set var="movieIds" value="${rentedMovieIds}" />
                        <c:set var="idToCheck" value="${movie.id}" />
                        <c:set var="isFound" value="false" />

                        <c:forEach var="id" items="${movieIds}">
                            <c:if test="${idToCheck == id}">
                                <c:set var="isFound" value="true" />
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${isFound}">
                                Rented
                            </c:when>
                            <c:otherwise>
                                <form action="/rentals" method="post">
                                    <c:if test="${not empty movie}">
                                        <input type="hidden" name="movieId" id="${movie.id}" value="${movie.id}" />
                                    </c:if>
                                    <input type="hidden" name="userId" value="${sessionScope.userId}" />
                                    <button type="submit" class="action-button">
                                        <span class="material-icons">shopping_cart</span> Rent
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>



                        <button class="action-button">
                            <span class="material-icons">favorite_border</span> Wishlist
                        </button>
                        <button class="action-button">
                            <span class="material-icons">star_rate</span> Rate
                        </button>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>