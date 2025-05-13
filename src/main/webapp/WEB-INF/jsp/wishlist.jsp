<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Your Wishlist</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wishlist.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<header>
  <h1>Your Wishlist</h1>
  <form action="${pageContext.request.contextPath}/auth/logout" method="post" style="margin: 0;">
    <div style="display: flex; align-items: center;">
      <p>Logged in as: <b>${sessionScope.username}</b></p>
    </div>
    <button type="submit" class="btn">Logout</button>
  </form>
</header>

<main>
  <div class="wishlist-container">
    <c:choose>
      <c:when test="${empty movies}">
        <p>You have no movies in your wishlist.</p>
      </c:when>
      <c:otherwise>
        <div class="movie-list">
          <c:forEach var="movie" items="${movies}">
            <div class="movie-card">
              <a href="movie/${movie.id}">
                <img src="${movie.posterUrl}" alt="${movie.title}" class="movie-poster"/>
              </a>
              <div class="movie-card-content">
                <h2>${movie.title}</h2>
                <p>${movie.description}</p>
                <form action="/wishlist/remove" method="post">
                  <input type="hidden" name="movieId" value="${movie.id}" />
                  <input type="hidden" name="userId" value="${sessionScope.userId}" />
                  <button type="submit" class="action-button">
                    <span class="material-icons">delete</span> Remove
                  </button>
                </form>
              </div>
            </div>
          </c:forEach>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</main>
</body>
</html>
