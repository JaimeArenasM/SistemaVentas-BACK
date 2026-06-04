package group1.HD.Back.Service;

<<<<<<< HEAD
import group1.HD.Back.dto.CarritoItemDTO;
import group1.HD.Back.model.Carrito;
import group1.HD.Back.model.DetalleCarrito;

=======
import group1.HD.Back.Dto.CarritoItemDTO;
import group1.HD.Back.Model.Carrito;
import group1.HD.Back.Model.DetalleCarrito;
>>>>>>> 5e1d47078f6943589c00fd1e8da6c8be384a9b73
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

                item.setCantidad(
                        item.getCantidad() + dto.getCantidad()
                );

                return;
            }
        }

        DetalleCarrito nuevoItem = new DetalleCarrito(
                dto.getIdProducto(),
                dto.getCantidad()
        );

        carrito.getItems().add(nuevoItem);
    }

    public void actualizarCantidad(Long idProducto, Integer cantidad) {

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