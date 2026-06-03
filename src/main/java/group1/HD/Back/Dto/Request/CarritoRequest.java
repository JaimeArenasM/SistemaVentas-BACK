package group1.HD.Back.Dto.Request;

public class CarritoRequest {

    private Long productoId; // El ID del producto que quieres agregar
    private Integer cantidad; // Cuántas unidades de ese producto

    // Constructores
    public CarritoRequest() {
    }

    public CarritoRequest(Long productoId, Integer cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}