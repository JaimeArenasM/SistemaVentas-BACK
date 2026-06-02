package group1.HD.Back.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import group1.HD.Back.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
    boolean existsByCorreo(String correo);

    /*JPQL: BUSCAR USUARIO POR CORREO EXACTO */
    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
    Optional<Usuario> buscarPorCorreo(@Param("correo") String correo);

    /*JPQL: LISTAR SOLO USUARIOS ACTIVOS */
    @Query("SELECT u FROM Usuario u WHERE u.estado = 'activo'")
    List<Usuario> listarSoloActivos();

}
