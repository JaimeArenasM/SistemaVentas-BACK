package group1.HD.Back.Dto.Response;

public class UsuarioResponse {
    private Integer idUsuario;
    private String correo;
    private String tipoUsuario;
    private String estado;
    private String nombreCliente;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Integer idUsuario, String correo, String tipoUsuario, String estado, String nombreCliente) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.nombreCliente = nombreCliente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
