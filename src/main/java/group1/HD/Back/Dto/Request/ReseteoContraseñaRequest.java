package group1.HD.Back.Dto.Request;

public class ReseteoContraseñaRequest {
    private String token;
    private String nuevaPassword;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getNuevaPassword() { return nuevaPassword; }
    public void setNuevaPassword(String nuevaPassword) { this.nuevaPassword = nuevaPassword; }
}
