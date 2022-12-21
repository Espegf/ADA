package egf.myshop.persistence.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, ClientAmp> clientAmp;
	public static volatile SingularAttribute<Client, Long> id;
	public static volatile ListAttribute<Client, Article> articlesList;

	public static final String NAME = "name";
	public static final String CLIENT_AMP = "clientAmp";
	public static final String ID = "id";
	public static final String ARTICLES_LIST = "articlesList";

}

