package group1.HD.Back.controller;

import group1.HD.Back.dto.CarritoItemDTO;
import group1.HD.Back.model.Carrito;
import group1.HD.Back.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin("*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public Carrito obtenerCarrito() {
        return carritoService.obtenerCarrito();
    }

    @PostMapping("/items")
    public String agregarProducto(@RequestBody CarritoItemDTO dto) {

        carritoService.agregarProducto(dto);

        return "Producto agregado al carrito";
    }

    @PutMapping("/items/{idProducto}")
    public String actualizarCantidad(
            @PathVariable Long idProducto,
            @RequestParam int cantidad
    ) {

        carritoService.actualizarCantidad(idProducto, cantidad);

        return "Cantidad actualizada";
    }

    @DeleteMapping("/items/{idProducto}")
    public String eliminarProducto(@PathVariable Long idProducto) {

        carritoService.eliminarProducto(idProducto);

        return "Producto eliminado";
    }

    @DeleteMapping("/limpiar")
    public String limpiarCarrito() {

        carritoService.limpiarCarrito();

        return "Carrito vaciado";
    }
}