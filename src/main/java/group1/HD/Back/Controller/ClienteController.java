package group1.HD.Back.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group1.HD.Back.Dto.Request.ActualizarPerfilRequest;
import group1.HD.Back.Dto.Response.PerfilClienteResponse;
import group1.HD.Back.Service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET /api/clientes/perfil -> Devuelve los datos del cliente logueado
    @GetMapping("/perfil")
    public ResponseEntity<PerfilClienteResponse> miPerfil(Authentication authentication) {
        // Extraemos el correo desde el Token JWT
        String correo = authentication.getName(); 
        
        return ResponseEntity.ok(clienteService.obtenerPerfilPorCorreo(correo));
    }

    // PUT /api/clientes/perfil s
    @PutMapping("/perfil")
    public ResponseEntity<PerfilClienteResponse> actualizarMiPerfil(
            Authentication authentication,
            @Valid @RequestBody ActualizarPerfilRequest request) {
        
        // Verificamos quién es el que intenta actualizar
        String correo = authentication.getName();
        
        return ResponseEntity.ok(clienteService.actualizarPerfil(correo, request));
    }
}
