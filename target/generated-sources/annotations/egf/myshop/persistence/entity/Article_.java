package egf.myshop.persistence.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Article.class)
public abstract class Article_ {

	public static volatile SingularAttribute<Article, Double> price;
	public static volatile SingularAttribute<Article, String> name;
	public static volatile SingularAttribute<Article, Long> id;
	public static volatile ListAttribute<Article, Client> clientList;
	public static volatile SingularAttribute<Article, Integer> stock;
	public static volatile SingularAttribute<Article, Category> category;

	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String CLIENT_LIST = "clientList";
	public static final String STOCK = "stock";
	public static final String CATEGORY = "category";

}

