package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.AdminRepository;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Usuario;
import proyecto.tic.services.exceptions.AdminAlreadyExists;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.rmi.AdminManager;

@Service
public class AdminService implements AdminManager {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public void addAdmin(Admin administrador) throws InvalidInformation, AdminAlreadyExists {

        if (administrador.getFirstName() == null || "".equals(administrador.getFirstName()) || administrador.getLastName() == null || "".equals(administrador.getLastName()) || administrador.getEmail() == null || "".equals(administrador.getEmail()) || administrador.getPassword() == null || administrador.getTelefono() < 0 || administrador.getDireccion() == null || "".equals(administrador.getDireccion())) {

            throw new InvalidInformation();

        } else if (adminRepository.findOneByCi(administrador.getCi()) != null) {

            throw new AdminAlreadyExists();
        }

        adminRepository.save(administrador);

    }

    public Admin getAdmi(int ci){
        Admin admi= adminRepository.findOneByCi(ci);
        return admi;
    }
}
