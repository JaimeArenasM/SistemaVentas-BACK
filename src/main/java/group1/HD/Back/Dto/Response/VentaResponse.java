package group1.HD.Back.Dto.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaResponse {
    private Integer idVenta;
    private String nombreCliente;
    private LocalDateTime fechaVenta;
    private BigDecimal total;
    private String metodoPago;
    private String estadoPago;
    private List<DetalleVentaResponse> detalles;

    public VentaResponse(Integer idVenta, String nombreCliente, LocalDateTime fechaVenta, BigDecimal total,
            String metodoPago, String estadoPago, List<DetalleVentaResponse> detalles) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
        this.detalles = detalles;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public List<DetalleVentaResponse> getDetalles() {
        return detalles;
    }
}
