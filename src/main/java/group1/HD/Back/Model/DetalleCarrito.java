package group1.HD.Back.Model;
public class DetalleCarrito {

    private Long idProducto;
    private String nombreProducto;
    private int cantidad;

    public DetalleCarrito() {
    }

    public DetalleCarrito(Long idProducto, String nombreProducto, int cantidad) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}