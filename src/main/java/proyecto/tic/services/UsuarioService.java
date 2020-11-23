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

    public Iterable<Item> findAllSpecification(List<String> coloresParaFiltrar, List<String> tallesParaFiltrar, List<String> typeItem, List<String> marcas){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> item = cq.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();

        List<Predicate> predicadoColor = new ArrayList<>();
        if(coloresParaFiltrar.size() > 0){
            List<Item> itemsColor = new ArrayList<>();
            for(String color : coloresParaFiltrar){
                List<Stock> stockDelColor = stockRepository.findAllByColor(color);
                for(Stock stock : stockDelColor){
                    if(stock.getCantidad() > 0){
                        itemsColor.add(stock.getItem());
                    }
                }
                predicadoColor.add(item.in(itemsColor));
            }
            cq.where(item.in(itemsColor));
            predicates.add(cb.or(predicadoColor.toArray(new Predicate[0])));
        }

        List<Predicate> predicadoTalle = new ArrayList<>();
        if(tallesParaFiltrar.size() > 0){
            List<Item> itemsTalle = new ArrayList<>();
            for(String talle : tallesParaFiltrar){
                List<Stock> stockDelTalle =  stockRepository.findAllByTalle(talle);
                for(Stock stock : stockDelTalle){
                    if(stock.getCantidad() > 0){
                        itemsTalle.add(stock.getItem());
                    }
                }
                predicadoTalle.add(item.in(itemsTalle));
            }
            cq.where(item.in(itemsTalle));
            predicates.add(cb.or(predicadoColor.toArray(new Predicate[0])));
        }

        List<Predicate> predicadoType = new ArrayList<>();
        if(typeItem.size() > 0){
            typeItem.forEach(typeproducto -> predicadoType.add(cb.equal(item.get("type"), typeproducto)));
            predicates.add(cb.or(predicadoType.toArray(new Predicate[0])));
        }

        List<Predicate> predicadoMarcas = new ArrayList<>();
        if(marcas.size() > 0){
            marcas.forEach(categoryItem -> predicadoMarcas.add(cb.equal(item.get("brand"), categoryItem)));
            predicates.add(cb.or(predicadoMarcas.toArray(new Predicate[0])));
        }

        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
        cq.where(finalPredicate);
        List<Item> items = em.createQuery(cq).getResultList();
        return items;
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
