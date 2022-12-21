package egf.myshop.persistence.dao;

import egf.myshop.persistence.entity.Article;
import egf.myshop.persistence.entity.Category;
import egf.myshop.persistence.entity.Client;

import java.util.List;

/**
 * @author espeg
 */
public interface CategoryDAO {
    Category findByName(String name) throws Exception;

    List<Article> findByType(long type);

}
