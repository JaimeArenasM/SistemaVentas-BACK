package group1.HD.Back.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<DetalleCarrito> items = new ArrayList<>();

    public Carrito() {
    }

    public Carrito(Long idCarrito, List<DetalleCarrito> items) {
        this.idCarrito = idCarrito;
        this.items = items;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public List<DetalleCarrito> getItems() {
        return items;
    }

    public void setItems(List<DetalleCarrito> items) {
        this.items = items;
    }
}