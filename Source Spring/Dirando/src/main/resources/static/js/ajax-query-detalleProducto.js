$(document).ready(function () {
    $("#ratingContent").on("click", "button", function (event) {
        $("#ratingEvent").val(event.target.getAttribute('data-value'));
    });
    /*Add to cart button*/
    $('#addCartButton').click(function () {
        $.ajax({
            url: '/ListadoProductoAjaxCarrito', 
            data: {'info': event.target.getAttribute('data-id'),
        		   'name': event.target.getAttribute('data-name'),
        		   'price': event.target.getAttribute('data-price')
        	       }
        }).done(function (data) {
            $('#countItems').text(data);
        })
    });
    /*Load comments*/
    $.ajax({
        url: '/loadComments'
        , data: {
            'idProduct': $('#addCartButton').attr('data-id')
        }
        , dataType: "json"
    }).done(function (items) {
        var cadena = '';
        $.each(items, function (key, value) {
            cadena += '<li><div class="comment-main-level"><div class="comment-avatar"> <img src="img/logoNEW.jpg" alt=""> </div><div class="comment-box"><div class="comment-head"> <span class="glyphicon glyphicon-heart"></span><h6 class="comment-name">' + value['user'] + '</h6> </div><div class="comment-content">' + value['content'] + '</div></div></div></li>';
        });
        $('#comments-list').html(cadena);
    }).fail(function (error) {
        console.log(error)
    });
})