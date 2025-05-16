<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<div class="wishlist-container">--%>
<%--  <c:choose>--%>
<%--    <c:when test="${empty movies}">--%>
<%--      <p>You have no movies in your wishlist.</p>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--      <div class="movie-list">--%>
<%--        <c:forEach var="movie" items="${movies}">--%>
<%--          <div class="movie-card">--%>
<%--            <a href="movie/${movie.id}">--%>
<%--              <img src="${movie.posterUrl}" alt="${movie.title}" class="movie-poster"/>--%>
<%--            </a>--%>
<%--            <div class="movie-card-content">--%>
<%--              <h2>${movie.title}</h2>--%>
<%--              <p>${movie.description}</p>--%>
<%--              <form action="/wishlist/remove" method="post">--%>
<%--                <input type="hidden" name="movieId" value="${movie.id}"/>--%>
<%--                <input type="hidden" name="userId" value="${sessionScope.userId}"/>--%>
<%--                <button type="submit" class="action-button">--%>
<%--                  <span class="material-icons">delete</span> Remove--%>
<%--                </button>--%>
<%--              </form>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </c:forEach>--%>
<%--      </div>--%>
<%--    </c:otherwise>--%>
<%--  </c:choose>--%>
<%--</div>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wishlist-container">
  <p class="tagline">Saved movies and Shows you love</p>
  <c:choose>
    <c:when test="${empty movies}">
      <p class="empty-message">You have no movies in your wishlist.</p>
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
              <div class="movie-actions">
                <form action="/wishlist/remove" method="post">
                  <input type="hidden" name="movieId" value="${movie.id}"/>
                  <input type="hidden" name="userId" value="${sessionScope.userId}"/>
                  <button type="submit" class="action-button" title="Remove from Wishlist">
                    <span class="material-icons">delete</span>
                  </button>
                </form>
                <div class="action-button" title="Rating">
                  <span class="material-icons">star_rate</span> ${movie.rating}
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
</div>

