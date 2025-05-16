<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="movie-details-container">
  <div class="movie-details-card">
    <div class="poster-section">
      <img src="${movie.posterUrl}" alt="${movie.title}" class="movie-details-poster"/>
    </div>
    <div class="info-section">
      <h1 class="movie-title">${movie.title}</h1>
      <p class="movie-description">${movie.description}</p>

      <div class="movie-actions">
        <!-- Wishlist Status -->
        <c:choose>
          <c:when test="${isMovieInUserWishList}">
            <button class="action-button disabled" disabled>
              <span class="material-icons">favorite</span> In Wishlist
            </button>
          </c:when>
          <c:otherwise>
              <button type="submit" class="action-button" onclick="addToWishlist('${movie.id}', '${userId}')">
                <span class="material-icons">favorite_border</span> Add to Wishlist
              </button>
          </c:otherwise>
        </c:choose>

        <!-- Rental Status -->
        <c:choose>
          <c:when test="${isMovieRented}">
            <button class="action-button disabled" disabled>
              <span class="material-icons">shopping_cart</span> Already Rented
            </button>
          </c:when>
          <c:otherwise>
            <form action="${pageContext.request.contextPath}/rentals" method="post">
              <input type="hidden" name="movieId" value="${movie.id}"/>
              <input type="hidden" name="userId" value="${sessionScope.userId}"/>
              <button type="submit" class="action-button">
                <span class="material-icons">shopping_cart</span> Rent Movie
              </button>
            </form>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</div>

