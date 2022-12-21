package egf.myshop.persistence.service;

import egf.myshop.persistence.dao.*;
import egf.myshop.persistence.entity.*;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.print.Book;
import java.util.List;

/**
 * @author espeg
 */
public class PersonalDataService {
        private final GenericDAOImpl<Client> genericDAO;
        private final GenericDAOImpl<ClientAmp> genericDAOCA;
        private final ClientDAO clientDAO;

    public PersonalDataService() {
            this.genericDAO = new GenericDAOImpl<>(Client.class);
            this.genericDAOCA = new GenericDAOImpl<>(ClientAmp.class);
            this.clientDAO = new ClientDAOImpl();

        }

    //buscar
    public Client findByName(String name) throws ShopException {
        try {
            return clientDAO.findByName(name);
        } catch (Exception e) {
            throw new ShopException("Error al buscar el nombre del cliente",0005);
        }
    }

    public Object findById(Long id){
        if (id > 0){
          return genericDAO.findById(id);
        }else{
            return null;
        }
    }
    //Creacion, modificacion, guardado y eliminación
    //creacion
    public void crearC(Client client) throws ShopException {
        try {
            if (findByName(client.getName()) == null) {
                genericDAO.create(client);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ShopException("Error al crear al cliente",0002);
        }
    }
    //eliminacion
    public void eliminarC(long client) throws ShopException {
        try {
            if (!genericDAO.findById(client).isEmpty()) {
                genericDAO.deleteById(client);
            }
        } catch (Exception e) {
            throw new ShopException("El cliente a eliminar no existe",0003);
        }
    }
    public void eliminarC2(Client client) throws ShopException {
        if (client.getName()==null){
            throw new ShopException("No ha insertado un cliente con nombre",0004);
        }
        try {
            if (findByName(client.getName())==null) {
                throw new ShopException("El cliente a eliminar no existe",0003);
            }else{
                genericDAO.delete(client);
            }
        } catch (Exception e) {
            throw new ShopException("El cliente a eliminar no existe",0003);
        }
    }

    /*********Listas********/

    public void mostrarClientes(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);

        List<Client> clientes = session.createQuery(criteriaQuery).getResultList();
        clientes.forEach(System.out::println);
    }

    public void mostrarClienteAmp(Long id){
        if (id != null && id>0){
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            Client pp = session.find(Client.class, id);
            System.out.println(pp.getClientAmp());
        }else{
            System.err.println("No se ha introducido un id valido");
        }
    }


    public void mostrarArticulosDeUnCliente(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
        Root<Client> root = criteria.from(Client.class);
        Join<Client, Article> join = root.join(Client_.articlesList);
        criteria.select(root)
                .where(
                        builder.equal(join, session.find(Client.class, id))
                );
        List<Client> article = session.createQuery(criteria).getResultList();
        article.forEach(System.out::println);
    }

    //Cliente amp
    //Creacion y eliminación
    //creacion
    public void crearClientAmp(ClientAmp client) throws ShopException {
        try {
            if (client != null && client.getClient()!=null){
                try {
                    genericDAO.findById(client.getClient().getId());
                    genericDAOCA.create(client);
                }catch (NoResultException ex){
                    System.err.println("El id del cliente no existe");
                }
            }else{
                System.err.println("Informacion nula");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ShopException("Error al crear al clienteAmp",0002);
        }
    }
    //eliminacion
    public void eliminarClientAmp(long client) throws ShopException {
        try {
            if (!genericDAOCA.findById(client).isEmpty()) {
                genericDAOCA.deleteById(client);
            }
        } catch (Exception e) {
            throw new ShopException("El clienteAmp a eliminar no existe",0003);
        }
    }

    /*********Listas********/

    public void mostrarClientesAmp(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ClientAmp> criteriaQuery = builder.createQuery(ClientAmp.class);
        Root<ClientAmp> root = criteriaQuery.from(ClientAmp.class);

        List<ClientAmp> clientes = session.createQuery(criteriaQuery).getResultList();
        clientes.forEach(System.out::println);
    }
}
