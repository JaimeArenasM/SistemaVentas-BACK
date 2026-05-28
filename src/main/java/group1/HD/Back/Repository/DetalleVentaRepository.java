package group1.HD.Back.Repository;

import group1.HD.Back.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleRepository extends JpaRepository<DetalleVenta, Long> {
}