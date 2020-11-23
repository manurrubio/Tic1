package proyecto.tic.services.rmi;

import proyecto.tic.services.entities.Tarjeta;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.TarjetaAlreadyExists;

public interface TarjetaManager {
    Tarjeta getTarjeta(Long numero);
    void addTarjeta(Tarjeta tarjeta) throws InvalidInformation, TarjetaAlreadyExists;
}
