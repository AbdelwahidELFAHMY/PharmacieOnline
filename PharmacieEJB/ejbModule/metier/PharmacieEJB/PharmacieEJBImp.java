package metier.PharmacieEJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import metier.entities.Pharmacie;

@Stateless
public class PharmacieEJBImp implements IPharmacieLocal {

    @PersistenceContext
    private EntityManager em;

@Override
	public void addPharmacie(Pharmacie pharmacie) {
		 if (pharmacie == null) {
	            throw new IllegalArgumentException("La pharmacie ne peut pas être null");
	        }
	        try {
	            em.persist(pharmacie);
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de l'ajout de la pharmacie : " + pharmacie.getNom(), e);
	        }
		
	}



@Override
public boolean updatePharmacie(Pharmacie pharmacie) {
    if (pharmacie == null) {
        throw new IllegalArgumentException("La pharmacie ne peut pas être null");
    }

    try {
        // Récupérer l'ancienne pharmacie
        Pharmacie anciennePharmacie = em.find(Pharmacie.class, pharmacie.getIdPharmacie());
        if (anciennePharmacie == null) {
            throw new IllegalArgumentException("Pharmacie non trouvée avec l'ID : " + pharmacie.getIdPharmacie());
        }

        // Vérifier si le statut a changé
        if (anciennePharmacie.isActive() != pharmacie.isActive()) {
            if(pharmacie.isActive()) {
            anciennePharmacie.getMesPharmaciens().forEach(pharmacien -> {
                pharmacien.setStatus("actif");
                em.merge(pharmacien); 
            });
            }else {
            	anciennePharmacie.getMesPharmaciens().forEach(pharmacien -> {
                    pharmacien.setStatus("Inactif");
                    em.merge(pharmacien); 
                });
            }
        }

        // Mettre à jour la pharmacie
        em.merge(pharmacie);
        return true;
    } catch (Exception e) {
        throw new RuntimeException("Erreur lors de la modification de la pharmacie : " + pharmacie.getNom(), e);
    }
}



    @Override
    public Pharmacie findPharmacieById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la pharmacie ne peut pas être null");
        }
        try {
            Pharmacie pharmacie = em.find(Pharmacie.class, id);
            if (pharmacie == null) {
                throw new RuntimeException("Pharmacie introuvable avec l'ID : " + id);
            }
            return pharmacie;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la pharmacie avec l'ID : " + id, e);
        }
    }

    

    @Override
    public List<Pharmacie> getPharmacies() {
        try {
            TypedQuery<Pharmacie> query = em.createQuery("SELECT p FROM Pharmacie p WHERE p.isActive = true", Pharmacie.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des pharmacies", e);
        }
    }

    
    @Override
    public long getActivePharmacies() {
        String jpql = "SELECT COUNT(p) FROM Pharmacie p WHERE p.isActive = true"; // Correct field reference
        Query query = em.createQuery(jpql);
        return (long) query.getSingleResult();
    }

	@Override
	public long getTotalPharmacies() {
	    String jpql = "SELECT COUNT(p) FROM Pharmacie p";
	    Query query = em.createQuery(jpql);
	    return (long) query.getSingleResult();
	}


	@Override
	public boolean deletePharmacie(Long id) {
	    try {
	        Pharmacie pharmacie = em.find(Pharmacie.class, id); 
	        if (pharmacie != null) {
	            em.remove(pharmacie); 
	            return true; 
	        }
	        return false; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
