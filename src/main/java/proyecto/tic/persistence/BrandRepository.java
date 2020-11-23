package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyecto.tic.services.entities.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findOneByName(String name);
    List<Brand> findAll();

}
