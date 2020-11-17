package proyecto.tic.services.entities;

import javax.persistence.*;

@Entity
public class Stock {
    @Id
    private String id;
    @Column
    private String color;
    @Column
    private String talle;
    @Column
    private String store;
    @Column
    private Long cantidad;

    public Stock() {
    }

    public Stock(String id,String color, String talle, String store, Long cantidad) {
        this.id = id;
        this.cantidad = cantidad;
        this.color = color;
        this.talle = talle;
        this.store = store;
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
