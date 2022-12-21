package egf.myshop.persistence.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClientAmp.class)
public abstract class ClientAmp_ {

	public static volatile SingularAttribute<ClientAmp, String> address;
	public static volatile SingularAttribute<ClientAmp, String> numberPhone;
	public static volatile SingularAttribute<ClientAmp, String> fullName;
	public static volatile SingularAttribute<ClientAmp, Client> client;
	public static volatile SingularAttribute<ClientAmp, Long> id;

	public static final String ADDRESS = "address";
	public static final String NUMBER_PHONE = "numberPhone";
	public static final String FULL_NAME = "fullName";
	public static final String CLIENT = "client";
	public static final String ID = "id";

}

