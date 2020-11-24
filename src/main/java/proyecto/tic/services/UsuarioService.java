package proyecto.tic.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.ItemRepository;
import proyecto.tic.persistence.StockRepository;
import proyecto.tic.persistence.UsuarioRepository;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.services.exceptions.*;
import proyecto.tic.services.rmi.UsuarioManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UsuarioManager {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private StockRepository stockRepository;

    public UsuarioRepository getRepository(){
        return usuarioRepository;
    }

    @Autowired
    public EntityManager em;
    @Override
    public void addClient(Usuario cliente) throws InvalidInformation, ClientAlreadyExists { // Ingresar

        if (cliente.getFirstName() == null || "".equals(cliente.getFirstName()) || cliente.getLastName() == null || "".equals(cliente.getLastName()) || cliente.getEmail() == null || "".equals(cliente.getEmail()) || cliente.getPassword() == null || cliente.getTelefono() < 0 || cliente.getDireccion() == null || "".equals(cliente.getDireccion())) {

            throw new InvalidInformation();

        } else if (usuarioRepository.findOneByCi(cliente.getCi()) != null) {

            throw new ClientAlreadyExists();
        }

        usuarioRepository.save(cliente);

    }

    public Usuario getUsuario(int ci){
        Usuario usuario= usuarioRepository.findOneByCi(ci);
        return usuario;
    }



    @Override
    public void deleteUsuario(int ci){ // Si el cliente quiere borrar su cuenta
            usuarioRepository.deleteById(ci);
    }

    @Override
    public void updateClient(Usuario cliente){ // Esta funcion la usa solo un administrador para actualizar los datos de un cliente o VOLVER A UN CLIENTE ADMIN (ARREGLAR)
        Usuario clienteExistente =  usuarioRepository.findOneByCi(cliente.getCi());
        clienteExistente.setFirstName(cliente.getFirstName());
        clienteExistente.setLastName(cliente.getLastName());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setPassword(cliente.getPassword());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setDireccion(cliente.getDireccion());
        usuarioRepository.save(clienteExistente);
    }



}
