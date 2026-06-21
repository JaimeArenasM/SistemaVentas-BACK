package group1.HD.Back.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group1.HD.Back.Dto.Request.RegistroRequest;
import group1.HD.Back.Dto.Response.UsuarioResponse;
import group1.HD.Back.Service.UsuarioService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /api/usuarios -> Muestra la tabla completa
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

     @PutMapping("/{id}")
     public ResponseEntity<UsuarioResponse> actualizarUsuario
     (@PathVariable Integer id, @RequestBody RegistroRequest request) {
                  
         return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
     }

    // PUT /api/usuarios/5/estado?estado=bloqueado
    @PutMapping("/{id}/estado")
    public ResponseEntity<UsuarioResponse> cambiarEstado(
            @PathVariable Integer id, 
            @RequestParam String estado) {
        return ResponseEntity.ok(usuarioService.cambiarEstado(id, estado));
    }
}
