package proyecto.tic.services.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {
    @Id
    private String id;
    @Column
    private Long cantidad;
    @Column
    private String color;
    @Column
    private String talle;

    public Stock() {
    }

    public Stock(String id, Long cantidad, String color, String talle) {
        this.id = id;
        this.cantidad = cantidad;
        this.color = color;
        this.talle = talle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }
}
