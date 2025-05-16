<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search-container">
  <form action="${pageContext.request.contextPath}/search" method="get">
    <input type="text" name="query" class="search-input" placeholder="Search movies or series..."/>
    <button type="submit" class="search-button">
      <span class="material-icons">search</span>
    </button>
  </form>
</div>
<p class="tagline">This is the Prime Reel home page. Explore movies, series, and more!</p>
<div class="movie-list">
  <!-- Loop through the movie list and display each movie card -->
  <c:forEach var="movie" items="${movies}">
    <div class="movie-card">
      <a href="${pageContext.request.contextPath}/movie-details/${movie.id}">
        <img src="${movie.posterUrl}" alt="${movie.title}" class="movie-poster"/>
      </a>
      <div class="movie-card-content">
        <h2>${movie.title}</h2>
        <p>${movie.description}</p>
        <div class="movie-actions">
          <c:set var="movieIds" value="${rentedMovieIds}"/>
          <c:set var="isRented" value="false"/>

          <c:forEach var="id" items="${movieIds}">
            <c:if test="${movie.id == id}">
              <c:set var="isRented" value="true"/>
            </c:if>
          </c:forEach>

          <c:choose>
            <c:when test="${isRented}">
              Rented
            </c:when>
            <c:otherwise>
              <button type="submit" class="action-button" onclick="rentMovie('${movie.id}', '${userId}')">
                <span class="material-icons">shopping_cart</span> Rent
              </button>
            </c:otherwise>
          </c:choose>

          <c:set var="wishListId" value="${wishList}"/>
          <c:set var="isInWishList" value="false"/>

          <c:forEach var="id" items="${wishListId}">
            <c:if test="${movie.id == id}">
              <c:set var="isInWishList" value="true"/>
            </c:if>
          </c:forEach>

          <c:choose>
            <c:when test="${isInWishList}">
              <button class="action-button" onclick="removeFromWishList('${movie.id}', '${userId}')">
                <span class="material-icons">favorite</span>
              </button>
            </c:when>
            <c:otherwise>
              <button class="action-button" onclick="addToWishlist('${movie.id}', '${userId}')">
                <span class="material-icons">favorite_border</span>
              </button>
            </c:otherwise>
          </c:choose>

          <div class="action-button">
            <span class="material-icons">star_rate</span> ${movie.rating}
          </div>

        </div>
      </div>
    </div>
  </c:forEach>
</div>
