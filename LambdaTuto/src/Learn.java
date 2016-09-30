import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.log4j.Logger;

/**
 * 
 * CREATIVE COMMONS CC BY NC SA
 *
 * Cette oeuvre, cr�ation, site ou texte est sous licence Creative Commons  
 * Attribution - Pas d�Utilisation Commerciale - Partage dans les M�mes Conditions 4.0 International. 
 * Pour acc�der � une copie de cette licence, merci de vous rendre � l'adresse suivante : 
 * http://creativecommons.org/licenses/by-nc-sa/4.0/ ou envoyez un courrier � Creative Commons, 
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
		 * Lambda sans param�tre :
		 */
		Runnable monTraitement = () -> LOG.info("traitement");
		monTraitement.run();

		/*
		 * Lambda avec 1 param�tre : Bon, l� l'exemple est trivial est inutile
		 * cependant : 
		 * - Il n'est pas possible d'omettre les parenth�ses si le
		 * type est explicite (String param) 
		 * - Si plusieurs param�tres = parenth�ses obligatoires et s�par� par virgules.
		 */
		Consumer<String> logguer = (param) -> LOG.info(param);
		logguer.accept("test");

		/*
		 * Lambda 2 param�tres : Param�tres explicites et inf�r�s ne peuvent
		 * �tre mix�s Le cast sera n�cessairement avec un type primitif qui
		 * s'autoboxera. Si explicite on peut utiliser final et des annotations.
		 */
		BiFunction<Integer, Integer, Long> additionner = (num1, num2) -> (long) num1 + num2;
		LOG.info(additionner.apply(5, 4).toString());

		/*
		 * Les traitements d'une expression lambda peuvent �tre mis dans un bloc
		 * qui peut contenir plusieurs op�rations.
		 * Si le bloc doit retourner une valeur, emploi de return.
		 * Si conditions, toutes les branches return une valeur.
		 * La port�e des variables est la m�me que pour les classes anonymes.
		 */
		Function<Integer,Boolean> isPositif = valeur -> { return valeur >= 0; };
		LOG.info(isPositif.apply(-5));
		
		/*
		 * L'acc�s au variables du contexte englobant est contraint par final explicite
		 * ou implicite. 
		 */
//		int compteur = 0; 
//		// compteur++; <- interdit on modifie la variable dans le contexte englobant.
//		Consumer<Integer> count = () -> LOG.info(compteur);
//		count.accept(compteur);
		
		/*
		 * De m�me qu'en java standard il faut faire attention aux acc�s concurrents car 
		 * il est possible de passer en param�tre un objet mutable comme int[].
		 * Attention l'incr�mentation d'un des entiers du tableau en solution de 
		 * contournement est d�conseill�e. Utiliser AtomicXXX pour un compteur.
		 */
//		AtomicInteger compteurSafe = new AtomicInteger(0);
//		compteurSafe.incrementAndGet();
		

	}

}
