package proyecto.tic.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyecto.tic.services.entities.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Usuario findOneByCi(int ci);
    // Usuario findOneByCiAndDiscriminator(int ci, String type); no me aparece el atributo DISCRIMINATOR EN LA BDD pero lo implemente en la tabla create.sql (porque me anda mal)
} // ademas nose si se puede el fin por discriminator tmb si es legal o que
