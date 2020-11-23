package proyecto.tic.services.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin{
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

    public Admin(int ci, String firstName, String lastName, String email, String password, int telefono, String direccion) {
        this.ci = ci;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Admin() {
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
}
