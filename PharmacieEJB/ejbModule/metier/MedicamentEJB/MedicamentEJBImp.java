package metier.MedicamentEJB;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.Medicament;

@Stateless
public class MedicamentEJBImp implements IMedicamentLocal{

	@PersistenceContext
    private EntityManager em;
	
	
	@Override
	public void addMedicament(Medicament md) {
		if (md == null) {
            throw new IllegalArgumentException("Le medicament ne peut pas être null");
        }
        try {
            em.persist(md);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout du medicament  : " + md.getLibelle(), e);
        }
		
	}

	@Override
	public Medicament findMedicamentById(Long id) {
		if (id == null) {
            throw new IllegalArgumentException("L'ID du medicament ne peut pas être null");
        }
        try {
            Medicament md = em.find(Medicament.class, id);
            if (md == null) {
                throw new RuntimeException("medicament introuvable avec l'ID : " + id);
            }
            return md;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du medicament avec l'ID : " + id, e);
        }
	}

	@Override
	public void updateMedicament(Medicament md) {
		if (md == null) {
            throw new IllegalArgumentException("Le medicament ne peut pas être null");
        }
        try {
            em.merge(md);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la modification du medicament : " + md.getLibelle(), e);
        }
	}
	
	@Override
	public List<Medicament> getMedicamentsByIds(List<Long> idsMed) {
	    if (idsMed == null || idsMed.isEmpty()) {
	        throw new IllegalArgumentException("La liste des IDs ne peut pas être nulle ou vide.");
	    }
	    try {
	        return em.createQuery(
	            "SELECT m FROM Medicament m WHERE m.idMedicament IN :ids", Medicament.class)
	            .setParameter("ids", idsMed)
	            .getResultList();
	    } catch (Exception e) {
	        throw new RuntimeException("Erreur lors de la récupération des médicaments avec les IDs fournis.", e);
	    }
	}

	@Override
	public boolean deleteMedicament(Long id) {
		try {
	        Medicament med = em.find(Medicament.class, id);
	        if (med != null) {
	            em.remove(med);
	            return true;
	        }
	        return false;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	 public void decreaseQuantityByOne(List<Long> idsMed) {
	        if (idsMed == null || idsMed.isEmpty()) {
	            throw new IllegalArgumentException("La liste des IDs ne peut pas être nulle ou vide.");
	        }

	        try {
	            List<Medicament> medicaments = getMedicamentsByIds(idsMed);

	            for (Medicament medicament : medicaments) {
	                int currentQuantity = medicament.getQuantite();
	                if (currentQuantity > 0) {
	                    medicament.setQuantite(currentQuantity - 1); 
	                    em.merge(medicament); 
	                }
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la diminution de la quantité des médicaments.", e);
	        }
	    }

}
