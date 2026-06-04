package group1.HD.Back.Dto.Response;

public class CategoriaResponse {
    
    private Integer idCategoria;
    private String nombre;
    private String descripcion;

    public CategoriaResponse(Integer idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Integer getIdCategoria() { return idCategoria; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
}
