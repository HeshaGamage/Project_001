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
            <button class="action-button" onclick="rentMovie('${movie.id}', '${userId}')">
              <span class="material-icons">shopping_cart</span> Rent Movie
            </button>
          </c:otherwise>
        </c:choose>
      </div>

      <!-- Star Rating Input -->
      <div class="rating-comment-section">
        <h3>Rate this Movie</h3>
        <div class="rating-form">
          <div class="stars">
            <c:forEach var="i" begin="1" end="10">
              <input type="radio" id="star${i}" name="rating" value="${i}"/>
              <label for="star${i}" title="${i} stars">&#9733;</label>
            </c:forEach>
          </div>
          <textarea name="comment" placeholder="Leave a comment..." required></textarea>
          <button type="submit" class="btn primary"
                  onclick="rateMovie('${movie.id}', '${userId}', '${rating}', '${comment}')">Submit
          </button>
        </div>
      </div>
    </div>

  </div>
</div>
<h3 class="reviews-heading">User Reviews</h3>
<div class="comment-list">
  <c:forEach var="review" items="${reviews}">
    <div class="comment-item">
      <div class="review-header">
        <div class="user-initial">${review.username.substring(0,1)}</div>
        <div class="review-meta">
          <p class="review-username">${review.username}</p>
          <p class="review-rating">
            <span class="material-icons"
                  style="font-size: 16px; vertical-align: middle; color: #f39c12;">star</span> ${review.rating}/10
          </p>
        </div>
        <div style="margin-left: 10px">
          <p class="comment-text">${review.comment}</p>
        </div>
      </div>

    </div>
  </c:forEach>
</div>




