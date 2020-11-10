package proyecto.tic.services.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Usuario{

    public Client(int ci, String firstName, String lastName, String email, String password, int telefono, String direccion) {
        super(ci, firstName, lastName, email, password, telefono, direccion);
    }

    public Client() {

    }
}
