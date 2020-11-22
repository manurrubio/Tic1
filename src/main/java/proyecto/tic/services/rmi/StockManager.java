package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.entities.Store;

public interface StockManager {
    void addStock(Stock stock);
    Stock getStock(String id);

    Stock getStockByItem(Item item);
}
