package group1.HD.Back.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ActualizarPerfilRequest {
    
    @NotBlank(message = "Nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellidos;

    @NotBlank(message = "DNI es obligatorio")
    @Pattern( regexp = "\\d{8}")
    private String dni;

    @NotBlank(message = "Telefono es obligatorio")
    @Pattern( regexp = "\\d{9}")
    private String telefono;

    @NotBlank(message = "Direccion es obligatoria")
    private String direccion;


    public ActualizarPerfilRequest() {
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
