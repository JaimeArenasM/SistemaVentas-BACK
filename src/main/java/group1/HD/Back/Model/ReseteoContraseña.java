package group1.HD.Back.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESETEO_CONTRASENA")
public class ReseteoContraseña {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 6)
    private String token; 

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fechaExpiracion;

    public ReseteoContraseña() {
    }

    public ReseteoContraseña(Integer id, String token, Usuario usuario, LocalDateTime fechaExpiracion) {
        this.id = id;
        this.token = token;
        this.usuario = usuario;
        this.fechaExpiracion = fechaExpiracion;
    }

    public ReseteoContraseña(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
        this.fechaExpiracion = LocalDateTime.now().plusMinutes(15);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaExpiracion() {
        return this.fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    
    // 4. ¡ESTE ES EL MÉTODO QUE TE FALTABA! (Verifica los 15 minutos)
    public boolean estaExpirado() {
        return LocalDateTime.now().isAfter(this.fechaExpiracion);
    }
}