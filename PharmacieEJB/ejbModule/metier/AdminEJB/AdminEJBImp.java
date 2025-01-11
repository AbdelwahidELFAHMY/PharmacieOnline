package metier.AdminEJB;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Pharmacie;

@Stateless
public class AdminEJBImp implements IAdminLocal{
	
	@PersistenceContext
    private EntityManager em;
	
	
	public List<Object> getOrdersByPharmacyAndMonth() {
	    String jpql = "SELECT p.nom, FUNCTION('MONTH', c.dateCommande), COUNT(c) " +
	                  "FROM Commande c " +
	                  "JOIN c.pharmacie p " +
	                  "GROUP BY p.nom, FUNCTION('MONTH', c.dateCommande)";
	    Query query = em.createQuery(jpql);
	    return query.getResultList();
	}
	
	public List<Pharmacie> getPharmaciesWithPharmaciens() {
	    try {
	        String jpql = "SELECT p FROM Pharmacie p LEFT JOIN FETCH p.mesPharmaciens";
	        return em.createQuery(jpql, Pharmacie.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error fetching pharmacies with pharmacists: " + e.getMessage(), e);
	    }
	}



}
