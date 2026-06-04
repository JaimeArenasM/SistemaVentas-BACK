package group1.HD.Back.Service;

import group1.HD.Back.Dto.Request.CategoriaRequest;
import group1.HD.Back.Dto.Response.CategoriaResponse;
import group1.HD.Back.Model.Categoria;
import group1.HD.Back.Repository.CategoriaRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private CategoriaResponse mapearAResponse(Categoria c) {
        return new CategoriaResponse(c.getIdCategoria(), c.getNombre(), c.getDescripcion());
    }

    public List<CategoriaResponse> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoriaResponse crearCategoria(CategoriaRequest dto) {
        if (categoriaRepository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new RuntimeException("La categoría ya existe");
        }
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        
        return mapearAResponse(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaResponse actualizarCategoria(Integer id, CategoriaRequest dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Validar que el nuevo nombre no choque con otra categoría existente
        if (!categoria.getNombre().equalsIgnoreCase(dto.getNombre()) && 
            categoriaRepository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new RuntimeException("El nombre de la categoría ya está en uso");
        }

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        return mapearAResponse(categoriaRepository.save(categoria));
    }
}
