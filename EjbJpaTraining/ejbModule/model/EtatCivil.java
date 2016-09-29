package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EtatCivil database table.
 * 
 */
@Entity
@NamedQuery(name="EtatCivil.findAll", query="SELECT e FROM EtatCivil e")
public class EtatCivil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEtatCivil;

	private int age;

	private String name;

	public EtatCivil() {
	}

	public int getIdEtatCivil() {
		return this.idEtatCivil;
	}

	public void setIdEtatCivil(int idEtatCivil) {
		this.idEtatCivil = idEtatCivil;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}