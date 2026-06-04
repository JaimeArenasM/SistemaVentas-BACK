package group1.HD.Back.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import group1.HD.Back.Dto.Request.CrearVentaRequest;
import group1.HD.Back.Dto.Request.DetalleVentaRequest;
import group1.HD.Back.Dto.Response.DetalleVentaResponse;
import group1.HD.Back.Dto.Response.VentaResponse;
import group1.HD.Back.Model.Cliente;
import group1.HD.Back.Model.DetalleVenta;
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
        
        Cliente cliente = clienteRepository.obtenerPerfilPorCorreoJPQL(correoCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDateTime.now());
        venta.setMetodoPago(request.getMetodoPago() != null ? request.getMetodoPago() : "Efectivo"); 
        venta.setEstado("PAGADO"); 
        
        List<DetalleVenta> detallesBD = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO; 

        for (DetalleVentaRequest item : request.getDetalles()) {
            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio()); 
            
            detallesBD.add(detalle);
            
            BigDecimal cantidadBD = new BigDecimal(item.getCantidad());
            BigDecimal subtotal = producto.getPrecio().multiply(cantidadBD);
            total = total.add(subtotal);
        }

        venta.setDetalles(detallesBD);
        venta.setTotal(total);

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
    public VentaResponse cambiarEstado(Integer id, String nuevoEstadoPago) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        
        venta.setEstado(nuevoEstadoPago);
        
        return mapearADto(ventaRepository.save(venta));
    }

    // --- AQUÍ ESTÁ LA MAGIA FINAL ---
    private VentaResponse mapearADto(Venta v) {
        List<DetalleVentaResponse> detallesDto = v.getDetalles().stream()
            .map(d -> {
                BigDecimal cantidadBD = new BigDecimal(d.getCantidad());
                BigDecimal subtotal = d.getPrecioUnitario().multiply(cantidadBD);
                
                return new DetalleVentaResponse(
                        d.getProducto().getNombre(), 
                        d.getCantidad(), 
                        d.getPrecioUnitario(), 
                        subtotal
                );
            })
            .collect(Collectors.toList());

        // Ahora el constructor envía los 7 parámetros, igual que en tu VentaResponse
        return new VentaResponse(
            v.getIdVenta(), 
            v.getCliente().getNombres(), 
            v.getFechaVenta(),
            v.getTotal(), 
            v.getMetodoPago(), 
            v.getEstado(), 
            detallesDto
        );
    }
}