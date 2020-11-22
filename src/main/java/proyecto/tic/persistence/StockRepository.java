package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;

public interface StockRepository extends CrudRepository<Stock,String> {
    Stock findOneById(String id);
    Stock findOneByItem(Item item);
}
