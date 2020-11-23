package proyecto.tic.services.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 25)
    private String name;
    @Column(length = 100)
    private String direccion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "STORE_ADMIN",
            joinColumns = @JoinColumn(name="STORE_ID"),
            inverseJoinColumns = @JoinColumn(name="ADMIN_ID")
    )
    private Admin admin;



    public Store(String name, String direccion, Admin admin) {
        this.name = name;
        this.direccion = direccion;
        this.admin = admin;
    }

    public Store() {

    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



}
