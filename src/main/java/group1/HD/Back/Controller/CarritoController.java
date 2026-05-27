package group1.HD.Back.controller;

import group1.HD.Back.dto.CarritoItemDTO;
import group1.HD.Back.model.Carrito;
import group1.HD.Back.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Obtener carrito
    @GetMapping
    public ResponseEntity<Carrito> obtenerCarrito() {

        Carrito carrito = carritoService.obtenerCarrito();

        return ResponseEntity.ok(carrito);
    }

    // Agregar producto al carrito
    @PostMapping("/items")
    public ResponseEntity<String> agregarProducto(
            @RequestBody CarritoItemDTO dto
    ) {

        carritoService.agregarProducto(dto);

        return ResponseEntity.ok("Producto agregado al carrito");
    }

    // Actualizar cantidad de un producto
    @PutMapping("/items/{idProducto}")
    public ResponseEntity<String> actualizarCantidad(
            @PathVariable Long idProducto,
            @RequestParam Integer cantidad
    ) {

        carritoService.actualizarCantidad(idProducto, cantidad);

        return ResponseEntity.ok("Cantidad actualizada");
    }

    // Eliminar producto del carrito
    @DeleteMapping("/items/{idProducto}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable Long idProducto
    ) {

        carritoService.eliminarProducto(idProducto);

        return ResponseEntity.ok("Producto eliminado del carrito");
    }

    // Vaciar carrito completo
    @DeleteMapping("/limpiar")
    public ResponseEntity<String> limpiarCarrito() {

        carritoService.limpiarCarrito();

        return ResponseEntity.ok("Carrito vaciado");
    }
}