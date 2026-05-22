package group1.HD.Back.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private Long id;
    private List<DetalleCarrito> items = new ArrayList<>();

    public Carrito() {
    }

    public Carrito(Long id, List<DetalleCarrito> items) {
        this.id = id;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DetalleCarrito> getItems() {
        return items;
    }

    public void setItems(List<DetalleCarrito> items) {
        this.items = items;
    }
}