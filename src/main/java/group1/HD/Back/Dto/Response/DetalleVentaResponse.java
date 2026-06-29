package group1.HD.Back.Dto.Response;

import java.math.BigDecimal;

public class DetalleVentaResponse {
    private Integer idProducto; 
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String imagenUrl; 

    // Actualizamos el constructor para recibir los 6 datos
    public DetalleVentaResponse(Integer idProducto, String nombreProducto, Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal, String imagenUrl) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.imagenUrl = imagenUrl;
    }

    public Integer getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public Integer getCantidad() { return cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public String getImagenUrl() { return imagenUrl; }
}