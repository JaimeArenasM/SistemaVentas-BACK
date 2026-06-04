package group1.HD.Back.Repository;

import group1.HD.Back.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
// Validar si ya existe una categoría con ese nombre
    boolean existsByNombreIgnoreCase(String nombre);
}