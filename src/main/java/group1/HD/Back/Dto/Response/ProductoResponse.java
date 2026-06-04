package group1.HD.Back.Dto.Response;

import java.math.BigDecimal;

public class ProductoResponse {
    private Integer idProducto;
    private String nombreCategoria;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String imagenUrl;
    private String estado;

    public ProductoResponse(Integer idProducto, String nombreCategoria, String nombre, String descripcion,
            BigDecimal precio, Integer stock, String imagenUrl, String estado) {
        this.idProducto = idProducto;
        this.nombreCategoria = nombreCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagenUrl = imagenUrl;
        this.estado = estado;
    }

    // Getters
    public Integer getIdProducto() {
        return idProducto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public String getEstado() {
        return estado;
    }
}
