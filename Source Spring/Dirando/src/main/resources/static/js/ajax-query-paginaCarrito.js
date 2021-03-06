$(document).ready(function () {
	
	//Any Ajax query will execute this...
    $body = $("body");
    $(document).on({
        ajaxStart: function () {
            $body.addClass("loading");
        }
        , ajaxStop: function () {
            $body.removeClass("loading");
        }
    })
    
	$.ajax({
        url: '/shoppingCartAjax'
        , dataType: 'json'
    }).done(function (items) {
        $.each(items, function (key, value) {
            $('#productList tbody').append('<tr><td><p>' + value['id'] + '</p></td><td>' + value['nombre'] + '</td><td><p>' + value['precio'] + '</p></td><td><a href="/Producto/'+value['id']+'" role="button"><p>View</p></a></td></tr>');
        })
    })
    
	//When I pulse "buy" I check if I am logged
	$('#buyButton').click(function(){
		$.ajax({
    		url: '/buy2'
        }).done ( function(data){
        	if (data==1){
        		alert("¡You need to be Logged In!");
        	}else if (data==2){
        		alert("¡No items in the cart!");
        	}
        });
	});
    
	$('#deleteButton').click(function(){
		$.ajax({
    		url: '/deleteCart'
        }).done ( function(data){
        });
	});

})