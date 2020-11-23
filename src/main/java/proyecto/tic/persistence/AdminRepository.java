package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyecto.tic.services.entities.Admin;
import proyecto.tic.services.entities.Usuario;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Admin findOneByCi(int ci);
}
