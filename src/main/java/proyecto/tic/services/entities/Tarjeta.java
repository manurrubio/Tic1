package proyecto.tic.services.entities;

import javax.persistence.*;

@Entity
public class Tarjeta {
    @Id
    private Long numero;
    @Column(length = 25)
    private String vencimiento;
    @Column(length = 25)
    private String nombre;
    @Column(length = 25)
    private String apellido;
    @Column(length = 25)
    private Integer cvc;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "TARJETA_USUARIO",
            joinColumns = @JoinColumn(name="TARJETA_ID"),
            inverseJoinColumns = @JoinColumn(name="USUARIO_ID")
    )
    private Usuario usuario;

    public Tarjeta(Long nTarjeta, String vencimiento, String nombre, String apellido, Integer cvc, Usuario usuario) {
        this.numero = nTarjeta;
        this.vencimiento = vencimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cvc = cvc;
        this.usuario = usuario;
    }

    public Tarjeta() {

    }

    public Long getnTarjeta() {
        return numero;
    }

    public void setnTarjeta(Long nTarjeta) {
        this.numero = nTarjeta;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
