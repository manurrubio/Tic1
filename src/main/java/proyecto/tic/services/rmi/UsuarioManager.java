package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Client;
import proyecto.tic.services.exceptions.*;


public interface UsuarioManager {
    void addClient(Client cliente) throws InvalidInformation, ClientAlreadyExists;
    void addAdmin(Admin administrador) throws InvalidInformation, AdminAlreadyExists;
//    Client getClient(int ci) throws ClientNotExist;
//    Admin getAdmin(int ci) throws AdminNotExist;
    void deleteUsuario(int ci);
    void updateClient(Client cliente);
}
