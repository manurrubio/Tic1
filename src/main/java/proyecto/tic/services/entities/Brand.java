package proyecto.tic.services.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 25)
    private String name;
    @Lob
    private byte[] brandImage;

    @ManyToMany(mappedBy = "brands")
    private Set<Store> stores;

    public Brand(String name, byte[] brandImage) {
        this.name = name;
        this.brandImage=brandImage;
        this.stores = new HashSet<>();
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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    public Brand() {
        this.stores = new HashSet<>();
    }

    public byte[] getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(byte[] brandImage) {
        this.brandImage = brandImage;
    }
}
