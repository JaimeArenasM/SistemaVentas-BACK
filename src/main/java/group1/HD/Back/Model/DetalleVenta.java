package group1.HD.Back.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProducto;

    private Integer cantidad;

    private Double precio;

    @ManyToOne /* Significa que muchos detalles pueden pertenecer a una sola venta , como por ejemplo piqueos y gaseosas en una misma boleta */
    /*Se usa mas que todo ya que una venta puede tener muchos detalles */
    @JoinColumn(name = "venta_id")
    private Venta venta;

    public DetalleVenta() {
    }

    public Long getId() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}