package metier.CommandeEJB;

import java.util.List;

import javax.ejb.Local;
import metier.entities.Commande;

@Local
public interface ICommandeLocal {

	void addCommande(Commande cmd);
	public boolean deleteCommande(Long idCommande);
	void updateCommande(Commande cmd);
    List<Commande> getCommandes();
    void sendEmailNotificationToPharmaciens(String to, String subject, String body);
    void sendEmailNotificationToPatient(String to, String subject, String body);
	public List<Commande> listCommandesNonTraites(Long idPatient);
	boolean annulerCommande(Long idCommande);
	public List<Commande> listCommandesTraites(Long idPatient);
	public Commande getCommandeDetails(Long idCommande);
	public long getTotalOrders();
	
}
