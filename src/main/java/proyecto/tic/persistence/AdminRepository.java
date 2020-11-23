package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Usuario;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Admin findOneByCi(int ci);
}
