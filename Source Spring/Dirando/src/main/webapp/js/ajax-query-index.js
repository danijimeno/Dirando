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
    });
    
    //Load 3 principal items 
    $.ajax({
        url: '/ListadoProductoAjax/'
        , data: {
            'page': 0
            , 'size': 3
        }
        , dataType: "json"
    }).done(function (items) {
        var cadena = "";
        $.each(items.content, function (key, value) {
            cadena += '<div class="col-sm-6 col-md-4"><div class="thumbnail"> <img class="img-responsive" src="' + value['image'] + '"><div class="btn-group  pop-imagenes" role="group"> <a href="/Producto/' + value['id'] + '" class="btn btn-secondary" role="button">View</a> <a class="btn btn-primary" role="button" id="AddCart-button'+ value['id'] +'" data-price="'+value['precio']+'" data-name="'+value['nombre']+'" data-id="'+value['id']+'">Add to cart</a> </div><div class="caption"><h3 class="desc-articulo">' + value['nombre'] + '</h3><p class="cortar">' + value['desProducto'] + '</p> <span class="precio">' + value['precio'] + '€</span><div class="progress"><div class="progress-bar progress-bar-' + value['stock'] + ' progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:' + value['stock'] + '%" data-toggle="tooltip" data-placement="bottom" title="Stock"> ' + value['stock'] + '</div></div></div></div></div></div>';
        });
        $('#productosLista').html(cadena);
    }).fail(function (error) {
        console.log(error)
    });
    
    //Add to Card
    $("#productosLista").on("click","a[id^='AddCart-button']", function(event) { 
    	$.ajax({
    		url: '/ListadoProductoAjaxCarrito',
            data: {'info': event.target.getAttribute('data-id'),
            		'name': event.target.getAttribute('data-name'),
            		'price': event.target.getAttribute('data-price')
            	  }
        }).done ( function(data){
        	$('#countItems').text(data);
        })
        
    });  
});