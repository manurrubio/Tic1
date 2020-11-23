package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.services.exceptions.*;


public interface UsuarioManager {
    void addClient(Usuario cliente) throws InvalidInformation, ClientAlreadyExists;
//    Client getClient(int ci) throws ClientNotExist;
//    Admin getAdmin(int ci) throws AdminNotExist;
    void deleteUsuario(int ci);
    void updateClient(Usuario cliente);
}
