package trade;

import javax.ejb.Remote;

import model.EtatCivil;

@Remote
public interface EjbTrainerRemote {
	public EtatCivil createEntryEtatCivil(EtatCivil ec);
}
