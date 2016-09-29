package nod.restservice;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import nod.model.persistence.Product;



/**
 * 
 * @author Copyright AQUILA NCM7 
 * @Since 2016 
 *
 */
@Stateless
@LocalBean
@Path("/hello")
public class NicoResource {
	
	public static final Logger LOGGER = Logger.getLogger(NicoResource.class.getCanonicalName());
	
	/**
	 * Default Constructor
	 */
	public NicoResource() {}
	
	@EJB
	HelloService hello;
	
	@EJB
	DataService dataservice;
	
/**
 * For conventionnal web service REST
 */
//	public NicoResource(List<AsyncResponse> listeners)
//	   {
//	      this.listeners = listeners;
//	   }
//
//	List<AsyncResponse> listeners;

	/**
	 * Method for produce a Response Hello World
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/text")
	public String getHelloWorld() {
		return hello.helloWorld();
	}
		
	
	


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/postjson")
	public Response postproductjson(Product prod) throws JsonProcessingException {
		LOGGER.info(prod.getName() + " - json ok");
		dataservice.saveProductService(prod);
		prod.setName("MODIFIED2");
		return Response.status(201).entity(prod).build();
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_XML)
//	@Produces(MediaType.APPLICATION_XML)
//	@Path("/postxml")
//	public Product postproductxml(Product prod) {
//		LOGGER.info(prod.getName() + " - " + prod.getPrice());
//		return prod;
//	}
	
}