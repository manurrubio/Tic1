package proyecto.tic.services.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; //AUTOGENERADO
    @Column(length = 25)
    private String name;
    @Column(length = 25)
    private String type;
    @Column(length = 100)
    private String description;
    private int price;
    @Column(length = 25)
    private String category;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ITEM_BRAND",
            joinColumns = @JoinColumn(name="ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name="BRAND_ID")
    )
    private Brand brand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ITEM_STOCK",
            joinColumns = @JoinColumn(name="ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name="STOCK_ID")
    )
    private Stock stock;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ITEM_STORE",
            joinColumns = @JoinColumn(name="ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name="STORE_ID")
    )
    private Store store;
    @Lob
    private byte[] imagenPrincipal;
    @Lob
    private byte[] imagen1;
    @Lob
    private byte[] imagen2;
    @Lob
    private byte[] imagen3;

    public Item() {
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Item(long id, String name, String type, String description, int price, String category, Brand brand, Stock stock, Store store, byte[] imagenPrincipal, byte[] imagen1, byte[] imagen2, byte[] imagen3) {
        this.id = id;
        this.name= name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.stock = stock;
        this.store = store;
        this.imagenPrincipal = imagenPrincipal;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public byte[][] getAllImages(){
        return new byte[][]{imagenPrincipal, imagen1, imagen2, imagen3};
    }

}
