package nod.restservice;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import nod.dao.MongoDBase;
import nod.dao.MorphiaDAO;
import nod.model.persistence.Product;

@Stateless
@LocalBean
public class DataService {
	
	public DataService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@EJB
	MorphiaDAO morphia;
	
	public void saveProductService(Product prod) {
		morphia.addProduct(prod, MongoDBase.URI_NOD);
	}

}
