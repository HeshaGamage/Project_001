function addToWishlist(movieId, userId) {
    fetch('/wishlist/add?userId=' + userId + '&movieId=' + movieId, {method: 'POST'})
        .then(() => {
            location.reload();
        });
}

function removeFromWishList(movieId, userId) {
    fetch('/wishlist/remove?userId=' + userId + '&movieId=' + movieId, {method: 'POST'})
        .then(() => {
            location.reload();
        });
}

function rentMovie(movieId, userId) {
    fetch('/rentals?userId=' + userId + '&movieId=' + movieId, {method: 'POST'})
        .then(() => {
            location.reload();
        });
}

function rateMovie(movieId, userId, rating, comment) {
    // fetch('/rentals?userId=' + userId + '&movieId=' + movieId, {method: 'POST'})
    //     .then(() => {
    //         location.reload();
    //     });
}