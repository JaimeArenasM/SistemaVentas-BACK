package group1.HD.Back.Dto.Response;

import java.time.LocalDateTime;

public class CarritoResponse {

    private Long id;
    private String nombreUsuario; // O clienteId, dependiendo de tu modelo
    private Integer totalItems;   // Cuántos productos hay en el carrito
    private Double totalPagar;    // El monto total a pagar
    private LocalDateTime fechaActualizacion;

    // Constructores
    public CarritoResponse() {
    }

    public CarritoResponse(Long id, String nombreUsuario, Integer totalItems, Double totalPagar, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.totalItems = totalItems;
        this.totalPagar = totalPagar;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}