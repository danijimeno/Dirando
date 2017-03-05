$(document).ready(function () {
    $.ajax({
        url: '/shoppingCartAjax'
        , dataType: 'json'
    }).done(function (items) {
        console.log('Respuesta', items);
        $.each(items, function (key, value) {
            $('#productList tbody').append('<tr><td><p>' + value['id'] + '</p></td><td><a>' + value['nombre'] + '</a></td><td><p>' + value['precio'] + '</p></td><td><input class="form-control" type="number" min="0"></td><td><p>' + value['precio'] + ' * number of items</p></td><td><a role="button"><p>Delete</p></a></td></tr>');
        })
    })
})