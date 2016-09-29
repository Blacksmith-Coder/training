package nod.restservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class MyApplication extends Application {

	/**
	 * Overriding with getClasses for EJB Module
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(NicoResource.class);
		return classes;

	}

	/**
	 * Service REST Web Classique
	 */
	// private Set<Object> singletons = new HashSet<Object>();
	// private Set<Class<?>> empty = new HashSet<Class<?>>();
	//
	// public MyApplication()
	// {
	// List<AsyncResponse> listeners = new ArrayList<AsyncResponse>();
	// singletons.add(new NicoResource(listeners));
	//
	// }
	//
	// @Override
	// public Set<Class<?>> getClasses()
	// {
	// return empty;
	// }
	//
	// @Override
	// public Set<Object> getSingletons()
	// {
	// return singletons;
	// }
}