import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.JButton;

import org.apache.log4j.Logger;

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
 * @Version 1.0  -  30 sept. 2016
 * @Since JDK 1.8.0_101
 *
 */
public class Lambda {

	/**
	 * Logger log4j
	 * 
	 * @see resources/log4j.properties
	 */
	private static final Logger LOG = Logger.getLogger(Lambda.class);

	private static ArrayList<String> list = new ArrayList<String>();

	static {
		list.add("un");
		list.add("deux");
		list.add("trois");
	};

	public static void main(String[] args) {

		
		/*
		 * Style traditionnel :
		 */
		for (int i = 0; i < list.size(); i++) {
			LOG.info(list.get(i));
		}

		
		/*
		 * Lamdbda : style fonctionnel :
		 */
		list.forEach(LOG::info);

		
		/*
		 * Auparavant on utilisait les classes anonymes internes :
		 */
		Thread myThread = new Thread(new Runnable() {
			@Override
			public void run() {
				LOG.info("Dans le thread tradi");
			}
		});
		myThread.start();

		
		/*
		 * De nos jours on peut faire du Lambda :
		 */
		Thread lambdaThread = new Thread(() -> {
			LOG.info("Dans le thread Lambda");
		});
		lambdaThread.start();

		
		/**
		 * Lambda sans paramètre :
		 */
		Runnable monTraitement = () -> LOG.info("traitement");
		monTraitement.run();

		
		/*
		 * Lambda avec 1 paramètre : Bon, là l'exemple est trivial est inutile
		 * cependant : 
		 * - Il n'est pas possible d'omettre les parenthèses si le
		 * type est explicite (String param) 
		 * - Si plusieurs paramètres = parenthèses obligatoires et séparé par virgules.
		 */
		Consumer<String> logguer = (param) -> LOG.info(param);
		logguer.accept("test");

		
		/*
		 * Lambda 2 paramètres : Paramètres explicites et inférés ne peuvent
		 * être mixés Le cast sera nécessairement avec un type primitif qui
		 * s'autoboxera. Si explicite on peut utiliser final et des annotations.
		 */
		BiFunction<Integer, Integer, Long> additionner = (num1, num2) -> (long) num1 + num2;
		LOG.info(additionner.apply(5, 4).toString());

		
		/*
		 * Les traitements d'une expression lambda peuvent être mis dans un bloc
		 * qui peut contenir plusieurs opérations.
		 * Si le bloc doit retourner une valeur, emploi de return.
		 * Si conditions, toutes les branches return une valeur.
		 * La portée des variables est la même que pour les classes anonymes.
		 */
		Function<Integer,Boolean> isPositif = valeur -> { return valeur >= 0; };
		LOG.info(isPositif.apply(-5));
		
		
		/*
		 * L'accés au variables du contexte englobant est contraint par final explicite
		 * ou implicite. 
		 * De même qu'en java standard il faut faire attention aux accés concurrents car 
		 * il est possible de passer en paramètre un objet mutable comme int[].
		 * Attention l'incrémentation d'un des entiers du tableau en solution de 
		 * contournement est déconseillée. Utiliser AtomicXXX pour un compteur.
		 */
		JButton button = new JButton("Incrémenter");
		//int compteur = 0; <- interdit on modifie la variable dans le contexte englobant !
		//compteur++; <- même problèmatique !
		// Solution propre :
		AtomicInteger compteur = new AtomicInteger(0);
		button.addActionListener(event -> compteur.incrementAndGet());

		
		/*
		 * Egalement une expression lambda ne définit pas de nouvelle portée
		 * l'exemple ci dessous ne compilera pas.
		 * Dans le même esprit le mot clef this fera référence à l'instance courante dans
		 * le contexte englobant ET dans l'expression lambda.
		 */
		
		// String p1 = new String("chaine1");
		// String p2 = new String("chaine2");
		// Comparator<String> triParNom = (String p1, String p2) -> {
 		//	  return p2.compareTo(p1);
		// };
		 
		
		/*
		 * Définition d'une variable qui à pour type 
		 * une interface fonctionnelle.
		 * L'instance qui implémente l'interface est donc un Objet.
		 */
		Runnable myRun = () -> {
			LOG.info("run run");
		};
		
		Object obj = myRun;
		Runnable thread = (Runnable) obj;
		thread.run();
		
		
		
	}

}
