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
    private Long store;
    @Column
    private Long cantidad;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "STOCK_ITEM",
            joinColumns = @JoinColumn(name="STOCK_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ID")
    )
    private Item item;

    public Stock() {
    }

    public Stock(String id,String color, String talle, Long store, Long cantidad, Item item) {
        this.id = id;
        this.cantidad = cantidad;
        this.color = color;
        this.talle = talle;
        this.store = store;
        this.item=item;
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


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
