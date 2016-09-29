package trade;

import javax.ejb.Local;

import model.EtatCivil;

@Local
public interface EjbTrainerLocal {
	public EtatCivil createEntryEtatCivil(EtatCivil ec);

}
