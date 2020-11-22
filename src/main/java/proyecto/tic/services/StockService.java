package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.StockRepository;
import proyecto.tic.persistence.StoreRepository;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.entities.Store;
import proyecto.tic.services.rmi.StockManager;

@Service
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

    @Override
    public Stock getStockByItem(Item item){ return stockRepository.findOneByItem(item);}
}
