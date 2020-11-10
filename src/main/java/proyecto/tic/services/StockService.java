package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import proyecto.tic.persistence.StockRepository;
import proyecto.tic.persistence.StoreRepository;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.entities.Store;
import proyecto.tic.services.rmi.StockManager;

public class StockService implements StockManager {

    @Autowired
    private StockRepository stockRepository;

    public StockRepository getRepository(){
        return stockRepository;
    }

    @Override
    public void addStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public Stock getStock(String id) {
        return stockRepository.findOneById(id);
    }
}
