package group1.HD.Back.Repository;

import group1.HD.Back.Model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

// JPQL: Listar todos los activos con paginación
    @Query("SELECT p FROM Producto p WHERE p.estado = 'activo'")
    Page<Producto> listarActivos(Pageable pageable);

    // JPQL: Buscar por nombre (ignorando mayúsculas) y estado activo
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND p.estado = 'activo'")
    Page<Producto> buscarPorNombreJPQL(@Param("nombre") String nombre, Pageable pageable);

    // JPQL: Filtrar por categoría y estado activo
    @Query("SELECT p FROM Producto p JOIN p.categoria c WHERE c.idCategoria = :idCategoria AND p.estado = 'activo'")
    Page<Producto> buscarPorCategoriaJPQL(@Param("idCategoria") Integer idCategoria, Pageable pageable);
}
