package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;

import java.util.List;

public interface StockManager {
    void addStock(Stock stock);
    Stock getStock(String id);

    List<Stock> getStockByItem(Item item);

    void buyStock(String id, Long cantidad);
}
