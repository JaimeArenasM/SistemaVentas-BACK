package group1.HD.Back.Service;

import java.util.Optional;
import java.util.Random;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import group1.HD.Back.Dto.Request.IniciarSesionRequest;
import group1.HD.Back.Dto.Request.RegistroRequest;
import group1.HD.Back.Dto.Response.AuthResponse;
import group1.HD.Back.Model.Cliente;
import group1.HD.Back.Model.ReseteoContraseña;
import group1.HD.Back.Model.Usuario;
import group1.HD.Back.Repository.ClienteRepository;
import group1.HD.Back.Repository.ReseteoContraseñaRespository;
import group1.HD.Back.Repository.UsuarioRepository;
import group1.HD.Back.Security.Jwt;
import jakarta.transaction.Transactional;

@Service
public class AuthService {
   private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Jwt jwt;
    private final ReseteoContraseñaRespository tokenRepository;
    private final EmailService emailService;


    public AuthService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, Jwt jwt, ReseteoContraseñaRespository tokenRepository, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }
   

 @Transactional
    public AuthResponse registrarCliente(RegistroRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }
        if (request.getDni() != null && clienteRepository.existsByDni(request.getDni())) {
            throw new RuntimeException("El DNI ya está registrado.");
        }

        // 1. Creamos el Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setCorreo(request.getCorreo());
        nuevoUsuario.setContrasenaHash(passwordEncoder.encode(request.getPassword()));
        nuevoUsuario.setTipoUsuario("cliente");
        nuevoUsuario.setEstado("activo");

        // 2. Creamos el Cliente
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombres(request.getNombres());
        nuevoCliente.setApellidos(request.getApellidos());
        nuevoCliente.setDni(request.getDni());
        nuevoCliente.setTelefono(request.getTelefono());
        nuevoCliente.setDireccion(request.getDireccion());

        // 3. ENLACE BIDIRECCIONAL (La clave del éxito)
        // Le decimos al cliente quién es su usuario, y al usuario quién es su cliente
        nuevoCliente.setUsuario(nuevoUsuario);
        nuevoUsuario.setCliente(nuevoCliente);

        // 4. Guardamos SOLO el Usuario. 
        // Gracias a tu CascadeType.ALL, Hibernate detectará al cliente y lo guardará automáticamente en su tabla
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // 5. Generamos el token usando el usuario ya consolidado
        String token = jwt.generateToken(usuarioGuardado.getCorreo(), usuarioGuardado.getTipoUsuario());
        
        return new AuthResponse(token, usuarioGuardado.getTipoUsuario(), usuarioGuardado.getCliente().getNombres());
    }

    public AuthResponse login(IniciarSesionRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.buscarPorCorreo(request.getCorreo()).orElseThrow();
        String token = jwt.generateToken(usuario.getCorreo(), usuario.getTipoUsuario());
        
        String nombres = usuario.getTipoUsuario().equals("admin") ? "Administrador" : usuario.getCliente().getNombres();
        
        return new AuthResponse(token, usuario.getTipoUsuario(), nombres);
    }

    /*PARA RECUPERAR CONTRASEÑA */
    // Método 1: Genera el código y manda el correo
    @Transactional
    public void solicitarRecuperacionPassword(String correo) {
        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("No existe ninguna cuenta con este correo."));

        // Si ya tenía un código anterior sin usar, lo borramos para no acumular basura
        Optional<ReseteoContraseña> tokenExistente = tokenRepository.findByUsuario(usuario);
        tokenExistente.ifPresent(tokenRepository::delete);

        // Generamos un código aleatorio de 6 dígitos (ej: 048592)
        String codigoGenerado = String.format("%06d", new Random().nextInt(999999));

        // Lo guardamos en la base de datos (se auto-configura para expirar en 15 min)
        ReseteoContraseña miToken = new ReseteoContraseña(codigoGenerado, usuario);
        tokenRepository.save(miToken);

        // Llamamos al cartero
        emailService.enviarCodigoRecuperacion(usuario.getCorreo(), codigoGenerado);
    }

    // Método 2: Verifica el código y cambia la contraseña
    @Transactional
    public void cambiarPasswordConToken(String token, String nuevaPassword) {
        ReseteoContraseña resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("El código ingresado es incorrecto."));

        if (resetToken.estaExpirado()) {
            tokenRepository.delete(resetToken);
            throw new RuntimeException("El código ha expirado. Por favor, solicita uno nuevo.");
        }

        // Si todo está bien, le cambiamos la contraseña al usuario
        Usuario usuario = resetToken.getUsuario();
        usuario.setContrasenaHash(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);

        // Destruimos el código para que no se pueda volver a usar
        tokenRepository.delete(resetToken);
    }
}
