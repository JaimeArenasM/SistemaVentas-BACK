package group1.HD.Back.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group1.HD.Back.Dto.Response.UsuarioResponse;
import group1.HD.Back.Model.Usuario;
import group1.HD.Back.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponse actualizarUsuario(Integer idUsuario, group1.HD.Back.Dto.Request.RegistroRequest request) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 1. Actualizamos los datos base (Comunes para Admin y Cliente)
        usuario.setCorreo(request.getCorreo());

        // 2. Lógica Condicional: ¿Es un cliente con datos biológicos?
        if (usuario.getCliente() != null) {
            usuario.getCliente().setNombres(request.getNombres());
            usuario.getCliente().setApellidos(request.getApellidos());
            usuario.getCliente().setDni(request.getDni());
            usuario.getCliente().setTelefono(request.getTelefono());
            usuario.getCliente().setDireccion(request.getDireccion());
        }

        // 3. Guardamos los cambios
        Usuario guardado = usuarioRepository.save(usuario);
        
        // 4. Extraemos todos los datos de forma segura para el DTO
        String nombre = (guardado.getCliente() != null) ? guardado.getCliente().getNombres() : "Administrador";
        String apellidos = (guardado.getCliente() != null) ? guardado.getCliente().getApellidos() : "";
        String dni = (guardado.getCliente() != null) ? guardado.getCliente().getDni() : "";
        String telefono = (guardado.getCliente() != null) ? guardado.getCliente().getTelefono() : "";
        String direccion = (guardado.getCliente() != null) ? guardado.getCliente().getDireccion() : "";

        return new UsuarioResponse(
                guardado.getIdUsuario(), 
                guardado.getCorreo(),
                guardado.getTipoUsuario(),
                guardado.getEstado(), 
                nombre, 
                apellidos, 
                dni, 
                telefono, 
                direccion
        );
    }

    /* Devuelve todos los usuarios empacados en el DTO */
    public List<UsuarioResponse> listarTodos(){
        return usuarioRepository.findAll().stream().map(u ->{
            // Extraemos todos los datos de forma segura para el DTO
            String nombre = (u.getCliente() != null) ? u.getCliente().getNombres() : "Administrador";
            String apellidos = (u.getCliente() != null) ? u.getCliente().getApellidos() : "";
            String dni = (u.getCliente() != null) ? u.getCliente().getDni() : "";
            String telefono = (u.getCliente() != null) ? u.getCliente().getTelefono() : "";
            String direccion = (u.getCliente() != null) ? u.getCliente().getDireccion() : "";
            
            return new UsuarioResponse(
                u.getIdUsuario(),
                u.getCorreo(),
                u.getTipoUsuario(),
                u.getEstado(),
                nombre,
                apellidos,
                dni,
                telefono,
                direccion
            );
        }).collect(Collectors.toList());
    }

    /* El administrador puede activar o bloquear cuentas */
    @Transactional
    public UsuarioResponse cambiarEstado(Integer idUsuario, String nuevoEstado){
        Usuario usuario = usuarioRepository.findById(idUsuario)
        .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if (!nuevoEstado.equals("activo") && !nuevoEstado.equals("bloqueado")) {
          throw new RuntimeException("Estado no valido. Usa 'activo' o 'bloqueado'") ; 
        }

        usuario.setEstado(nuevoEstado);
        Usuario guardado = usuarioRepository.save(usuario);
        
        // Extraemos todos los datos de forma segura para el DTO
        String nombre = (guardado.getCliente() != null) ? guardado.getCliente().getNombres() : "Administrador";
        String apellidos = (guardado.getCliente() != null) ? guardado.getCliente().getApellidos() : "";
        String dni = (guardado.getCliente() != null) ? guardado.getCliente().getDni() : "";
        String telefono = (guardado.getCliente() != null) ? guardado.getCliente().getTelefono() : "";
        String direccion = (guardado.getCliente() != null) ? guardado.getCliente().getDireccion() : "";
        
        return new UsuarioResponse(
            guardado.getIdUsuario(), 
            guardado.getCorreo(),
            guardado.getTipoUsuario(),
            guardado.getEstado(), 
            nombre,
            apellidos,
            dni,
            telefono,
            direccion
        );
    }
}