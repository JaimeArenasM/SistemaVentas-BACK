package group1.HD.Back.service;

import group1.HD.Back.Dto.Request.CarritoRequest;
import group1.HD.Back.Dto.Response.CarritoResponse;
import group1.HD.Back.model.Carrito;
import group1.HD.Back.model.DetalleCarrito;

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