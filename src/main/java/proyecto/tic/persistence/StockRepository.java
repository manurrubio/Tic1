package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock,String> {
    Stock findOneById(String id);
    List<Stock> findOneByItem(Item item);

    Stock findAllByColor(String color);

    Stock findAllByTalle(String talle);
}
