package nod.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import nod.model.persistence.Product;

/**
 * Class MorphiaDAO, abstraction layer for mongoDB CRUD operations. Using
 * Morphia API
 *
 * @author Copyright AQUILA NCM7 
 * @Since 2016 
 *
 */
@Stateless
@LocalBean
public class MorphiaDAO {

	public MorphiaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Logger MORPHIA_LOG = Logger.getLogger(MorphiaDAO.class.getName());

	/**
	 * Methode of connection to the database.
	 * 
	 * @param uri
	 * @param databaseName
	 * @return Datastore database connection or create if don't exist
	 */
	public Datastore morphiaConnector(String uri, String databaseName) {
		Morphia morphia = new Morphia();
		morphia.mapPackage("nod.model.persistence");
		final Datastore datastore = morphia.createDatastore(MongoDBase.getMongoClient(uri), databaseName);
		datastore.ensureIndexes();
		MORPHIA_LOG.info("Datastore created or already exist");
		return datastore;
	}

	/**
	 * Method for add a object defined with persistence annotations of Morphia
	 * 
	 * @param prod
	 * @param uri
	 */
	public void addProduct(Product prod, String uri) {
		String databaseName = "myNodBase";
		morphiaConnector(uri, databaseName).save(prod);
		MORPHIA_LOG.info("Object saved within database");
	}

}
