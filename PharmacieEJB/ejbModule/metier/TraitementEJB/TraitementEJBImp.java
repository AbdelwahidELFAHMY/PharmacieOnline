package metier.TraitementEJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.Medicament;
import metier.entities.Traitement;

@Stateless
public class TraitementEJBImp implements ITraitementLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addTraitement(Traitement tr) {
        if (tr == null) {
            throw new IllegalArgumentException("Le traitement ne peut pas être null");
        }
        try {
            em.persist(tr);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout du traitement", e);
        }
    }

    @Override
    public Traitement findTraitementById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du traitement ne peut pas être null");
        }
        try {
            Traitement tr = em.find(Traitement.class, id);
            if (tr == null) {
                throw new RuntimeException("Traitement introuvable avec l'ID : " + id);
            }
            return tr;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du traitement avec l'ID : " + id, e);
        }
    }

    @Override
    public boolean deleteTraitement(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du traitement ne peut pas être null");
        }
        try {
            Traitement tr = em.find(Traitement.class, id);
            if (tr == null) {
                throw new RuntimeException("Traitement introuvable avec l'ID : " + id);
            }
            em.remove(tr);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du traitement avec l'ID : " + id, e);
        }
    }

    @Override
    public void updateTraitement(Medicament md) {
        if (md == null) {
            throw new IllegalArgumentException("Le médicament ne peut pas être null");
        }
        try {
            em.merge(md);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour du médicament dans le traitement", e);
        }
    }
}