import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.EtatCivil;
import trade.EjbTrainer;
import trade.EjbTrainerLocal;

public class EjbTest {
	
	private static Logger LOGTEST = Logger.getLogger("Test logger - ");

	private static EJBContainer ejbC;
	private static Context ctx;
    private static EjbTrainerLocal ejbTrainInterface;

   /**
    * Initialisation d'un container
    * @throws NamingException
    */
    @BeforeClass
    public static void setUpClass() throws Exception {
        ejbC = EJBContainer.createEJBContainer();
        ctx = ejbC.getContext();

        String ejbTrainerName = EjbTrainer.class.getSimpleName();
        String IEjbInterface = EjbTrainerLocal.class.getName();
        
        LOGTEST.info("java:/"+ejbTrainerName+"!"+IEjbInterface);
        ejbTrainInterface = (EjbTrainerLocal) ctx.lookup("java:/"+ejbTrainerName+"!"+IEjbInterface);
        LOGTEST.info("... Done...");
    }

	@Test
	public void test() {
		
		EtatCivil ec = new EtatCivil();
		ec.setName("Nod");
		ec.setAge(25);
		ejbTrainInterface.createEntryEtatCivil(ec);
	}
	
	@AfterClass
    public static void tearDownClass() throws Exception {
        ejbC.close();
    }


}
