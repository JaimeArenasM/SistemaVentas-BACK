package group1.HD.Back.Controller; // Ojo: Verifica si tu carpeta es Controller o controller (con C mayúscula como en la imagen)

import group1.HD.Back.Dto.Request.CarritoRequest; // Cambiamos el import
import group1.HD.Back.Dto.Response.CarritoResponse; // Agregamos el import del Response
import group1.HD.Back.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Obtener carrito
    @GetMapping
    public ResponseEntity<CarritoResponse> obtenerCarrito() { // Cambiamos el retorno a CarritoResponse
        CarritoResponse carrito = carritoService.obtenerCarrito();
        return ResponseEntity.ok(carrito);
    }

    // Agregar producto al carrito
    @PostMapping("/items")
    public ResponseEntity<CarritoResponse> agregarProducto( // Cambiamos el retorno a CarritoResponse
            @Valid @RequestBody CarritoRequest request       // Cambiamos CarritoItemDTO a CarritoRequest
    ) {
        CarritoResponse carritoActualizado = carritoService.agregarProducto(request);
        return ResponseEntity.ok(carritoActualizado); // Ahora devolvemos el carrito actualizado, no un String
    }

    // Actualizar cantidad de un producto
    @PutMapping("/items/{idProducto}")
    public ResponseEntity<CarritoResponse> actualizarCantidad( // Cambiamos el retorno
            @PathVariable Long idProducto,
            @RequestParam Integer cantidad
    ) {
        CarritoResponse carritoActualizado = carritoService.actualizarCantidad(idProducto, cantidad);
        return ResponseEntity.ok(carritoActualizado);
    }

    // Eliminar producto del carrito
    @DeleteMapping("/items/{idProducto}")
    public ResponseEntity<CarritoResponse> eliminarProducto( // Cambiamos el retorno
            @PathVariable Long idProducto
    ) {
        CarritoResponse carritoActualizado = carritoService.eliminarProducto(idProducto);
        return ResponseEntity.ok(carritoActualizado);
    }

    // Vaciar carrito completo
    @DeleteMapping("/limpiar")
    public ResponseEntity<CarritoResponse> limpiarCarrito() { // Cambiamos el retorno
        CarritoResponse carritoVacio = carritoService.limpiarCarrito();
        return ResponseEntity.ok(carritoVacio);
    }
}