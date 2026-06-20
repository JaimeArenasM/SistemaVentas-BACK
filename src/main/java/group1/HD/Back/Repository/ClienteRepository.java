package group1.HD.Back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import group1.HD.Back.Model.Cliente;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> { // Cambiado a Integer

    boolean existsByDni(String dni);

    // 1. JPQL con INNER JOIN: Traer el perfil del cliente filtrando por el correo de su cuenta
    @Query("SELECT c FROM Cliente c JOIN c.usuario u WHERE u.correo = :correo")
    Optional<Cliente> obtenerPerfilPorCorreoJPQL(@Param("correo") String correo);
}