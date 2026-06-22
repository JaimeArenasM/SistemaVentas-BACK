package group1.HD.Back.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCodigoRecuperacion(String destino, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("isaacchupillon16@gmail.com"); 
        mensaje.setTo(destino);
        mensaje.setSubject("Código de Recuperación - La Tiendita de Don Pepe");
        mensaje.setText("Hola,\n\nTu código de seguridad para cambiar la contraseña es: " + codigo + 
                        "\n\nEste código expirará en 15 minutos.\nSi no solicitaste esto, ignora este mensaje.");
        
        mailSender.send(mensaje);
    }
}