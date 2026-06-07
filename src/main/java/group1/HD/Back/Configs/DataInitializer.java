package group1.HD.Back.Configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import group1.HD.Back.Model.Usuario;
import group1.HD.Back.Repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@donpepe.com";
        
        if (usuarioRepository.buscarPorCorreo(adminEmail).isEmpty()) {
            Usuario admin = new Usuario();
            admin.setCorreo(adminEmail);
            admin.setContrasenaHash(passwordEncoder.encode("admin123")); 
            admin.setTipoUsuario("admin"); 
             admin.setEstado("activo"); 
            
            usuarioRepository.save(admin);
            System.out.println("✅ Administrador creado desde el código: " + adminEmail);
        }
    }
}