package egf.myshop.persistence.service;

import egf.myshop.persistence.dao.*;
import egf.myshop.persistence.entity.*;
import egf.myshop.persistence.entity.Category;
import egf.myshop.persistence.exception.ShopException;

/**
 * @author espeg
 */
public class ArticleDataService {
    private final GenericDAOImpl<Category> genericDAO;
    private final GenericDAOImpl<Article> genericDAOA;
    private final CategoryDAO categoryDAO;
    private final ArticleDAO articleDAO;

    public ArticleDataService() {
        genericDAO = new GenericDAOImpl<>(Category.class);
        this.genericDAOA = new GenericDAOImpl<>(Article.class);
        this.categoryDAO = new CategoryDAOImpl();
        this.articleDAO = new ArticleDAOImpl();
    }

    /*Categorias*/

    /*Buscar*/
    public Category findByName(String name) throws Exception {
        return categoryDAO.findByName(name);
    }

    //Creacion, modificacion, guardado y eliminación
    //creacion
    public void crearCat(Category categoria) throws ShopException {
        try {
            if (findByName(categoria.getName()) == null) {
                genericDAO.create(categoria);
            }
        } catch (Exception e) {
            throw new ShopException("Error al crear al categoria",0002);
        }
    }
    //eliminacion
    public void eliminarC(long categoria) throws ShopException {
        try {
            if (!genericDAO.findById(categoria).isEmpty()) {
                genericDAO.deleteById(categoria);
            }
        } catch (Exception e) {
            throw new ShopException("El categoria a eliminar no existe",0003);
        }
    }

}
