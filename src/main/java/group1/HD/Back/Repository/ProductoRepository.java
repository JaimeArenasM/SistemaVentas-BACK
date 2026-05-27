package group1.HD.Back.repository;

import group1.HD.Back.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {

    private final List<Producto> productos = new ArrayList<>();

    public List<Producto> listarProductos() {
        return productos;
    }

    public Producto buscarPorId(Long idProducto) {

        return productos.stream()
                .filter(producto ->
                        producto.getIdProducto().equals(idProducto))
                .findFirst()
                .orElse(null);
    }

    public void guardarProducto(Producto producto) {
        productos.add(producto);
    }
}