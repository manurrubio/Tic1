package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.StoreRepository;
import proyecto.tic.services.entities.Store;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.StoreAlreadyExists;
import proyecto.tic.services.exceptions.StoreNotExist;
import proyecto.tic.services.rmi.StoreManager;

@Service
public class StoreService implements StoreManager {

    @Autowired
    private StoreRepository storeRepository;

    public StoreRepository getRepository(){
        return storeRepository;
    }

    @Override
    public void addStore(Store store) throws InvalidInformation, StoreAlreadyExists {

        if(store.getName() == null || "".equals(store.getName()) || store.getDireccion() == null || "".equals(store.getDireccion())){
            throw new InvalidInformation();
        }

        if(storeRepository.findOneByName(store.getName()) != null){
            throw new StoreAlreadyExists();
        }

        storeRepository.save(store);

    }

    @Override
    public Store getStore(String name) {
        Store store = storeRepository.findOneByName(name);
        return store;
    }

    @Override
    public void deleteStore(String name) {
        Store store = storeRepository.findOneByName(name);
        storeRepository.deleteById(store.getId());
    }

    @Override
    public void updateStore(Store store) {
        Store storeExistente = storeRepository.findOneByName(store.getName());
        storeExistente.setName(store.getName());
        storeExistente.setDireccion(store.getDireccion());
        storeRepository.save(storeExistente);
    }
}
