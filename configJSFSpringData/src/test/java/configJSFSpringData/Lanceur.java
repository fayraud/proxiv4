package configJSFSpringData;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huios.metier.Client;
import com.huios.metier.Compte;
import com.huios.metier.Conseiller;
import com.huios.service.IServiceConseiller;

public class Lanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
				//1- Chargement du container et creation des beans
				ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
				//2- récuperation d'un bean
				
				
				IServiceConseiller isc = (IServiceConseiller) appContext.getBean("serviceImp");
				
				
				
				Conseiller conseiller = (Conseiller) appContext.getBean("conseiller");
				
				conseiller = isc.afficherConseiller(6);
				
				System.out.println(isc.listerClientsParConseiller(conseiller));
				
				Client client = (Client) appContext.getBean("client");
				
				client = isc.retourneClientParId(5);
				
				
								
				System.out.println(isc.listerComptesClient(client));
				
				Compte compte1 = (Compte) appContext.getBean("compteCourant");
				Compte compte2 = (Compte) appContext.getBean("compteEpargne");
				
				compte1 = isc.getCompteParId(1);
				compte2 = isc.getCompteParId(4);
				
				isc.virementComptes(compte1, compte2, 200);
				
				
				client.setNom("NomMod");
				
				isc.modifierClient(client);
				
				
				
				//SocieteDevLogiciel societe = (SocieteDevLogiciel) appContext.getBean("societe");
				//3- traitement
				
				//4- detruire le context
				appContext.close();
		
		
		
	}

}
