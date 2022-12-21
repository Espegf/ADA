package egf.myshop.persistence.dao;

import egf.myshop.persistence.entity.Article;
import egf.myshop.persistence.entity.Article_;
import egf.myshop.persistence.entity.Category;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

/**
 * @author espeg
 */
public class CategoryDAOImpl extends GenericDAOImpl<Category> implements CategoryDAO{
    public CategoryDAOImpl() {
        super(Category.class);
    }

    @Override
    public Category findByName(String name) throws Exception {
        return null;
    }

    @Override
    public List<Article> findByType(long type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);
        Join<Article, Category> join = root.join(Article_.category);
        criteria.select(root)
                .where(
                        builder.equal(join, session.find(Category.class, type))
                );
        List<Article> articles = session.createQuery(criteria).getResultList();
        return articles;
    }
}
