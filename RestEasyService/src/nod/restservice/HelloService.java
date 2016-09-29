package nod.restservice;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class HelloService {

	/**
	 * Default constructor
	 */
	public HelloService() {}

	public String helloWorld() {
		return "Hello from HelloService EJB3 module !";
	}

}
