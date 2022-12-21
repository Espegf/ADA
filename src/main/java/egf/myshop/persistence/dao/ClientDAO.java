package egf.myshop.persistence.dao;

import egf.myshop.persistence.entity.Client;
import egf.myshop.persistence.entity.ClientAmp;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;

/**
 * @author espeg
 */
public interface ClientDAO {
    Client findByName(String name) throws ShopException;
}
