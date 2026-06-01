package group1.HD.Back.Dtos.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistroRequest {
    
    @NotBlank(message = "Correo Obligatorio")
    @Email(message = "No cumple el dominio del correo")
    private String correo;

    @NotBlank(message = "Contraseña es obligatoria")
    @Size(message = "No puede ser menor 6 digitos",min = 6)
    private String password;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellidos;

    @Pattern(regexp = "\\d{8}")
    private String dni;

    @Pattern(regexp = "\\d{9}")
    private String telefono;

    private String direccion;


    public RegistroRequest() {
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

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
