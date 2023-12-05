package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import org.slf4j.Logger;


import com.example.demo.model.Cliente;
import com.example.demo.model.DetalleFactura;
import com.example.demo.model.Factura;
import com.example.demo.model.Producto;
import com.example.demo.services.impl.ClienteServiceImpl;
import com.example.demo.services.impl.DetalleFacturaServiceImpl;
import com.example.demo.services.impl.FacturaServiceImpl;
import com.example.demo.services.impl.ProductoServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private FacturaServiceImpl facturaService;

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private DetalleFacturaServiceImpl detalleFacturaService;

    @GetMapping("/clientes")
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/productos")
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.getAllProductos();
    }

    @PostMapping("/realizar-compra/{idCliente}")
    public Factura realizarCompra(@PathVariable("idCliente") Long idCliente, @RequestBody List<DetalleFactura> detallesFactura) {


        Cliente cliente = clienteService.getClienteById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));


        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFecha(new Date(System.currentTimeMillis()));
        facturaService.save(factura);


        double totalFactura = 0;
        int totalItems = 0;
        for (DetalleFactura detalle : detallesFactura) {
            Producto producto = productoService.getProductoById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + detalle.getProducto().getId()));

            detalle.setFactura(factura);
            detalle.setPrecio(producto.getPrecio() * detalle.getCantidad());
            detalleFacturaService.save(detalle);

            totalItems+= detalle.getCantidad();


            totalFactura += detalle.getPrecio();
        }



        if (totalItems >= 5 && totalFactura > 1000000) {
            for (DetalleFactura detalle : detallesFactura){
                double precioDetalleDescuento = detalle.getPrecio()*.9;
                detalle.setPrecio(precioDetalleDescuento);
                detalleFacturaService.save(detalle);
            }
        }

        return factura;
    }

    @GetMapping("/factura/{idFactura}/total")
    public Double obtenerTotalFactura(@PathVariable Long idFactura) {
        Factura factura = facturaService.getFacturaById(idFactura)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + idFactura));

        List<DetalleFactura> detallesFactura = detalleFacturaService.getDetallesFacturaByIdFactura(idFactura);

        double totalFactura = detallesFactura.stream().mapToDouble(DetalleFactura::getPrecio).sum();

        return totalFactura;
    }

}