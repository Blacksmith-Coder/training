import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

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
public class Learn {

	/**
	 * Logger log4j
	 * 
	 * @see resources/log4j.properties
	 */
	private static final Logger LOG = Logger.getLogger(Learn.class);

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
		 */
//		int compteur = 0; 
//		// compteur++; <- interdit on modifie la variable dans le contexte englobant.
//		Consumer<Integer> count = () -> LOG.info(compteur);
//		count.accept(compteur);
		
		/*
		 * De même qu'en java standard il faut faire attention aux accés concurrents car 
		 * il est possible de passer en paramètre un objet mutable comme int[].
		 * Attention l'incrémentation d'un des entiers du tableau en solution de 
		 * contournement est déconseillée. Utiliser AtomicXXX pour un compteur.
		 */
//		AtomicInteger compteurSafe = new AtomicInteger(0);
//		compteurSafe.incrementAndGet();
		

	}

}
