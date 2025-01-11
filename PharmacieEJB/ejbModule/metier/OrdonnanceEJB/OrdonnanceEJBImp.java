package metier.OrdonnanceEJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.Ordonnance;

@Stateless
public class OrdonnanceEJBImp implements IOrdonnanceLocal {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void addOrdonnance(Ordonnance newOrdo) {
		if (newOrdo == null) {
            throw new IllegalArgumentException("L'Ordonnace ne peut pas être null");
        }
        try {
            em.persist(newOrdo);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'Ordonnance : ", e);
        }
		
	}
	

}
