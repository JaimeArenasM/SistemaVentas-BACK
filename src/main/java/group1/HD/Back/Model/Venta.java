package group1.HD.Back.Model;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*; /* Mejor llamarlos a todos de una vez , todos son muy utiles */

@Entity
@Table(name = "venta")
public class Venta {

    @Id /* Esto indicara la llave primaria */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Esto hará que Postgree genere IDS de forma automática */
    private Long id;

    private LocalDateTime fecha;

    private Double total;

    @Enumerated(EnumType.STRING) /*Esto permitirá que se guarde como texto , perfecto apra que quede unicamente como Pendiente o Pagado */
    private EstadoVenta estado;
/* Relación uno-a-muchos:
   - Una Venta puede tener varios DetalleVenta.
   - Cada DetalleVenta está asociado a una única Venta.
   - 'mappedBy = "venta"' indica que el dueño de la relación es la entidad DetalleVenta. */
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;
    public Venta() {
    }
    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }
}