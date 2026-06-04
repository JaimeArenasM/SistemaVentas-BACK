package group1.HD.Back.Service;

<<<<<<< HEAD
<<<<<<< HEAD
import group1.HD.Back.dto.CarritoItemDTO;
=======
import group1.HD.Back.Dto.Request.CarritoRequest;
import group1.HD.Back.Dto.Response.CarritoResponse;
>>>>>>> 941416a258aaff9c63d3b945424e730d25a0b4a0
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

    public CarritoResponse obtenerCarrito() {
        return mapToResponse(carrito);
    }

    public CarritoResponse agregarProducto(CarritoRequest request) {

        carrito.getItems().stream()
                .filter(item -> item.getIdProducto().equals(request.getProductoId()))
                .findFirst()
                .ifPresentOrElse(

                        item -> item.setCantidad(
                                item.getCantidad() + request.getCantidad()
                        ),

                        () -> {

                            DetalleCarrito detalle = new DetalleCarrito(
                                    request.getProductoId(),
                                    request.getCantidad()
                            );

                            detalle.setCarrito(carrito);

                            carrito.getItems().add(detalle);
                        }
                );

        return mapToResponse(carrito);
    }

    public CarritoResponse actualizarCantidad(Long idProducto, Integer cantidad) {

        carrito.getItems().stream()
                .filter(item -> item.getIdProducto().equals(idProducto))
                .findFirst()
                .ifPresent(item -> item.setCantidad(cantidad));

        return mapToResponse(carrito);
    }

    public CarritoResponse eliminarProducto(Long idProducto) {

        carrito.getItems()
                .removeIf(item -> item.getIdProducto().equals(idProducto));

        return mapToResponse(carrito);
    }

    public CarritoResponse limpiarCarrito() {

        carrito.getItems().clear();

        return mapToResponse(carrito);
    }

    private CarritoResponse mapToResponse(Carrito carritoModel) {

        CarritoResponse response = new CarritoResponse();

        response.setId(1L);

        response.setNombreUsuario("Usuario en sesión");

        int totalItems = carritoModel.getItems()
                .stream()
                .mapToInt(DetalleCarrito::getCantidad)
                .sum();

        response.setTotalItems(totalItems);

        response.setTotalPagar(0.0);

        return response;
    }
}