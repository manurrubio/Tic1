package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
    Store findOneByName(String name);
}
