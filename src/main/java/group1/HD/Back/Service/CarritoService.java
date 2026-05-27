package group1.HD.Back.service;

import group1.HD.Back.dto.CarritoItemDTO;
import group1.HD.Back.model.Carrito;
import group1.HD.Back.model.DetalleCarrito;

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