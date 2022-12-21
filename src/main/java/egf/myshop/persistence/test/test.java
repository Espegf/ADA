package egf.myshop.persistence.test;

import egf.myshop.persistence.entity.*;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import javax.script.ScriptEngine;
import java.awt.print.Book;
import java.util.List;

/**
 * @author espeg
 */
public class test {
    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
/*
                System.out.println("Todos articulos");
                CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
                Root<Article> root = criteriaQuery.from(Article.class);
                criteriaQuery.select(root); //select all
                List<Article> articulos = session.createQuery(criteriaQuery)
                        .getResultList();
                articulos.forEach(System.out::println);

                System.out.println("Todos clientes");
                CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
                Root<Client> root = criteriaQuery.from(Client.class);
                criteriaQuery.select(root); //select all
                List<Client> clientes = session.createQuery(criteriaQuery)
                        .getResultList();
                clientes.forEach(System.out::println);

                System.out.println("Todos clientesamp");
                CriteriaQuery<ClientAmp> criteriaQuery = criteriaBuilder.createQuery(ClientAmp.class);
                Root<ClientAmp> root = criteriaQuery.from(ClientAmp.class);
                criteriaQuery.select(root); //select all
                List<ClientAmp> clientesAmp = session.createQuery(criteriaQuery)
                        .getResultList();
                clientesAmp.forEach(System.out::println);


                System.out.println("Todas categorias");
                CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
                Root<Category> root = criteriaQuery.from(Category.class);
                criteriaQuery.select(root); //select all
                List<Category> categories = session.createQuery(criteriaQuery)
                        .getResultList();
                categories.forEach(System.out::println);



                System.out.println("Todos articulos 1 categoria");
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
                Root<Article> root = criteria.from(Article.class);
                Join<Article, Category> join = root.join(Article_.category);
                criteria.select(root)
                        .where(
                                builder.equal(join, session.find(Category.class, 1))
                        );
                List<Article> articles = session.createQuery(criteria).getResultList();
                articles.forEach(System.out::println);
*/


                CriteriaQuery<Article> criteria = criteriaBuilder.createQuery(Article.class);
                Root<Article> root = criteria.from(Article.class);
                Join<Article, Client> join = root.join(Article_.clientList);
                criteria.select(root)
                        .where(
                                criteriaBuilder.equal(join, session.find(Article.class, 3))
                        );
                List<Article> article = session.createQuery(criteria).getResultList();
                article.forEach(System.out::println);
            } catch (RuntimeException ex) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.out.println(ex.getMessage());
                    System.out.println("Falla");
                }
            }
        }
    }
}
