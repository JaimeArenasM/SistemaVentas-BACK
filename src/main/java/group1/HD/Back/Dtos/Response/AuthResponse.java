package group1.HD.Back.Dtos.Response;

public class AuthResponse {
    private String token;
    private String tipoUsuario;
    private String nombres;
    

    public AuthResponse(String token, String tipoUsuario, String nombres) {
        this.token = token;
        this.tipoUsuario = tipoUsuario;
        this.nombres = nombres;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
}
