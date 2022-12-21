package egf.myshop.persistence.util;

import egf.myshop.persistence.exception.ShopException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.service.ServiceRegistry;

/**
 * @author espeg
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory==null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory(){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("META-INF/hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static void close(){
        sessionFactory.close();
    }

    public static void tryConexion() throws ShopException {
        try {
            buildSessionFactory();
        }catch (Exception e){
            throw new ShopException("Error al conectar con la base de datos",0001);
        }
    }
}
