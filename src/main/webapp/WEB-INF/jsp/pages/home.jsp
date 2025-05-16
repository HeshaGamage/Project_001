<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
          <c:set var="movieIds" value="${rentedMovieIds}"/>
          <c:set var="idToCheck" value="${movie.id}"/>
          <c:set var="isFound" value="false"/>

          <c:forEach var="id" items="${movieIds}">
            <c:if test="${idToCheck == id}">
              <c:set var="isFound" value="true"/>
            </c:if>
          </c:forEach>
          <c:choose>
            <c:when test="${isFound}">
              Rented
            </c:when>
            <c:otherwise>
              <form action="/rentals" method="post">
                <c:if test="${not empty movie}">
                  <input type="hidden" name="movieId" id="${movie.id}" value="${movie.id}"/>
                </c:if>
                <input type="hidden" name="userId" value="${sessionScope.userId}"/>
                <button type="submit" class="action-button">
                  <span class="material-icons">shopping_cart</span> Rent
                </button>
              </form>
            </c:otherwise>
          </c:choose>

          <button class="action-button" onclick="addToWishlist('${movie.id}')">
            <span class="material-icons">favorite</span> Wishlist
          </button>

          <script>
              function addToWishlist(movieId) {
                  const userId = '${sessionScope.userId}'; // This assumes the user is stored in sessionScope
                  fetch('/wishlist/add?userId=' + userId + '&movieId=' + movieId, {method: 'POST'})
                      .then(response => {
                          if (response.ok) {
                              alert('Movie added to your Wishlist!');
                          } else {
                              alert('Failed to add movie to Wishlist.');
                          }
                      });
              }
          </script>
          <button class="action-button">
            <span class="material-icons">star_rate</span> Rate
          </button>

        </div>
      </div>
    </div>
  </c:forEach>
</div>
