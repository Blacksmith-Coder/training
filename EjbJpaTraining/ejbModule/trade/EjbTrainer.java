package trade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import model.EtatCivil;

/**
 * Session Bean implementation class EjbTrainer
 *
 * @author Copyright AQUILA NCM7 
 * @Since 2016 
 *
 */
@Stateless
public class EjbTrainer implements EjbTrainerRemote, EjbTrainerLocal {
	
	private static Logger LOGGER = Logger.getLogger(EjbTrainer.class);

    /**
     * Default constructor. 
     */
    public EjbTrainer() {
       
    }
    
    @PersistenceContext(unitName = "EjbJpaTrainingUnit")
    EntityManager em;

	@Override
	public EtatCivil createEntryEtatCivil(EtatCivil ec) {
		LOGGER.info("Try to persist EtatCivil" + ec.getName());
		em.persist(ec);
		LOGGER.info(ec.getName() + " persitence done.");
		return ec;
	}

}
