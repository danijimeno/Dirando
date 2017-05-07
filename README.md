# Dirando
*Aplicación web de compra de productos de ciclismo*

## Descripción de la temática de la web
*La aplicación dispondrá de una parte pública y otra pivada. La pública estará destinada a los clientes externos, con posibilidad de interactuar con los productos, ver sus descripciones, precios o su disponibilidad. Si el cliente está registrado, las funcionalidades aumentan (p.ej historial de compra). No obstante, si no está registrado, podrá añadir productos a su carro/pedido (aunque para finalizar su compra deba estar registrado). Además, se podrá simular el proceso de pago.
Por otro lado, la parte privada de la aplicación poseerá el control sobre la lista de productos alojados, sus categorías, descripciones y demás. Dichas funcionalidades de gestión estarán disponibles para el/los administrador/es mediante usuarios específicos.*

## Entidades
 1. Producto: Cada producto tendrá un nombre e identificador, además de una fotografía, descripción, precio y demás características.
 2. Pedido: El usuario tendrá pedidos acumulados, registrando los productos de dichos pedidos.
 3. Usuarios: Perfil de cada usuario y funciones diferenciadas entre Usuario no logueado, Usuario logueado y Administrador.
 4. Categoría Productos: Además, se podrán identificar los productos mediante una categoría asignada.
 5. Valoración y comentarios: Pequeña descripción más una valoración del producto.
 6. Publicidad: Gestión de productos populares o, si estás registrado, productos favoritos para mostrarlos en espacios especificos de publicidad.
 
## Integrantes
- Ignacio de Lucas Noguero  :arrow_right:  i.delucasn@alumnos.urjc.es   :arrow_right: https://github.com/Igna91

- Roberto Pérez Llanos    :arrow_right:    r.perezll@alumnos.urjc.es    :arrow_right: https://github.com/rperezll

- Alejandro Pinto Fernández :arrow_right:  a.pinto@alumnos.urjc.es    :arrow_right:   https://github.com/AlejandroPinto

- Daniel Jimeno Sáez       :arrow_right:   d.jimeno@alumnos.urjc.es   :arrow_right:   https://github.com/danijimeno

## Diagrama navegación fase 2: ##
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/DiagramaNavegacion.PNG)

## Diagrama navegación fase 3: ##
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/diagramaNav3.png)

## Diagrama Entidades fase 3: ##
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/diagramaEntidades.jpg)

## Diagrama Clases y templates V1 fase 3: ##
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/diagramaClasesFase3.jpg)

## Diagrama Clases y templates (V2 ACTUALIZADO): ##
WebControllers + Services + Repositories [FRONTEND]
####
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/WebController+Services+Repositories.jpg)
####
Templates + WebControllers [FRONTEND]
####
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/WebControllers+Templates.jpg)

## Diagrama API REST [FRONTEND]: ##
WebRestControllers + Services + Repositories [FRONTEND]
####
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/RestController+Services+Repositories.jpg)
####
AdminRestControllers + Services + Repositories [FRONTEND]
####
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/DiagramAdminRestController-Services-Repository.jpg)

## Diagrama ANGULAR 2: ##
Components & Services
####
![logo](https://github.com/danijimeno/Dirando/blob/master/Diagramas/DiagramaAngular.png)

## Template bootstrap ##
Hemos usado una plantilla Bootstrap para la zona de administración. Sin embargo, hemos cambiado los cuerpos de las páginas y modificado el menú.
https://startbootstrap.com/template-overviews/sb-admin-2/

# Dirando Wiki
*Small description of the methods used*

## WebController Guide
### WebRestControllerCategory
 - Return the products of a category
   - Method: GET
   - URL: https://localhost:8443/rest/category
   - Result: 
     ```sh
       { "id": 1, "nombre": "Specialized Epic XCO 2017", "desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight", "precio": 3500, "valoracion": 0, "theBest": 5, "mustImprove": 3, "image": "img/ejemplo1.jpg",      "stock": 30, "comments": [{ "id": 1, "user": "user", "content": "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "rating": "XX"}],      "categoria": "xco", "referencia": 0,"bad": 1}|
     ```
    - Status code: 200 (OK)

### WebRestControllerLog
 - Check if the user is logged in
   - Method: GET
   - URL: https://localhost:8443/rest/log
   - Result: 
      User logged in
     ```sh
       Logged in!
     ```
    - Status code: 200 (OK)
       User not logged in
      ```sh
       Not Logged !
      ```
    - Status code: 423 Locked (WebDAV)

### WebRestControllerOrder 
 - Pay an order
   - Method: PUT
   - URL: https://localhost:8443/rest/pay
   - Result: 
     Not empty car and logged in
     ```sh
       Finished payment
     ```
    - Status code: 200 (OK)
       Not empty car but logged out
      ```sh
        Logged required!
      ```
    - Status code: 401 Not Anauthorized
       Empty car 
      ```sh
       The cart is empty!
      ```
    - Status code: 206 Parcial Content

 - Return carriage size
   - Method: GET
   - URL: https://localhost:8443/rest/cartSize
   - Result: 
     ```sh
       1
     ```
    - Status code: 200 (OK)

 - Return carriage
   - Method: GET
   - URL: https://localhost:8443/rest/cartSize
   - Result: 
      Not empty car
     ```sh
       {"id": 2,"nombre": "Specialized Epic XCO 2017","desProducto": null,"precio": 2000,"valoracion": 0,"theBest": 0, "mustImprove": 0,"image": null,"stock": 0,"comments": null,"categoria": null,"bad": 0,"referencia": 0}
     ```
    - Status code: 200 (OK)
       Empty car
      ```sh
       
      ```
    - Status code: 204 No Content

 - Delete cart
   - Method: DELETE
   - URL: https://localhost:8443/rest/cart
   - Result: 
     ```sh
       The Cart is empty!
     ```
    - Status code: 200 (OK)

 - Add to cart
   - Method: PUT
   - URL: https://localhost:8443/rest/cart
   - Body:
     ```sh
       {"id": 2,"nombre":"Specialized Epic XCO 2017","precio":2000}
     ```
   - Result: 
     ```sh
       Product added to cart
     ```
    - Status code: 200 (OK)
### WebRestControllerProduct
 - Show index
   - Method: GET
   - URL: https://localhost:8443/rest/indexItems
   - Result: 
     ```sh
       {"content": [{"id": 1,"nombre": "Specialized Epic XCO 2017","desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New metry, crazy low frame weight","precio": 3500,"valoracion": 0,"theBest": 5,"mustImprove": 3,"image": "img/ejemplo1.jpg",  "stock": 30, "comments": [ {"id": 1,"user": "user","content": "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?","rating": "XX" }]}
     ```
    - Status code: 200 (OK)

 - Show carrusel
   - Method: GET
   - URL: https://localhost:8443/rest/carrusel
   - Result: 
     ```sh
       [{"id": 1,"nomPublicidad": "Chania","imagen": "img/carousel1.jpg"},{"id": 2,"nomPublicidad": "Chania","imagen": "img/carousel2.jpg"},{"id": 3,"nomPublicidad": "Flower","imagen": "img/carousel3.jpg"}]
     ```
    - Status code: 200 (OK)

 - Item by search
   - Method: GET
   - URL: https://localhost:8443/rest/items/{Id}
   - Result: 
     ```sh
       {"content": [{"id": 2,"nombre": "Specialized XL 2017 2028","desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight","precio": 4600,"valoracion": 0,"theBest": 5, "mustImprove": 3,"image": img/ejemplo2.jpg","stock": 50,"comments": [],"categoria": "xco","bad": 1,"referencia": 0}]}
     ```
    - Status code: 200 (OK)

- Show product details
   - Method: GET
   - URL: https://localhost:8443/rest/productDetail/{Id}
   - Result: 
     ```sh
       {"id": 1,"nombre": "Specialized Epic XCO 2017","desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low rame weight","precio": 3500,"valoracion": 0,"theBest": 5,"mustImprove": 3,"image": "img/ejemplo1.jpg","stock": 30, "comments": [{"id": 1,"user": "user", "content": "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?","rating": "XX"}],"categoria": "xco",  "bad": 1,"referencia": 0}
     ```
   - Status code: 200 (OK)

- Show product rating
   - Method: GET
   - URL: https://localhost:8443/rest/productRating/{Id}
   - Result: 
     ```sh
       [55.555557,33.333336,11.111112]
     ```
    - Status code: 200 (OK)

### WebRestControllerUser
 - Modify account
   - Method: PUT
   - URL: https://localhost:8443/rest/account
   - Body:
     ```sh
       {"fullName":"nuevo nombre comleto","email":"nuevo correo","phone":"98789797","password":"1234","address": "Nueva Calle los rosales 10"}
     ```
   - Result: 
     ```sh
       |
     ```
    - Status code: 200 (OK)

 - Show logged in user
   - Method: GET
   - URL: https://localhost:8443/rest/user
   - Result: 
     ```sh
       {"id": 3,"name": "admin","email": "adminser@dirando.com","password": $2a$10$9pJOe8TTv98WFKwn0Ps4VurM3v20Wz.QN3aAMVKsrFsnmDoqEyz0y","role": ["ROLE_USER","ROLE_ADMIN"],"address": "Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068","phone": 695698365,"comments": [],"pedidos": [],"fullName": "Con honor"}
     ```
    - Status code: 200 (OK)

 - Add new user
   - Method: POST
   - URL: https://localhost:8443/rest/user
   - Body:
     ```sh
       {"name": "name","fullName":"NombreCompleto","email":"correo","phone":"98789797","password":"1234","address": "Calle los rosales 10"}
     ```
   - Result: 
     ```sh
       |
     ```
    - Status code: 200 (OK)

## AdminController Guide
### AdminRestController
 - Return a product
   - Method: GET
   - URL: https://localhost:8443/rest/admin/products/{id}
   - Result: 
     ```sh
       { "id": 1, "nombre": "Specialized Epic XCO 2017", "desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight", "precio": 3500, "valoracion": 0, "theBest": 5, "mustImprove": 3, "image": "img/ejemplo1.jpg",      "stock": 30, "comments": [{ "id": 1, "user": "user", "content": "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "rating": "XX"}],      "categoria": "xco", "referencia": 0,"bad": 1}
     ```
    - Status code: 200 (OK)

 - New product
   - Method: POST
   - URL: https://localhost:8443/rest/admin/products
   - Body:
     ```sh
       {"nombre": "Nuevo nombre","desProducto": "If you ask us, it had beent","precio": 50,"theBest": 1,     "mustImprove": 1, "bad": 1, "image": "img/ejemplo2.jpg","stock": 50,"categoria": "nueva"}
     ```
   - Result: 
     ```sh
       {"id": 57,"nombre": "Nuevo nombre","desProducto": "If you ask us, it had beent","precio": 50,"valoracion": 0, "theBest": 1,"mustImprove": 1,"image": "img/ejemplo2.jpg","stock": 50,"comments": null,"categoria": "nueva",  "referencia": 0}
     ```
    - Status code: 201 Created

 - Upgrade a product
   - Method: PUT
   - URL: https://localhost:8443/rest/admin/products/{id}
   - Body:
     ```sh
       {"nombre": "Nuevo nombre","desProducto": "If you ask us, it had beent","precio": 50,"theBest": 1,     "mustImprove": 1, "bad": 1, "image": "img/ejemplo2.jpg","stock": 50,"categoria": "nueva"}
     ```
   - Result: 
     ```sh
       {"id": 2,"nombre": "Nuevo nombre","desProducto": "If you ask us, it had beent","precio": 50,"valoracion": 0,"theBest": 1,"mustImprove": 1,"image": "img/ejemplo2.jpg","stock": 50,"comments": null,"categoria": "nueva","bad": 0,  "referencia": 0}
     ```
    - Status code: 200 (OK)

 - Delete a product
   - Method: DELETE
   - URL: https://localhost:8443/rest/admin/products/{id}
   - Result: 
     ```sh
       {"id": 2,"nombre": "Nuevo nombre","desProducto": "If you ask us, it had beent","precio": 50,"valoracion": 0,"theBest": 1,"mustImprove": 1,"image": "img/ejemplo2.jpg","stock": 50,"comments": null,"categoria": "nueva","bad": 0,  "referencia": 0}
     ```
    - Status code: 200 (OK)

 - Return all products
   - Method: GET
   - URL: https://localhost:8443/rest/admin/products
   - Result: 
     ```sh
       { "id": 1, "nombre": "Specialized Epic XCO 2017", "desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight", "precio": 3500, "valoracion": 0, "theBest": 5, "mustImprove": 3, "image": "img/ejemplo1.jpg",      "stock": 30, "comments": [{ "id": 1, "user": "user", "content": "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "rating": "XX"}],      "categoria": "xco", "referencia": 0,"bad": 1}
     ```
    - Status code: 200 (OK)

 - Returns all products of all categories
   - Method: GET
   - URL: https://localhost:8443/rest/admin/categories
   - Result: 
     ```sh
       { "id": 1, "nombre": "Specialized Epic XCO 2017", "desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight", "precio": 3500, "valoracion": 0, "theBest": 5, "mustImprove": 3, "image": "img/ejemplo1.jpg",      "stock": 30, "comments": [{ "id": 1, "user": "user", "content": "0_1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi et iure laudantium vitae, praesentium optio, sapiente distinctio illo?", "rating": "XX"}],      "categoria": "xco", "referencia": 0,"bad": 1}...
     ```
    - Status code: 200 (OK)

 - Add new categorie
   - Method: POST
   - URL: https://localhost:8443/rest/admin/categories
   - Body:
     ```sh
       {"nombre": "nueva"}
     ```
   - Result: 
     ```sh
       {"id": 6,"publicidad": null,"productos": null,"nombre": "nueva"}
     ```
    - Status code: 201 Created

 - Returns all products in a category
   - Method: GET
   - URL: https://localhost:8443/rest/admin/categories/{id}
   - Result: 
     ```sh
       {"id": 4,"publicidad": [],"productos": [{"id": 25,"nombre": "Specialized TopFuel 2014","desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight","precio": 3500,"valoracion": 0,"theBest": 5,"mustImprove": 3,   "image": "img/ejemplo1.jpg","stock": 30,"comments": [],"categoria": "cyclocross","bad": 1,"referencia": 0},
     ```
    - Status code: 200 (OK)

 - Upgrade a category
   - Method: PUT
   - URL: https://localhost:8443/rest/admin/categories/{id}
   - Body:
     ```sh
       {"nombre": "nueva"}
     ```
   - Result: 
     ```sh
       {"id": 4,"publicidad": [],"productos": [{"id": 25,"nombre": "Specialized TopFuel 2014","desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight","precio": 3500,"valoracion": 0,"theBest": 5,"mustImprove": 3,    "image": "img/ejemplo1.jpg","stock": 30,"comments": [],"categoria": "nueva","bad": 1, "referencia": 0},...
     ```
    - Status code: 201 Created

 - Delete a category
   - Method: DELETE
   - URL: https://localhost:8443/rest/admin/categories/{id}
   - Result: 
     ```sh
       {"id": 4,null: [],"productos": [{"id": 25,"nombre": "Specialized TopFuel 2014","desProducto": "If you ask us, it had been too long since hardtails got their world shaken up. Well, our all-new Epic Hardtail Pro Carbon World Cup does just that. New geometry, crazy low frame weight","precio": 3500,"valoracion": 0,"theBest": 5,"mustImprove": 3,    "image": "img/ejemplo1.jpg","stock": 30,"comments": [],"categoria": null ,"bad": 1, "referencia": 0},...
     ```
    - Status code: 200 (OK)

- Returns all users
   - Method: GET
   - URL: https://localhost:8443/rest/admin/users
   - Result: 
     ```sh
       [{"id": 1,"name": "user","email": "userDomPru@gmail.com","password": $2a$10$wm/QYuSvX9abk.1QnFS5huGB910vJ3nTKQ0JpcYotbgj2feRKqX9u","role": ["ROLE_USER"],"address": "Thomas Nolan Kaszas I,5322 Otter Lane,Middleberge FL 32068","phone": 695698365,"comments": [],"pedidos": [],"fullName": "Dominguez Pruiz"},
     ```
    - Status code: 200 (OK)

- Returns an users
   - Method: GET
   - URL: https://localhost:8443/rest/admin/users/{id}
   - Result: 
     ```sh
       {"id": 2,"name": "user2","email": "userDomPru@gmail.com", "password": "$2a$10$sKuh1QHsOr2sTcu7XYw68uaITfybeHe3b42QNkmQ32av58ulQg3va", "role": ["ROLE_USER"], "address": "Thomas Nolan Kaszas II,5322 Otter Lane,Middleberge FL 32068","phone": 695698365,"comments": [],"pedidos": [],"fullName": "Dominguez Pruiz"}
     ```
    - Status code: 200 (OK)

- Add an users
   - Method: POST
   - URL: https://localhost:8443/rest/admin/users
   - Body:
     ```sh
       {"name": "nuevouserpost","fullName": "Garcia Garcia","email": "nuevo@gmail.com","imgRuta": "","password": "pass",  "phone": "668588558","address": "direccion calle"}
     ```
   - Result: 
     ```sh
       {"id": 4,"name": "nuevouserpost","email": "nuevo@gmail.com","password": "pass","role": ["ROLE_USER","ROLE_ADMIN"  ],"address": "direccion calle","phone": 668588558,"comments": [],"pedidos": [],"fullName": "Garcia Garcia"}
     ```
    - Status code: 201 Created

- Return ads
   - Method: GET
   - URL: https://localhost:8443/rest/admin/publicity
   - Result: 
     ```sh
       [{"id": 1,"nomPublicidad": "Chania","imagen": "img/carousel1.jpg"},{"id": 2,"nomPublicidad": "Chania","imagen": "img/carousel2.jpg"},{"id": 3,"nomPublicidad": "Flower","imagen": "img/carousel3.jpg"}]
     ```
    - Status code: 200 (OK)

- Returns ande delete an ad 
   - Method: DELETE
   - URL: https://localhost:8443/rest/admin/publicity/{id}
   - Result: 
     ```sh
       {"id": 2,"nomPublicidad": "Chania","imagen": "img/carousel2.jpg"}
     ```
    - Status code: 200 (OK)
 
