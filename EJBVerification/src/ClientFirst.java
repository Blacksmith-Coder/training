import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import nod.pak.myejb.metier.sessions.FirstBean;
import nod.pak.myejb.metier.sessions.FirstBeanRemote;



/**
 * 
 * CREATIVE COMMONS CC BY NC SA
 *
 * Cette oeuvre, création, site ou texte est sous licence Creative Commons  
 * Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International. 
 * Pour accéder à une copie de cette licence, merci de vous rendre à l'adresse suivante : 
 * http://creativecommons.org/licenses/by-nc-sa/4.0/ ou envoyez un courrier à Creative Commons, 
 * 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @author ndps@nlystdev 
 * @Version 1.0  -  1 oct. 2016
 * @Since JDK 1.8.0_101
 *
 */
public class ClientFirst {

	

	public static void main(String[] args) throws Exception {

		// Invoke Stateless Bean
		invokeStatelessBean();

	}

	/**
	 * Looks up a stateless bean and invokes on it
	 *
	 * @throws NamingException
	 */
	private static void invokeStatelessBean() throws NamingException {
		// Let's lookup the remote stateless calculator
		FirstBeanRemote remoteBean = lookupRemoteStatelessFirstBean();
		System.out.println("Obtained a remote stateless FirstBean for invocation");

		// invoke on the remote First and access method
		System.out.println(remoteBean.saidHello("Nico"));
		
		
	}

	/**
	 * Looks up and return the proxy to remote stateless FirstBean
	 * 
	 * @return
	 * @throws NamingException
	 */
	private static FirstBeanRemote lookupRemoteStatelessFirstBean() throws NamingException {

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	    jndiProperties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
//	    jndiProperties.put(Context.SECURITY_PRINCIPAL,"dev");
//	    jndiProperties.put(Context.SECURITY_CREDENTIALS, "dev");
	    
	   // This property is important for remote resolving
	    jndiProperties.put("jboss.naming.client.ejb.context", true);
	    //This propert is not important for remote resolving
	    jndiProperties.put("org.jboss.ejb.client.scoped.context", "true");
		Context context = new InitialContext(jndiProperties);

		// The app name is the application name of the deployed EJBs. This is
		// typically the ear name
		// without the .ear suffix. However, the application name could be
		// overridden in the application.xml of the
		// EJB deployment on the server.
		// Since we haven't deployed the application as a .ear, the app name for
		// us will be an empty string
		final String appName = "";

		// This is the module name of the deployed EJBs on the server. This is
		// typically the jar name of the
		// EJB deployment, without the .jar suffix, but can be overridden via
		// the ejb-jar.xml
		// In this example, we have deployed the EJBs in a
		// jboss-as-ejb-remote-app.jar, so the module name is
		// jboss-as-ejb-remote-app
		final String moduleName = "EssaiEjb";

		// AS7 allows each deployment to have an (optional) distinct name. We
		// haven't specified a distinct name for
		// our EJB deployment, so this is an empty string
		final String distinctName = "";

		// The EJB name which by default is the simple class name of the bean
		// implementation class
		final String beanName = FirstBean.class.getSimpleName();

		// the remote view fully qualified class name
		final String viewClassName = FirstBeanRemote.class.getName();

		// let's do the lookup
		System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (FirstBeanRemote) context.lookup("java:/EssaiEjb//FirstBean!nod.pak.myejb.metier.sessions.FirstBeanRemote");
	}

}


