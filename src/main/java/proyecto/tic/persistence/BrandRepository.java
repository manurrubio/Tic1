package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.tic.services.entities.Brand;

import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findOneByName(String name);
    List<Brand> findAll();

}
