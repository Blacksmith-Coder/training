package nod.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import nod.model.persistence.Product;

/**
 * @author ndps@nlystdev 
 * @Since 2016
 * 
 * Cette oeuvre, création, site ou texte est sous licence Creative Commons  
 * Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International. 
 * Pour accéder à une copie de cette licence, merci de vous rendre à l'adresse suivante :  
 * http://creativecommons.org/licenses/by-nc-sa/4.0/ ou envoyez un courrier à Creative Commons, 
 * 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 */

/**
 * Class MorphiaDAO, abstraction layer for mongoDB CRUD operations. Using
 * Morphia API
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
