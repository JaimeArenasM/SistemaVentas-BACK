package group1.HD.Back.Controller;

import group1.HD.Back.Dto.Request.CategoriaRequest;
import group1.HD.Back.Dto.Response.CategoriaResponse;
import group1.HD.Back.Service.CategoriaService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

   // Ruta pública: Cualquiera puede ver las categorías para filtrar el catálogo
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    // Rutas protegidas: Solo el Admin puede crear o editar
    @PostMapping
    public ResponseEntity<CategoriaResponse> crearCategoria(@Valid @RequestBody CategoriaRequest dto) {
        return ResponseEntity.ok(categoriaService.crearCategoria(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaRequest dto) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, dto));
    }
}