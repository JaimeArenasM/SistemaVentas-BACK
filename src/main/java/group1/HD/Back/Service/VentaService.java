package group1.HD.Back.Service;
import java.time.LocalDateTime; /* Es mejor tener conexion con todos los modelos */
import java.util.ArrayList; /*Importar el repositorio para usar sus métodos en la base de datos */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group1.HD.Back.Model.DetalleVenta;
import group1.HD.Back.Model.EstadoVenta;
import group1.HD.Back.Model.Venta;
import group1.HD.Back.Repository.VentaRepository;


@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Venta checkout() {

        Venta venta = new Venta();

        venta.setFecha(LocalDateTime.now());
        venta.setEstado(EstadoVenta.PENDIENTE);
        List<DetalleVenta> detalles = new ArrayList<>();

        DetalleVenta d1 = new DetalleVenta();
        d1.setNombreProducto("Laptop");
        d1.setCantidad(1);
        d1.setPrecio(2500.0);
        d1.setVenta(venta);

        detalles.add(d1);

        DetalleVenta d2 = new DetalleVenta();
        d2.setNombreProducto("Mouse");
        d2.setCantidad(2);
        d2.setPrecio(80.0);
        d2.setVenta(venta);

        detalles.add(d2);

        venta.setDetalles(detalles);

        double total = 0;

        for (DetalleVenta d : detalles) {
            total += d.getPrecio() * d.getCantidad();
        }

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerVentas() {
        
        return ventaRepository.findAll();
    }
    public Venta obtenerVentaPorId(Long id) {

         return ventaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public Venta cambiarEstado(Long id, EstadoVenta nuevoEstado) {

    Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
         venta.setEstado(nuevoEstado);

         return ventaRepository.save(venta);
    }
}