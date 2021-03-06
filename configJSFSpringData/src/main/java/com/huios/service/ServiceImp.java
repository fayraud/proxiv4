package com.huios.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.huios.dao.springdata.IDaoConseiller;
import com.huios.dao.springdata.IDaoAdresse;
import com.huios.dao.springdata.IDaoClient;
import com.huios.dao.springdata.IDaoCompte;
import com.huios.metier.Adresse;
import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.Conseiller;
import com.huios.metier.Personne;
import com.huios.metier.User;
import com.huios.metier.CompteCourant;
import com.huios.metier.CompteEpargne;

@Service
public class ServiceImp implements IServiceConseiller {

	@Autowired
	private IDaoClient daoP;
	@Autowired
	private IDaoAdresse daoA;
	
	@Autowired
	private IDaoConseiller daoC;
	
	
	@Autowired
	private IDaoCompte daoCo;

	@Override
	public Conseiller verificationLogin(String login, String pwd) {
		return daoC.verificationLogin(login, pwd);
	}

	@Override
	public Collection<Client> listerClientsParConseiller(Conseiller conseiller) {
		return daoP.listerClientsParConseiller(conseiller);
	}


	@Override
	public Collection<Compte> listerComptesClient(Client client) {
		return daoCo.listerComptesClient(client);
	}

	@Override
	public Client retourneClientParId(long idClient) {
		return daoP.findOne(idClient);
	}
	@Override
	public Compte getCompteParId(long idCompte) {
		return daoCo.findOne(idCompte);
	}

	@Override
	public Collection<Compte> listerAutresComptes(long idCompte) {
		return daoCo.listerAutresComptes(idCompte);
	}

	@Override
	public boolean virementComptes(Compte compteDebiteur, Compte compteCrediteur, double montant) {
		if (montant >= 0) {
			if (compteDebiteur instanceof CompteEpargne) {
				if (montant > compteDebiteur.getSolde()) {
					return false;
				}
			}
			if (compteDebiteur instanceof CompteCourant) {
				double decouvert = ((CompteCourant) compteDebiteur).getDecouvert();
				if (montant > compteDebiteur.getSolde() + decouvert) {
					return false;
				}
			}
			compteDebiteur.setSolde(compteDebiteur.getSolde() - montant);
			compteCrediteur.setSolde(compteCrediteur.getSolde() + montant);
			
			daoCo.save(compteDebiteur);
			daoCo.save(compteCrediteur);
			
			return true;
		}
		return false;
	}
		
	

	@Override
	public Conseiller afficherConseiller(long idConseiller) {
		
		return (Conseiller) daoC.findOne(idConseiller);
	}

	@Override
	public void modifierClient(Client client) {
		daoP.save(client);
		Adresse ad = client.getAdresse();
		daoA.save(ad);
	}

	@Override
	public void ajouterClient(long idConseiller, Client client) {
		Conseiller cons = daoC.findOne(idConseiller);
		Collection<Client> clients = daoP.listerClientsParConseiller(cons);
		
		//if(clients.size()>=10){
			client.setConseiller(cons);
			daoP.save(client);
			daoA.save(client.getAdresse());
			
					
		//}
			
		
		
	}
	
	
	
	
}
