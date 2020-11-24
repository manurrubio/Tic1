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



    public Brand(String name, byte[] brandImage) {
        this.name = name;
        this.brandImage=brandImage;
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

    public  Brand(){}

    public byte[] getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(byte[] brandImage) {
        this.brandImage = brandImage;
    }


    public boolean equals(Brand oBrand) {
        return this.name.equals(oBrand.getName());
    }
}
