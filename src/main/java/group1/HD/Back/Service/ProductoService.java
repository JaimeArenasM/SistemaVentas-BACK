package group1.HD.Back.Service;
import group1.HD.Back.Dto.Request.ProductoRequest;
import group1.HD.Back.Dto.Response.ProductoResponse;
import group1.HD.Back.Model.Categoria;
import group1.HD.Back.Model.Producto;
import group1.HD.Back.Repository.CategoriaRepository;
import group1.HD.Back.Repository.ProductoRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

   private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    private ProductoResponse mapearAResponse(Producto p) {
        return new ProductoResponse(
                p.getIdProducto(), p.getCategoria().getNombre(), p.getNombre(), 
                p.getDescripcion(), p.getPrecio(), p.getStock(), p.getImagenUrl(), p.getEstado()
        );
    }

    // Paginación inteligente
    public Page<ProductoResponse> listarProductos(int page, int size, String nombre, Integer categoria) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Producto> paginaEntidades;

        if (nombre != null && !nombre.isEmpty()) {
            paginaEntidades = productoRepository.buscarPorNombreJPQL(nombre, pageable);
        } else if (categoria != null) {
            paginaEntidades = productoRepository.buscarPorCategoriaJPQL(categoria, pageable);
        } else {
            paginaEntidades = productoRepository.listarActivos(pageable);
        }

        // Convertimos la página de Entidades a una página de DTOs usando .map()
        return paginaEntidades.map(this::mapearAResponse);
    }

    public ProductoResponse obtenerProducto(Integer id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapearAResponse(p);
    }

    @Transactional
    public ProductoResponse crearProducto(ProductoRequest dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setCategoria(categoria);
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());
        producto.setEstado("activo");

        return mapearAResponse(productoRepository.save(producto));
    }

    @Transactional
    public ProductoResponse actualizarProducto(Integer id, ProductoRequest dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.setCategoria(categoria);
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());

        return mapearAResponse(productoRepository.save(producto));
    }

    @Transactional
    public void eliminarProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setEstado("inactivo"); // Eliminación lógica
        productoRepository.save(producto);
    }
}