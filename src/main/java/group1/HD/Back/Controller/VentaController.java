package group1.HD.Back.Controller;

import group1.HD.Back.Dto.Request.CrearVentaRequest;
import group1.HD.Back.Dto.Response.VentaResponse;
import group1.HD.Back.Service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // POST /api/ventas/checkout -> Crea una venta real para el cliente logueado
    @PostMapping("/checkout")
    public ResponseEntity<VentaResponse> crearVenta(
            Authentication authentication, 
            @Valid @RequestBody CrearVentaRequest request) {
        
        String correo = authentication.getName(); // Lee el token
        return ResponseEntity.ok(ventaService.checkout(correo, request));
    }

    // GET /api/ventas/mis-compras -> Muestra solo las compras del usuario que hace la petición
    @GetMapping("/mis-compras")
    public ResponseEntity<List<VentaResponse>> misCompras(Authentication authentication) {
        String correo = authentication.getName(); // Lee el token
        return ResponseEntity.ok(ventaService.obtenerMisCompras(correo));
    }

    // GET /api/ventas -> solo Admin Lista el historial de toda la tienda
    @GetMapping
    public ResponseEntity<List<VentaResponse>> obtenerTodasLasVentas() {
        return ResponseEntity.ok(ventaService.obtenerTodasLasVentas());
    }

    // PUT /api/ventas/5/estado?estado=PAGADO -> solo Admin Cambia el estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<VentaResponse> cambiarEstado(
            @PathVariable Integer id,
            @RequestParam String estado) {
        return ResponseEntity.ok(ventaService.cambiarEstado(id, estado));
    }
}