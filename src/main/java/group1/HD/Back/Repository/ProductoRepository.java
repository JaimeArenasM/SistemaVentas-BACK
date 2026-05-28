package group1.HD.Back.Repository;

import group1.HD.Back.Model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Page<Producto> findByEstado(String estado, Pageable pageable);

    Page<Producto> findByNombreContainingIgnoreCaseAndEstado(
            String nombre,
            String estado,
            Pageable pageable
    );

    Page<Producto> findByCategoria_IdCategoriaAndEstado(
            Integer idCategoria,
            String estado,
            Pageable pageable
    );
}
