package group1.HD.Back.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group1.HD.Back.Model.EstadoVenta;
import group1.HD.Back.Model.Venta;
import group1.HD.Back.Repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Venta crearVenta() {

        Venta venta = new Venta();

        venta.setFecha(LocalDateTime.now());
        venta.setTotal(100.0);
        venta.setEstado(EstadoVenta.PENDIENTE);

        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }
}