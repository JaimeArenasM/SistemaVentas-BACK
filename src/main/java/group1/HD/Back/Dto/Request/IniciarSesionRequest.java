package group1.HD.Back.Dto.Request;

import jakarta.validation.constraints.NotBlank;

public class IniciarSesionRequest {
    
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;


    public IniciarSesionRequest() {
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
