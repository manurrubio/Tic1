package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.exceptions.AdminAlreadyExists;
import proyecto.tic.services.exceptions.InvalidInformation;

public interface AdminManager {
    void addAdmin(Admin administrador) throws InvalidInformation, AdminAlreadyExists;
}
