package group1.HD.Back.Repository;

import group1.HD.Back.Model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    
    // JPQL: Buscar el carrito que el cliente está usando actualmente
    @Query("SELECT c FROM Carrito c WHERE c.cliente.usuario.correo = :correo AND c.estado = 'activo'")
    Optional<Carrito> buscarCarritoActivoPorCorreo(@Param("correo") String correo);
}