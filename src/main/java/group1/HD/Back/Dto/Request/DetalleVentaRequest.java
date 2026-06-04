package group1.HD.Back.Dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DetalleVentaRequest {
    @NotNull(message = "El producto es obligatorio")
    private Integer idProducto;

    @NotNull
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;

    public DetalleVentaRequest() {}
    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
