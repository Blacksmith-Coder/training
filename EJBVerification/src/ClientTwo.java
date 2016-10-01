import java.util.Optional;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.jboss.logging.Logger;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Personne;
import trade.Example;
import trade.IExample;


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
public class ClientTwo extends Application {

	private static Logger LOG = Logger.getLogger(ClientTwo.class);

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Looks up a stateless bean and invokes on it
	 *
	 * @throws NamingException
	 */
	private static void invokeStatelessBean(String name) throws Exception {
		// Let's lookup the remote stateless calculator
		IExample remoteBean = null;

		try {
			remoteBean = lookupRemoteStatelessFirstBean();
		} catch (NamingException e1) {

		}

		LOG.info("Obtained a remote stateless Example for invocation");
		Personne perso = new Personne();
		perso.setName(name);
		perso.setAge(25);

		LOG.info("try to add on database : " + perso.getClass().getSimpleName() + " -> " + perso.getName());

		Personne added = remoteBean.create(perso);
		LOG.info("Object added : " + added.getClass().getSimpleName() + " -> " + added.getName() + " with id : "
				+ added.getId());

	}

	/**
	 * Looks up and return the proxy to remote stateless FirstBean
	 * 
	 * @return
	 * @throws NamingException
	 */
	private static IExample lookupRemoteStatelessFirstBean() throws NamingException {

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		// jndiProperties.put(Context.SECURITY_PRINCIPAL,"dev");
		// jndiProperties.put(Context.SECURITY_CREDENTIALS, "dev");

		// This property is important for remote resolving
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		// This propert is not important for remote resolving
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
		final String moduleName = "JPAExample";

		// AS7 allows each deployment to have an (optional) distinct name. We
		// haven't specified a distinct name for
		// our EJB deployment, so this is an empty string
		final String distinctName = "";

		// The EJB name which by default is the simple class name of the bean
		// implementation class
		final String beanName = Example.class.getSimpleName();

		// the remote view fully qualified class name
		final String viewClassName = IExample.class.getName();

		// let's do the lookup
		LOG.info("java:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (IExample) context.lookup("java:/JPAExample//Example!trade.IExample");
	}

	/**
	 * Pour la demo dans la même classe mais décomposer en MVC pour un projet.
	 */
	@Override
	public void start(Stage primaryStage) {

		// Java FX Dialog quickly implementation...
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Identity persistence");
		dialog.setHeaderText("Quel est votre nom");
		dialog.setContentText("Please enter your name:");

		Optional<String> result = dialog.showAndWait();

		// Invoke Stateless Bean with java lambda expression
		result.ifPresent(name -> {
			try {
				invokeStatelessBean(name);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Database Updated !");
				alert.setContentText("Le nom à été corectement rentré en base de donnée!");
				alert.showAndWait();
			} catch (Exception e) {
				LOG.info("Désolé insertion impossible --> doublon");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur de persistence");
				alert.setHeaderText("Un problème est survenue en bdd");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		});

	}
}
