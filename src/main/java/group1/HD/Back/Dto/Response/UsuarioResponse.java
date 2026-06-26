package group1.HD.Back.Dto.Response;

public class UsuarioResponse {
    private Integer idUsuario;
    private String correo;
    private String tipoUsuario;
    private String estado;
    private String nombreCliente;
    
    // === NUEVOS CAMPOS PARA EL MODAL ===
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Integer idUsuario, String correo, String tipoUsuario, String estado, String nombreCliente, String apellidos, String dni, String telefono, String direccion) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters Originales
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    // Getters y Setters Nuevos
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}