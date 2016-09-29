package nod.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

/**
 * Class MongoDBase for manage MongoDB connections 
 *
 * @author Copyright AQUILA NCM7 
 * @Since 2016 
 *
 */
public class MongoDBase {

	public MongoDBase() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * LOGGERDB using org.jboss.logging.Logger
	 */
	private static Logger LOGGERDB = Logger.getLogger(MongoDBase.class.getName());

	// constants for my home
	public static final String URI_NOD = "mongodb://192.168.0.11:27017/";

	/**
	 * Pool of instance of MongoClient
	 */
	protected static Map<String, MongoClient> poolMongoClients = new ConcurrentHashMap<String, MongoClient>();

	/**
	 * Static Method to obtain client within a synchronized pool. Two
	 * singleton's controls have been implemented.
	 * 
	 * @author Nicolas Dupuis
	 * @param uri
	 *            String
	 * @return MongoClient instance
	 */
	public static MongoClient getMongoClient(String uri) {
		if (poolMongoClients.get(uri) == null) {
			synchronized (poolMongoClients) {
				if (poolMongoClients.get(uri) == null) {
					MongoClientURI objUri = new MongoClientURI(uri);
					MongoClient cli = new MongoClient(objUri);
					poolMongoClients.put(uri, cli);
				}
			}
		}
		LOGGERDB.info("Client connector > " + uri);
		return poolMongoClients.get(uri);
	}
}
