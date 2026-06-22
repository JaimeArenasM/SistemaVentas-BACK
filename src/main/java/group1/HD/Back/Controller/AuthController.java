package group1.HD.Back.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group1.HD.Back.Dto.Request.IniciarSesionRequest;
import group1.HD.Back.Dto.Request.RegistroRequest;
import group1.HD.Back.Dto.Request.ReseteoContraseñaRequest;
import group1.HD.Back.Dto.Request.SolicitarRecuperacionRequest;
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

    @PostMapping("/solicitar-recuperacion")
    public ResponseEntity<?> solicitarRecuperacion(@RequestBody SolicitarRecuperacionRequest request) {
        try {
            authService.solicitarRecuperacionPassword(request.getCorreo());
            // Devolvemos un 200 OK con un mensaje JSON simple
            return ResponseEntity.ok().body("{\"mensaje\": \"Si el correo existe, se ha enviado un código de recuperación.\"}");
        } catch (Exception e) {
            // Mandamos Bad Request si el correo no existe
            return ResponseEntity.badRequest().body("{\"mensaje\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ReseteoContraseñaRequest request) {
        try {
            authService.cambiarPasswordConToken(request.getToken(), request.getNuevaPassword());
            return ResponseEntity.ok().body("{\"mensaje\": \"Contraseña actualizada correctamente.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"mensaje\": \"" + e.getMessage() + "\"}");
        }
    }

}
