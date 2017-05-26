package com.huios.metier;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Component
@Scope("prototype")
@DiscriminatorValue("GERANT")
public class Gerant extends Personne {

	@OneToMany(mappedBy="gerant")
	private Collection<Conseiller> conseillers = new ArrayList<Conseiller>();
	@OneToOne(mappedBy = "gerant")
	private Agence agence;

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Collection<Conseiller> getConseillers() {
		return conseillers;
	}

	public void setConseillers(Collection<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}

	public Gerant(Collection<Conseiller> conseillers, Agence agence) {
		super();
		this.conseillers = conseillers;
		this.agence = agence;
	}

	public Gerant(Collection<Conseiller> conseillers) {
		super();
		this.conseillers = conseillers;
	}

	public Gerant() {
		super();
	}

	@Override
	public String toString() {
		return "Gerant [conseillers=" + conseillers + "]";
	}

}
