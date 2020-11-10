package proyecto.tic.services.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario{

    public Admin(int ci, String firstName, String lastName, String email, String password, int telefono, String direccion) {
        super(ci, firstName, lastName, email, password, telefono, direccion);
    }

    public Admin() {

    }
}
