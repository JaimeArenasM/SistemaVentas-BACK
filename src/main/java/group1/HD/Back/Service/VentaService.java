package group1.HD.Back.Service;
import java.time.LocalDateTime; /* Es mejor tener conexion con todos los modelos */
import java.util.ArrayList; /*Importar el repositorio para usar sus métodos en la base de datos */
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group1.HD.Back.Dto.Request.CrearVentaRequest;
import group1.HD.Back.Dto.Request.DetalleVentaRequest;
import group1.HD.Back.Dto.Response.DetalleVentaResponse;
import group1.HD.Back.Dto.Response.VentaResponse;
import group1.HD.Back.Model.Cliente;
import group1.HD.Back.Model.DetalleVenta;
import group1.HD.Back.Model.EstadoVenta;
import group1.HD.Back.Model.Producto;
import group1.HD.Back.Model.Venta;
import group1.HD.Back.Repository.ClienteRepository;
import group1.HD.Back.Repository.ProductoRepository;
import group1.HD.Back.Repository.VentaRepository;
import jakarta.transaction.Transactional;


@Service
public class VentaService {

   private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public VentaResponse checkout(String correoCliente, CrearVentaRequest request) {
        
        // 1. Buscamos al cliente logueado
        Cliente cliente = clienteRepository.obtenerPerfilPorCorreoJPQL(correoCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 2. Preparamos la cabecera de la venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDateTime.now());
        venta.setEstado("PENDIENTE");
        
        List<DetalleVenta> detallesBD = new ArrayList<>();
        double total = 0.0;

        // 3. Procesamos los productos que envió Angular en el carrito
        for (DetalleVentaRequest item : request.getDetalles()) {
            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio()); // El precio se saca de la BD por seguridad
            
            detallesBD.add(detalle);
            total += (producto.getPrecio() * item.getCantidad());
        }

        venta.setDetalles(detallesBD);
        venta.setTotal(total);

        // 4. Guardamos todo en cascada
        Venta guardada = ventaRepository.save(venta);

        return mapearADto(guardada);
    }

    public List<VentaResponse> obtenerMisCompras(String correo) {
        return ventaRepository.listarMisCompras(correo).stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public List<VentaResponse> obtenerTodasLasVentas() {
        return ventaRepository.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    @Transactional
    public VentaResponse cambiarEstado(Integer id, String nuevoEstado) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        venta.setEstado(nuevoEstado);
        return mapearADto(ventaRepository.save(venta));
    }

    // Método privado para emplatar la respuesta limpia a Angular
    private VentaResponse mapearADto(Venta v) {
        List<DetalleVentaResponse> detallesDto = v.getDetalles().stream()
            .map(d -> new DetalleVentaResponse(d.getProducto().getNombre(), d.getCantidad(), d.getPrecioUnitario(), d.getCantidad() * d.getPrecioUnitario()))
            .collect(Collectors.toList());

        return new VentaResponse(v.getIdVenta(), v.getCliente().getNombres(), v.getFechaVenta(),
            v.getTotal(), v.getEstado(), detallesDto
        );
    }
}