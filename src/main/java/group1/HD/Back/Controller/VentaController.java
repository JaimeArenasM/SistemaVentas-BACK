package group1.HD.Back.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import group1.HD.Back.Model.EstadoVenta;
import group1.HD.Back.Model.Venta;
import group1.HD.Back.Service.VentaService;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin("*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/checkout")
    public Venta checkout() {
        return ventaService.checkout();
    }

    @GetMapping
    public List<Venta> obtenerVentas() {
        return ventaService.obtenerVentas();
    }
    @GetMapping("/{id}")
    public Venta obtenerVentaPorId(@PathVariable Long id) {

        return ventaService.obtenerVentaPorId(id);
    }

    @PutMapping("/{id}/estado")
    public Venta cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoVenta estado
    ) {

        return ventaService.cambiarEstado(id, estado);
    }

    @GetMapping("/mis-compras")
    public List<Venta> misCompras() {

        return ventaService.obtenerVentas();
    }
    }