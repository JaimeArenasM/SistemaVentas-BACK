package group1.HD.Back.Repositories;

import group1.HD.Back.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { // Cambiado a Integer

    boolean existsByDni(String dni);

    // 1. JPQL con INNER JOIN: Traer el perfil del cliente filtrando por el correo de su cuenta
    @Query("SELECT c FROM Cliente c JOIN c.usuario u WHERE u.correo = :correo")
    Optional<Cliente> obtenerPerfilPorCorreoJPQL(@Param("correo") String correo);

    // 2. JPQL: Buscador múltiple (Busca en nombres o apellidos ignorando mayúsculas)
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombres) LIKE LOWER(CONCAT('%', :filtro, '%')) OR LOWER(c.apellidos) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Cliente> buscarPorNombreOApellido(@Param("filtro") String filtro);
}