package group1.HD.Back.Controller;

import group1.HD.Back.Dto.ProductoDTO;
import group1.HD.Back.Model.Producto;
import group1.HD.Back.Service.ProductoService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public Page<Producto> listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer categoria
    ) {

        return productoService.listarProductos(
                page,
                size,
                nombre,
                categoria
        );
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Integer id) {
        return productoService.obtenerProducto(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody ProductoDTO dto) {
        return productoService.crearProducto(dto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(
            @PathVariable Integer id,
            @RequestBody ProductoDTO dto
    ) {
        return productoService.actualizarProducto(id, dto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Integer id) {

        productoService.eliminarProducto(id);

        return "Producto desactivado correctamente";
    }
}