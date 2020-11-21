package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Item;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.ItemAlreadyExists;

public interface ItemManager {
    void addItem(Item item) throws InvalidInformation, ItemAlreadyExists;
    // utlizar cosas del itemRepository para pensar las funciones de filtrado.
}
