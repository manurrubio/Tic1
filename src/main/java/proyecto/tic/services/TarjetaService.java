package proyecto.tic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.tic.persistence.TarjetaRepository;
import proyecto.tic.services.entities.Brand;
import proyecto.tic.services.entities.Tarjeta;
import proyecto.tic.services.exceptions.BrandAlreadyExists;
import proyecto.tic.services.exceptions.InvalidInformation;
import proyecto.tic.services.exceptions.TarjetaAlreadyExists;
import proyecto.tic.services.rmi.TarjetaManager;

@Service
public class TarjetaService implements TarjetaManager {
    @Autowired
    private TarjetaRepository tarjetaRepository;

    /*@Override
    public Tarjeta getTarjeta(Long nTarjeta) {
        Tarjeta tarjeta= tarjetaRepository.findOneByNTarjeta(nTarjeta);
        return tarjeta;
    }*/

    @Override
    public Tarjeta getTarjeta(Long numero) {
        return tarjetaRepository.findOneByNumero(numero);
    }

    @Override
    public void addTarjeta(Tarjeta tarjeta) throws InvalidInformation, TarjetaAlreadyExists { // Funcion para el Admin para agregar Marcas
        if(tarjeta.getnTarjeta()==null||tarjeta.getNombre()==null||tarjeta.getApellido()==null||tarjeta.getCvc()==null||tarjeta.getVencimiento()==null||tarjeta.getUsuario()==null){
            throw new InvalidInformation();
        }
        if(tarjetaRepository.findOneByNumero(tarjeta.getnTarjeta()) != null){
            throw new TarjetaAlreadyExists();
        }
        tarjetaRepository.save(tarjeta);

    }

}
