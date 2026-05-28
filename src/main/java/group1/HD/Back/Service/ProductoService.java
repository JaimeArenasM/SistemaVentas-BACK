package group1.HD.Back.Service;

import group1.HD.Back.Dto.ProductoDTO;
import group1.HD.Back.Model.Categoria;
import group1.HD.Back.Model.Producto;
import group1.HD.Back.Repository.CategoriaRepository;
import group1.HD.Back.Repository.ProductoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Page<Producto> listarProductos(
            int page,
            int size,
            String nombre,
            Integer categoria
    ) {

        PageRequest pageable = PageRequest.of(page, size);

        if (nombre != null) {
            return productoRepository
                    .findByNombreContainingIgnoreCaseAndEstado(
                            nombre,
                            "activo",
                            pageable
                    );
        }

        if (categoria != null) {
            return productoRepository
                    .findByCategoria_IdCategoriaAndEstado(
                            categoria,
                            "activo",
                            pageable
                    );
        }

        return productoRepository.findByEstado("activo", pageable);
    }

    public Producto obtenerProducto(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto crearProducto(ProductoDTO dto) {

        Categoria categoria = categoriaRepository
                .findById(dto.getIdCategoria())
                .orElseThrow();

        Producto producto = new Producto();

        producto.setCategoria(categoria);
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());
        producto.setEstado("activo");

        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Integer id, ProductoDTO dto) {

        Producto producto = productoRepository.findById(id).orElseThrow();

        Categoria categoria = categoriaRepository
                .findById(dto.getIdCategoria())
                .orElseThrow();

        producto.setCategoria(categoria);
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setImagenUrl(dto.getImagenUrl());

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {

        Producto producto = productoRepository.findById(id).orElseThrow();

        producto.setEstado("inactivo");

        productoRepository.save(producto);
    }
}