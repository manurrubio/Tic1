package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Store;


public interface StoreRepository extends CrudRepository<Store, Long> {
    Store findOneByName(String name);
}
