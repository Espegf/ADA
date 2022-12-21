package egf.myshop.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author espeg
 */
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "clientList")
    private List<Article> articlesList = new ArrayList<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.REMOVE)
    private ClientAmp clientAmp;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientAmp getClientAmp() {
        return clientAmp;
    }

    public void setClientAmp(ClientAmp clientAmp) {
        this.clientAmp = clientAmp;
    }

    public List<Article> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<Article> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public String toString() {
        return "Client: " + id +
                ", name= " + name;
    }
}
