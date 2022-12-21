package egf.myshop.persistence.dao;

import egf.myshop.persistence.entity.Article;
import egf.myshop.persistence.entity.Client;

/**
 * @author espeg
 */
public interface ArticleDAO {
    Article findByName(String name) throws Exception;

    void modifyStock(Article article, int stock);

    void modifyPrice(Article article, double price);


}
