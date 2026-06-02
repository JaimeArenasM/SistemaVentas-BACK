package group1.HD.Back.Security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import group1.HD.Back.Models.Usuario;
import group1.HD.Back.Repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;


    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws
    UsernameNotFoundException{
        Usuario usuario = usuarioRepository.buscarPorCorreo(correo)
        .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con correo: "+ correo));

        if (usuario.getEstado().equals("bloqueado")) {
            throw new RuntimeException("El usuario esta bloqueado");
        }
        return new org.springframework.security.core.userdetails.User(usuario.getCorreo(),usuario.getContrasenaHash(),
        Collections.singletonList(new SimpleGrantedAuthority(usuario.getTipoUsuario())));
    }
}
