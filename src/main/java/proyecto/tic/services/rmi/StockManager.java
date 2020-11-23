package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;

import java.util.List;

public interface StockManager {
    void addStock(Stock stock);
    Stock getStock(String id);

    List<Stock> getStockByItem(Item item);
    List<Stock> getStockByColor(String color);
    List<Stock> getStockByTalle(String talle);

    void buyStock(String id, Long cantidad);
}
