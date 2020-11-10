package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.BrandRepository;
import proyecto.tic.persistence.StoreRepository;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.exceptions.BrandAlreadyExists;
import proyecto.tic.services.exceptions.BrandNotExist;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.rmi.BrandManager;

@Service
public class BrandService implements BrandManager {

    @Autowired
    private BrandRepository brandRepository;

    public BrandRepository getRepository(){
        return brandRepository;
    }

    @Override
    public void addBrand(Brand brand) throws InvalidInformation, BrandAlreadyExists { // Funcion para el Admin para agregar Marcas

        if(brand.getName() == null || "".equals(brand.getName()) || brand.getBrandImage() == null){
            throw new InvalidInformation();
        }

        if(brandRepository.findOneByName(brand.getName()) != null){
            throw new BrandAlreadyExists();
        }

        brandRepository.save(brand);

    }

    @Override
    public Brand getBrand(String name) throws BrandNotExist { // Funcion por si el admin quiere obtener la marca (creo que podriamos borrar esta pero ta la deamos por las dudas)
        Brand brand  = brandRepository.findOneByName(name);
        if(brand == null){
            throw new BrandNotExist();
        }
        return brand;
    }

    @Override
    public void updateBrand(Brand brand) { // Se puede querer actualizar el icono de la marca por el ADMIN
        Brand brandExistente = brandRepository.findOneByName(brand.getName());
        brandExistente.setBrandImage(brand.getBrandImage());
        brandRepository.save(brandExistente);
    }
}
