package group1.HD.Back.Service;

import group1.HD.Back.Dto.Request.CarritoRequest;
import group1.HD.Back.Dto.Response.CarritoResponse;
import group1.HD.Back.Dto.Response.DetalleVentaResponse;
import group1.HD.Back.Model.Carrito;
import group1.HD.Back.Model.Cliente;
import group1.HD.Back.Model.DetalleCarrito;
import group1.HD.Back.Model.Producto;
import group1.HD.Back.Repository.CarritoRepository;
import group1.HD.Back.Repository.ClienteRepository;
import group1.HD.Back.Repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public CarritoService(CarritoRepository carritoRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    // 1. OBTENER O CREAR EL CARRITO DEL CLIENTE
    private Carrito obtenerCarritoEntidad(String correo) {
        return carritoRepository.buscarCarritoActivoPorCorreo(correo).orElseGet(() -> {
            Cliente cliente = clienteRepository.obtenerPerfilPorCorreoJPQL(correo)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            Carrito nuevoCarrito = new Carrito();
            nuevoCarrito.setCliente(cliente);
            nuevoCarrito.setEstado("activo");
            return carritoRepository.save(nuevoCarrito);
        });
    }

    public CarritoResponse obtenerMiCarrito(String correo) {
        return mapearADto(obtenerCarritoEntidad(correo));
    }

    // 2. AGREGAR PRODUCTO
    @Transactional
    public CarritoResponse agregarProducto(String correo, CarritoRequest dto) {
        Carrito carrito = obtenerCarritoEntidad(correo);
        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificamos si el producto ya está en el carrito
        Optional<DetalleCarrito> itemExistente = carrito.getItems().stream()
                .filter(item -> item.getProducto().getIdProducto().equals(producto.getIdProducto()))
                .findFirst();

        if (itemExistente.isPresent()) {
            // Si ya existe, le sumamos la cantidad
            DetalleCarrito detalle = itemExistente.get();
            detalle.setCantidad(detalle.getCantidad() + dto.getCantidad());
            BigDecimal cantidadBD = new BigDecimal(detalle.getCantidad());
            detalle.setSubtotal(producto.getPrecio().multiply(cantidadBD));
        } else {
            // Si es nuevo, lo creamos
            DetalleCarrito nuevoItem = new DetalleCarrito();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            nuevoItem.setCantidad(dto.getCantidad());
            BigDecimal cantidadBD = new BigDecimal(dto.getCantidad());
            nuevoItem.setSubtotal(producto.getPrecio().multiply(cantidadBD));
            carrito.getItems().add(nuevoItem);
        }

        return mapearADto(carritoRepository.save(carrito));
    }

    // 3. ACTUALIZAR CANTIDAD EXACTA
    @Transactional
    public CarritoResponse actualizarCantidad(String correo, Integer idProducto, Integer cantidad) {
        Carrito carrito = obtenerCarritoEntidad(correo);
        
        for (DetalleCarrito item : carrito.getItems()) {
            if (item.getProducto().getIdProducto().equals(idProducto)) {
                item.setCantidad(cantidad);
                BigDecimal cantidadBD = new BigDecimal(cantidad);
                item.setSubtotal(item.getProducto().getPrecio().multiply(cantidadBD));
                break;
            }
        }
        return mapearADto(carritoRepository.save(carrito));
    }

    // 4. ELIMINAR PRODUCTO
    @Transactional
    public CarritoResponse eliminarProducto(String correo, Integer idProducto) {
        Carrito carrito = obtenerCarritoEntidad(correo);
        carrito.getItems().removeIf(item -> item.getProducto().getIdProducto().equals(idProducto));
        return mapearADto(carritoRepository.save(carrito));
    }

    // 5. VACIAR CARRITO
    @Transactional
    public CarritoResponse limpiarCarrito(String correo) {
        Carrito carrito = obtenerCarritoEntidad(correo);
        carrito.getItems().clear();
        return mapearADto(carritoRepository.save(carrito));
    }

   // 6. MAPEO A DTO Y CÁLCULO DEL TOTAL
    private CarritoResponse mapearADto(Carrito carrito) {
        BigDecimal totalCarrito = BigDecimal.ZERO;
        
        List<DetalleVentaResponse> itemsDto = carrito.getItems().stream().map(item -> {
            return new DetalleVentaResponse(
                    item.getProducto().getIdProducto(), // Enviamos el ID al frontend
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.getProducto().getPrecio(),
                    item.getSubtotal(),
                    item.getProducto().getImagenUrl()  // Enviamos la foto al frontend
            );
        }).collect(Collectors.toList());

        for (DetalleVentaResponse item : itemsDto) {
            totalCarrito = totalCarrito.add(item.getSubtotal());
        }

        return new CarritoResponse(carrito.getIdCarrito(), totalCarrito, itemsDto);
    }
}