package proyecto.tic.services.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR",
        discriminatorType = DiscriminatorType.STRING) // Usuario no necesita Discriminatorvalue porque no vamos a tener en la bdd usuarios, solo clientes o administradores
public class Usuario {
    @Id
    private int ci;
    @Column(length = 25)
    private String firstName;
    @Column(length = 25)
    private String lastName;
    @Column(length = 100)
    private String email;
    @Column(length = 25)
    private String password;
    @Column
    private int telefono;
    @Column(length = 100)
    private String direccion;

    public Usuario(int ci, String firstName, String lastName, String email, String password, int telefono, String direccion) {
        this.ci = ci;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario() {

    }

    @Override
    public boolean equals(Object oUsuario) {
        if (oUsuario == this) {
            return true;
        }
        if (!(oUsuario instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) oUsuario;
        return ci == usuario.ci
                && firstName.equalsIgnoreCase(usuario.firstName)
                && lastName.equalsIgnoreCase(usuario.lastName)
                && email.equalsIgnoreCase(usuario.email);

    }
}