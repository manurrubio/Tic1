package proyecto.tic.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.UsuarioRepository;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Client;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.services.exceptions.*;
import proyecto.tic.services.rmi.UsuarioManager;

@Service
public class UsuarioService implements UsuarioManager {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioRepository getRepository(){
        return usuarioRepository;
    }

    @Override
    public void addClient(Client cliente) throws InvalidInformation, ClientAlreadyExists { // Ingresar

        if (cliente.getFirstName() == null || "".equals(cliente.getFirstName()) || cliente.getLastName() == null || "".equals(cliente.getLastName()) || cliente.getEmail() == null || "".equals(cliente.getEmail()) || cliente.getPassword() == null || cliente.getTelefono() < 0 || cliente.getDireccion() == null || "".equals(cliente.getDireccion())) {

            throw new InvalidInformation();

        } else if (usuarioRepository.findOneByCi(cliente.getCi()) != null) {

            throw new ClientAlreadyExists();
        }

        usuarioRepository.save(cliente);

    }

    @Override
    public void addAdmin(Admin administrador) throws InvalidInformation, AdminAlreadyExists { // podemos hacer que un admin le cree la cuenta a otro admin

        if (administrador.getFirstName() == null || "".equals(administrador.getFirstName()) || administrador.getLastName() == null || "".equals(administrador.getLastName()) || administrador.getEmail() == null || "".equals(administrador.getEmail()) || administrador.getPassword() == null || administrador.getTelefono() < 0 || administrador.getDireccion() == null || "".equals(administrador.getDireccion())) {

            throw new InvalidInformation();

        } else if (usuarioRepository.findOneByCi(administrador.getCi()) != null) {

            throw new AdminAlreadyExists();
        }

        usuarioRepository.save(administrador);

    }

    public Usuario getUsuario(int ci){
        Usuario usuario= usuarioRepository.findOneByCi(ci);
        return usuario;
    }

/*    @Override
    public Client getClient(int ci) throws ClientNotExist { // Cuando implementemos esta funcion la implementamos en un try catch en el controller por el DISCRIMINATOR, por el (CASTEO)
        Client cliente = (Client) usuarioRepository.findOneByCi(ci);
        if(cliente == null){
        throw new ClientNotExist();
        }
        return cliente;
    } */ // VER PORQUE NO ME CONECTA A MI BDD

/*    @Override
    public Admin getAdmin(int ci) throws AdminNotExist { // try catch para que no lo caste a admin si no es con discrimintaor admin (Dudas sobre esto preguntar!)
        Admin administrador = (Admin) usuarioRepository.findOneByCiAndDiscriminator(ci, Admin);
        if(administrador == null){
            throw new AdminNotExist();
        }
        return administrador;
    } */ // VER PORQUE NO ME CONECTA A MI BDD

    @Override
    public void deleteUsuario(int ci){ // Si el cliente quiere borrar su cuenta
            usuarioRepository.deleteById(ci);
    }

    @Override
    public void updateClient(Client cliente){ // Esta funcion la usa solo un administrador para actualizar los datos de un cliente o VOLVER A UN CLIENTE ADMIN (ARREGLAR)
        Client clienteExistente = ((Client) usuarioRepository.findOneByCi(cliente.getCi()));
        clienteExistente.setFirstName(cliente.getFirstName());
        clienteExistente.setLastName(cliente.getLastName());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setPassword(cliente.getPassword());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setDireccion(cliente.getDireccion());
        usuarioRepository.save(clienteExistente);
    }



}
