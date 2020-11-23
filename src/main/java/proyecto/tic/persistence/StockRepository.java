package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock,String> {
    Stock findOneById(String id);
    List<Stock> findOneByItem(Item item);

    List<Stock> findAllByColor(String color);

    List<Stock> findAllByTalle(String talle);
}
