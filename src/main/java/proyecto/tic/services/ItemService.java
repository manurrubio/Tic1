package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import proyecto.tic.persistence.ItemRepository;
import proyecto.tic.services.entities.Item;
import proyecto.tic.services.entities.ItemSpecification;
import proyecto.tic.services.rmi.ItemManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService implements ItemManager {

    @Autowired
    private ItemRepository itemRepository;

    public ItemRepository getRepository() {
        return itemRepository;
    }


    @Transactional
    public List<Item> findProducts(@RequestParam(name = "type", required = false) String type,
                                   @RequestParam(name = "color", required = false) String color,
                                   @RequestParam(name = "size", required = false) String size,
                                   @RequestParam(name = "style", required = false) String style,
                                   @RequestParam(name = "category", required = false) String category,
                                   @RequestParam(name= "brand", required = false) String brand) {
        /*
        if(type!=null ||color !=null ||size!=null || style!=null || category!=null || brand!=null){
            return itemRepository.findAll(type,color,size,style,category,brand)


            )
        }

         */
            List<Item> result = new ArrayList<>();
            Iterator<Item> itemIterator = itemRepository.findAll().iterator();
            while (itemIterator.hasNext())
                result.add(itemIterator.next());
            return result;
        }


        // Ver que funciones hacemos para esto ademas de las de add delete y update del admin , como las de filtrado en el itemRepository
}