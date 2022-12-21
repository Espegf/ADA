package egf.myshop.persistence.dao;

import egf.myshop.persistence.entity.Article;
import egf.myshop.persistence.entity.Client;
import egf.myshop.persistence.entity.ClientAmp;
import egf.myshop.persistence.entity.Client_;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

/**
 * @author espeg
 */
public class ClientDAOImpl extends GenericDAOImpl<Client> implements ClientDAO {

    public ClientDAOImpl() {
        super(Client.class);
    }

    @Override
    public Client findByName(String name) throws ShopException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
        Root<Client> root = criteriaQuery.from(Client.class);

        criteriaQuery.select(root)
                .where(builder.equal(root.get(Client_.name), name));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException no){
            return null;
        }
    }
}
