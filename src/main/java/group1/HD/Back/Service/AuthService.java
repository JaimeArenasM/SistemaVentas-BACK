package group1.HD.Back.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import group1.HD.Back.Dto.Request.IniciarSesionRequest;
import group1.HD.Back.Dto.Request.RegistroRequest;
import group1.HD.Back.Dto.Response.AuthResponse;
import group1.HD.Back.Model.Cliente;
import group1.HD.Back.Model.Usuario;
import group1.HD.Back.Repository.ClienteRepository;
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

    public AuthService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository,
                       PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                       Jwt jwt) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
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
}
