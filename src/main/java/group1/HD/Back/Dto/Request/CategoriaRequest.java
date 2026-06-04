package group1.HD.Back.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {
 
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;
    private String descripcion;

    public CategoriaRequest() {}
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
