@Tienda

Feature: realizar compras en jumbo por menu y que no pase la compra de 200000 y minimo 20 productos y recoger en tienda
  (antioquia-medellin-las Vegas)

  Scenario: compra de productos

    Given registramos los datos de entrega "001"
    When  seleccionamos los productos
    Then  validamos carrito de compras