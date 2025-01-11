package metier.PharmacienEJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import metier.entities.Commande;
import metier.entities.Medicament;
import metier.entities.Pharmacien;

import java.util.List;

@Stateless
public class PharmacienEJBImp implements IPharmacienLocal {

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<Commande> getCommandesByPharmacien(Long idPharmacien) {
	    String jpql = "SELECT c FROM Commande c " +
	                  "JOIN c.pharmacie p " +
	                  "JOIN p.mesPharmaciens ph " +
	                  "WHERE ph.id_utilisateur = :idPharmacien and c.status <> :status";
	    TypedQuery<Commande> query = em.createQuery(jpql, Commande.class);
	    query.setParameter("idPharmacien", idPharmacien);
	    query.setParameter("status", "annulé");
	    return query.getResultList();
	}

	@Override
	public List<Medicament> listMedicaments(Long idPharmacien) {
		try {

			Pharmacien ph = em.find(Pharmacien.class, idPharmacien);
			
			if (ph != null) {
	            // Return the list of Medicament entities associated with the pharmacist's pharmacy
	            return ph.getPharmacie().getMesMedicaments();
	        }
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	

}
