package group1.HD.Back.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import group1.HD.Back.Model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    // JPQL: Buscar solo las ventas del cliente 
    @Query("SELECT v FROM Venta v WHERE v.cliente.usuario.correo = :correo ORDER BY v.fechaVenta DESC")
    List<Venta> listarMisCompras(@Param("correo") String correo);
}