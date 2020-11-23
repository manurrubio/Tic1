package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.tic.services.entities.Tarjeta;

public interface TarjetaRepository extends CrudRepository<Tarjeta, Long> {
    Tarjeta findOneByNTarjeta(Long nTarjeta);
}
