package egf.myshop.persistence.service;

import egf.myshop.persistence.dao.*;
import egf.myshop.persistence.entity.*;
import egf.myshop.persistence.entity.Category;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
            throw new ShopException("La categoria a eliminar no existe",0013);
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

    public void listaArtCat(Long id) throws ShopException{
        try{
            genericDAO.findById(id);
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
        }catch (Exception ex){
            throw new ShopException("El id elegido no se encuentra",0031);
        }
    }

    /*Articulos*/
    //Creacion, modificacion, guardado y eliminación
    //creacion
    public Article findByNameArt(String name) throws Exception {
        return articleDAO.findByName(name);
    }

    public Optional<Category> findByIdArt(Long id) {
        return genericDAO.findById(id);
    }
    public void crearArt(Article article) throws ShopException {
        try {
            if (findByName(article.getName()) == null) {
                genericDAOA.create(article);
            }
        } catch (Exception e) {
            throw new ShopException("Error al crear el articulo",0022);
        }
    }
    //eliminacion
    public void eliminarArt(long articulo) throws ShopException {
        try {
            if (!genericDAOA.findById(articulo).isEmpty()) {
                genericDAOA.deleteById(articulo);
            }
        } catch (Exception e) {
            throw new ShopException("El articulo a eliminar no existe",0023);
        }
    }

    public void mostrarTodosArt(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Article> query = session.createQuery("from Article", Article.class);

        Stream<Article> articleStream = query.getResultList().stream();//Solo para filtrar
        articleStream.forEach(System.out::println);
    }

    public void listaClientes(Long id) throws ShopException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
            Root<Client> root = criteria.from(Client.class);
            Join<Client, Article> join = root.join(Client_.articlesList);
            criteria.select(root)
                    .where(
                            builder.equal(join, session.find(Article.class, id))
                    );
            List<Client> chapters = session.createQuery(criteria).getResultList();
            chapters.forEach(System.out::println);
        }catch (Exception e){
            throw new ShopException("El articulo buscado no existe",0033);
        }

    }
}
