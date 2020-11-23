package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.StockRepository;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.Stock;
import proyecto.tic.services.rmi.StockManager;

import java.util.List;

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
    public List<Stock> getStockByItem(Item item){ return stockRepository.findOneByItem(item);}

    @Override
    public void buyStock(String id, Long cantidad){
        Stock stock= getStock(id);
        stock.setCantidad(stock.getCantidad() - cantidad);
    }
}
