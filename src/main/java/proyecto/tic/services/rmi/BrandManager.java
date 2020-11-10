package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.exceptions.BrandAlreadyExists;
import proyecto.tic.services.exceptions.BrandNotExist;
import proyecto.tic.services.exceptions.InvalidInformation;

public interface BrandManager {
    void addBrand(Brand brand) throws InvalidInformation, BrandAlreadyExists;
    Brand getBrand(String name) throws BrandNotExist;
    void updateBrand(Brand brand);
}
