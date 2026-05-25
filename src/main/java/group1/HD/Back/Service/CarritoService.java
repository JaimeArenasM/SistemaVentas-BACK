package group1.HD.Back.Service;

import group1.HD.Back.Dto.CarritoItemDTO;
import group1.HD.Back.Model.Carrito;
import group1.HD.Back.Model.DetalleCarrito;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    private final Carrito carrito = new Carrito();

    public Carrito obtenerCarrito() {
        return carrito;
    }

    public void agregarProducto(CarritoItemDTO dto) {

        for (DetalleCarrito item : carrito.getItems()) {

            if (item.getIdProducto().equals(dto.getIdProducto())) {
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }

        DetalleCarrito nuevoItem = new DetalleCarrito(
                dto.getIdProducto(),
                dto.getNombreProducto(),
                dto.getCantidad()
        );

        carrito.getItems().add(nuevoItem);
    }

    public void actualizarCantidad(Long idProducto, int cantidad) {

        for (DetalleCarrito item : carrito.getItems()) {

            if (item.getIdProducto().equals(idProducto)) {
                item.setCantidad(cantidad);
                return;
            }
        }
    }

    public void eliminarProducto(Long idProducto) {

        carrito.getItems().removeIf(
                item -> item.getIdProducto().equals(idProducto)
        );
    }

    public void limpiarCarrito() {
        carrito.getItems().clear();
    }
}