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

    /*Devuelve todos los usuarios empacados en el DTO */
    public List<UsuarioResponse> listarTodos(){
        return usuarioRepository.findAll().stream().map(u ->{
            String nombre = (u.getCliente() != null) ? u.getCliente().getNombres() : "Administrador";
            return new UsuarioResponse(
                u.getIdUsuario(),
                u.getCorreo(),
                u.getTipoUsuario(),
                u.getEstado(),
                nombre
            );
        }).collect(Collectors.toList());
    }

    /*el administrador puede activar o bloquear cuentas */
    @Transactional
    public UsuarioResponse cambiarEstado(Integer idUsuario, String nuevoEstado){
        Usuario usuario = usuarioRepository.findById(idUsuario)
        .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if (!nuevoEstado.equals("activo")&& !nuevoEstado.equals("bloqueado")) {
          throw new RuntimeException("Estado no valido. Usa 'activo' o 'bloqueado'") ; 
        }

        usuario.setEstado(nuevoEstado);
        Usuario guardado = usuarioRepository.save(usuario);
        String nombre = (guardado.getCliente() != null) ? guardado.getCliente().getNombres() : "Administrador";
        return new UsuarioResponse(
            guardado.getIdUsuario(), guardado.getCorreo(),
            guardado.getTipoUsuario(),guardado.getEstado(), nombre
        );
    }
}
