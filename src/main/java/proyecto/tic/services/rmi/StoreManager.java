package proyecto.tic.services.rmi;


import proyecto.tic.services.entities.Store;
import proyecto.tic.services.exceptions.ClientAlreadyExists;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.StoreAlreadyExists;
import proyecto.tic.services.exceptions.StoreNotExist;

public interface StoreManager {
    void addStore(Store store) throws InvalidInformation, StoreAlreadyExists;
    Store getStore(String name) throws StoreNotExist;
    void deleteStore(String name);
    void updateStore(Store store);
    // Nose si tendriamos que hacer funciones para tienda porque dani dijo que los clientes buscan por marcas o categorias de ropa y eso pero no por tienda.
}
