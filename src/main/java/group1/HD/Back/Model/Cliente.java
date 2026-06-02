package group1.HD.Back.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario",nullable = false)
    private Usuario usuario;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "dni", length = 8, unique = true)
    private String dni;

    @Column(name = "direccion",length = 200)
    private String direccion;


    public Cliente() {
    }


    public Cliente(Integer idCliente, Usuario usuario, String nombres, String apellidos, String telefono, String dni, String direccion) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.direccion = direccion;
    }


    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
