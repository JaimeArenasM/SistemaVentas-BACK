package group1.HD.Back.Dto.Response;

import java.math.BigDecimal;
import java.util.List;

public class CarritoResponse {
    private Integer idCarrito;
    private BigDecimal totalCarrito;
    private List<DetalleVentaResponse> items;

    public CarritoResponse(Integer idCarrito, BigDecimal totalCarrito, List<DetalleVentaResponse> items) {
        this.idCarrito = idCarrito;
        this.totalCarrito = totalCarrito;
        this.items = items;
    }
    public Integer getIdCarrito() { return idCarrito; }
    public BigDecimal getTotalCarrito() { return totalCarrito; }
    public List<DetalleVentaResponse> getItems() { return items; }
}