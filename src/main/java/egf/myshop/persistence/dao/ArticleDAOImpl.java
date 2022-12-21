package egf.myshop.persistence.dao;

import egf.myshop.persistence.entity.Article;
import egf.myshop.persistence.entity.Article_;
import egf.myshop.persistence.entity.Client;
import egf.myshop.persistence.entity.Client_;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

/**
 * @author espeg
 */
public class ArticleDAOImpl extends GenericDAOImpl<Article> implements ArticleDAO {


    public ArticleDAOImpl() {
        super(Article.class);
    }

    @Override
    public Article findByName(String name) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Article> criteriaQuery = builder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);

        criteriaQuery.select(root)
                .where(builder.equal(root.get(Article_.name), name));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void modifyStock(Article article, int stock) {
        article.setStock(article.getStock()+stock);
        save(article);
    }

    @Override
    public void modifyPrice(Article article, double price) {
        if (price<0){
            System.err.println("El precio no puede ser menor a 0");
        }else{
            article.setPrice(price);
            save(article);
        }
    }
}
