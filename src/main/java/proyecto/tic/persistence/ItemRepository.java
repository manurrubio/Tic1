package proyecto.tic.persistence;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> , JpaSpecificationExecutor<Item> {
    List<Item> findByCategory(String category);
    List<Item> findByType(String type);
    List<Item> findByBrand(String brand);
    List<Item> findById(String id);
}
