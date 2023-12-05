# PruebaT-ctnica

## Pruebas en postman:

**Crear:**
* url: http://localhost:8080/realizar-compra/1
* body:  
[
  {
    "producto": {
      "id": 1
    },
    "cantidad": 2
  },
  {
    "producto": {
      "id": 2
    },
    "cantidad": 3
  }

]

  
**Verificar el descuento:** 
* url: http://localhost:8080/factura/1/total
