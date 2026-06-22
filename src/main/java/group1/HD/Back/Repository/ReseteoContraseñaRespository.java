package group1.HD.Back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import group1.HD.Back.Model.ReseteoContraseña;
import group1.HD.Back.Model.Usuario;

public interface ReseteoContraseñaRespository extends JpaRepository<ReseteoContraseña,Integer>{
    // Buscar un token por el código de 6 dígitos
    Optional<ReseteoContraseña>findByToken(String token);
    
    // Buscar si el usuario ya tiene un código generado
    Optional<ReseteoContraseña> findByUsuario(Usuario usuario);
}
