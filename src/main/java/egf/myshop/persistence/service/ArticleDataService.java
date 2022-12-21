package egf.myshop.persistence.service;

import egf.myshop.persistence.dao.*;
import egf.myshop.persistence.entity.*;
import egf.myshop.persistence.entity.Category;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

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

    public Optional<Category> findByIdCat(Long id){
       return genericDAO.findById(id);
    }
    //Creacion, modificacion, guardado y eliminación
    //creacion
    public void crearCat(Category categoria) throws ShopException {
        try {
            if (findByName(categoria.getName()) == null) {
                genericDAO.create(categoria);
            }
        } catch (Exception e) {
            throw new ShopException("Error al crear al categoria",0012);
        }
    }
    //eliminacion
    public void eliminarC(long categoria) throws ShopException {
        try {
            if (!genericDAO.findById(categoria).isEmpty()) {
                genericDAO.deleteById(categoria);
            }
        } catch (Exception e) {
            throw new ShopException("El categoria a eliminar no existe",0013);
        }
    }

    public void mostrarTodasCat(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = builder.createQuery(Category.class);
        Root<Category> root = criteriaQuery.from(Category.class);

        List<Category> categories = session.createQuery(criteriaQuery).getResultList();
        categories.forEach(System.out::println);
    }

    public void listaArtCat(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);
        Join<Article, Category> join = root.join(Article_.category);
        criteria.select(root)
                .where(
                        builder.equal(join, session.find(Category.class, id))
                );
        List<Article> chapters = session.createQuery(criteria).getResultList();
        chapters.forEach(System.out::println);

        List<Article> articles = session.createQuery(criteria).getResultList();
        articles.forEach(System.out::println);
    }

}
