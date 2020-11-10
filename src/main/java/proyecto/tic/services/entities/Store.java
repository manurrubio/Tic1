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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BRAND_STORE",
            joinColumns = @JoinColumn(name="STORE_ID"),
            inverseJoinColumns = @JoinColumn(name="BRAND_ID")
    )
    private Set<Brand> brands;


    public Store(String name, String direccion) {
        this.name = name;
        this.direccion = direccion;
        this.brands = new HashSet<>();
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

    public Set<Brand> getBrands() {
        return brands;
    }

    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }

    public Store() {
        this.brands = new HashSet<>();
    }

}
