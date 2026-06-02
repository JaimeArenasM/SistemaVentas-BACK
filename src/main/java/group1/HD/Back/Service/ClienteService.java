package group1.HD.Back.Service;
import group1.HD.Back.Dto.Request.ActualizarPerfilRequest;
import group1.HD.Back.Dto.Response.PerfilClienteResponse;
import group1.HD.Back.Model.Cliente;
import group1.HD.Back.Repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Creamos un método interno privado para convertir Entidad -> DTO
    // Así evitamos repetir el código 
    private PerfilClienteResponse mapearAResponse(Cliente cliente) {
        return new PerfilClienteResponse(
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getDni(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getUsuario().getCorreo()
        );
    }

    public PerfilClienteResponse obtenerPerfilPorCorreo(String correo) {
        Cliente cliente = clienteRepository.obtenerPerfilPorCorreoJPQL(correo)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return mapearAResponse(cliente);
    }

    // Actualiza y devuelve el DTO con los nuevos datos
    @Transactional
    public PerfilClienteResponse actualizarPerfil(String correo, ActualizarPerfilRequest request) {
        // Buscamos al cliente con JPQL
        Cliente cliente = clienteRepository.obtenerPerfilPorCorreoJPQL(correo)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Actualizamos los datos 
        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());

        Cliente actualizado = clienteRepository.save(cliente);
        
        return mapearAResponse(actualizado);
    }
}