package egf.myshop.persistence.entity;

import jakarta.persistence.*;

/**
 * @author espeg
 */
@Entity
@Table(name = "client_amp")
public class ClientAmp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(name = "number_phone",nullable = false)
    private String numberPhone;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "client_id_client")
    private Client client;

    public ClientAmp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientAmp: " + id +
                ", address= " + address +
                ", numberPhone= " + numberPhone +
                ", fullName= " + fullName;
    }
}
