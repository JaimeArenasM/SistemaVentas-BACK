package group1.HD.Back.Controller;

import group1.HD.Back.Dto.Request.CarritoRequest;
import group1.HD.Back.Dto.Response.CarritoResponse;
import group1.HD.Back.Service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public ResponseEntity<CarritoResponse> obtenerCarrito(Authentication authentication) {
        String correo = authentication.getName();
        return ResponseEntity.ok(carritoService.obtenerMiCarrito(correo));
    }

    @PostMapping("/items")
    public ResponseEntity<CarritoResponse> agregarProducto(
            Authentication authentication, 
            @Valid @RequestBody CarritoRequest dto) {
        String correo = authentication.getName();
        return ResponseEntity.ok(carritoService.agregarProducto(correo, dto));
    }

    @PutMapping("/items/{idProducto}")
    public ResponseEntity<CarritoResponse> actualizarCantidad(
            Authentication authentication,
            @PathVariable Integer idProducto,
            @RequestParam Integer cantidad) {
        String correo = authentication.getName();
        return ResponseEntity.ok(carritoService.actualizarCantidad(correo, idProducto, cantidad));
    }

    @DeleteMapping("/items/{idProducto}")
    public ResponseEntity<CarritoResponse> eliminarProducto(
            Authentication authentication,
            @PathVariable Integer idProducto) {
        String correo = authentication.getName();
        return ResponseEntity.ok(carritoService.eliminarProducto(correo, idProducto));
    }

    @DeleteMapping("/limpiar")
    public ResponseEntity<CarritoResponse> limpiarCarrito(Authentication authentication) {
        String correo = authentication.getName();
        return ResponseEntity.ok(carritoService.limpiarCarrito(correo));
    }
}