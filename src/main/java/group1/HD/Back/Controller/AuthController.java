package group1.HD.Back.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group1.HD.Back.Dto.Request.IniciarSesionRequest;
import group1.HD.Back.Dto.Request.RegistroRequest;
import group1.HD.Back.Dto.Response.AuthResponse;
import group1.HD.Back.Service.AuthService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
     private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity <AuthResponse> login(@Valid @RequestBody IniciarSesionRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/registrar")
    public ResponseEntity <AuthResponse> register(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.ok(authService.registrarCliente(request));
    }


}
