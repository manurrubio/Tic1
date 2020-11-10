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
    private long id;
    @Column(length = 25)
    private String type;
    @Column(length = 25)
    private List<String> color;
    @Column(length = 25)
    private List<String> size;
    @Column(length = 25)
    private String style;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ITEM_USUARIO",
            joinColumns = @JoinColumn(name="ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name="USUARIO_ID")
    )
    private Usuario usuario;
    @Lob
    private byte[] imagenPrincipal;
    @Lob
    private byte[] imagen1;
    @Lob
    private byte[] imagen2;
    @Lob
    private byte[] imagen3;
    @Lob
    private byte[] imagen4;
    @Lob
    private byte[] imagen5;

    public Item() {
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Item(long id, String type, List<String> color, List<String> size, String style, String description, int price, String category, Brand brand, Stock stock, Store store, Usuario usuario, byte[] imagenPrincipal, byte[] imagen1, byte[] imagen2, byte[] imagen3, byte[] imagen4, byte[] imagen5) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.size = size;
        this.style = style;
        this.description = description;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.stock = stock;
        this.store = store;
        this.usuario = usuario;
        this.imagenPrincipal = imagenPrincipal;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.imagen5 = imagen5;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public byte[] getImagen4() {
        return imagen4;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public byte[] getImagen5() {
        return imagen5;
    }

    public void setImagen5(byte[] imagen5) {
        this.imagen5 = imagen5;
    }
    public byte[][] getAllImages(){
        return new byte[][]{imagenPrincipal, imagen1, imagen2, imagen3, imagen4, imagen5};
    }

}
