package metier.UtilisateurEJB;

import java.util.List;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import metier.entities.Admin;
import metier.entities.Patient;
import metier.entities.Pharmacie;
import metier.entities.Pharmacien;
import metier.entities.Utilisateur;

@Stateless
public class UtilisateurEJBImp implements IUtilisateurLocal {

    @PersistenceContext
    private EntityManager em;

    // Créer un nouvel utilisateur
    @Override
    public void addPatient(Patient patient) {
        try {
            /*// Hachage du mot de passe avant d'enregistrer
            String hashed  = BCrypt.hashpw(utilisateur.getMotpasse(), BCrypt.gensalt());
            utilisateur.setMotpasse(hashed);*/

            em.persist(patient);
        } catch (Exception e) {
        	throw new EJBTransactionRolledbackException("Transaction failed: " + e.getMessage());        }
    }

    @Override
    @Transactional
    public void addPharmacien(Pharmacien pharmacien,Long idPharmacie) {
        try {
            /*// Hachage du mot de passe avant d'enregistrer
            String hashed  = BCrypt.hashpw(utilisateur.getMotpasse(), BCrypt.gensalt());
            utilisateur.setMotpasse(hashed);*/
        
        	Pharmacie maPharmacie = em.find(Pharmacie.class, idPharmacie);
        	if (maPharmacie == null) {
        	    throw new IllegalArgumentException("La pharmacie avec l'ID " + idPharmacie + " est introuvable.");
        	}
        	pharmacien.setPharmacie(em.merge(maPharmacie));

            em.persist(pharmacien);
            
        } catch (Exception e) {
        	throw new EJBTransactionRolledbackException("Transaction failed: " + e.getMessage());        }
    }
    
    @Override
    public void addAdmin(Admin admin) {
        try {
            /*// Hachage du mot de passe avant d'enregistrer
            String hashed  = BCrypt.hashpw(utilisateur.getMotpasse(), BCrypt.gensalt());
            utilisateur.setMotpasse(hashed);*/

            em.persist(admin);
        } catch (Exception e) {
        	throw new EJBTransactionRolledbackException("Transaction failed: " + e.getMessage());        }
    }
    
    
    @Override
    public Patient getPatient(String email, String password) {
        try {
            TypedQuery<Patient> query = em.createQuery(
                "SELECT p FROM Patient p WHERE p.email = :email AND p.motpasse = :password", Patient.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            
            List<Patient> patients = query.getResultList();
            
            if (!patients.isEmpty()) {
                return patients.get(0);
            }
            
            return null; // Aucun patient trouvé
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du patient", e);
        }
    }

    @Override
    public Pharmacien getPharmacien(String email, String password) {
        try {
            TypedQuery<Pharmacien> query = em.createQuery(
                "SELECT p FROM Pharmacien p WHERE p.email = :email AND p.motpasse = :password", Pharmacien.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<Pharmacien> pharmaciens = query.getResultList();

            if (!pharmaciens.isEmpty()) {
                return pharmaciens.get(0);
            }

            return null; // Aucun pharmacien actif trouvé
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du pharmacien", e);
        }
    }
    
    @Override
    public Admin getAdmin(String email, String password) {
        try {
            TypedQuery<Admin> query = em.createQuery(
                "SELECT a FROM Admin a WHERE a.email = :email AND a.motpasse = :password", Admin.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<Admin> admins = query.getResultList();

            if (!admins.isEmpty()) {
                return admins.get(0);
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du l'Admin", e);
        }
    }

    @Override
    public List<Utilisateur> getAll() {
        TypedQuery<Utilisateur> query = em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class);
        return query.getResultList();
    }

	@Override
	public Patient findPatientById(Long id) {
		 if (id == null) {
	            throw new IllegalArgumentException("L'ID du patient ne peut pas être null");
	        }
	        try {
	            Patient patient = em.find(Patient.class, id);
	            if (patient == null) {
	                throw new RuntimeException("Patient introuvable avec l'ID : " + id);
	            }
	            return patient;
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la récupération du Patient avec l'ID : " + id, e);
	        }
	}

	@Override
	public boolean updatePatient(Patient pat) {

		 try {
	            em.merge(pat);
	            return true;
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la modification du Patient", e);
	        }
	}

	@Override
	public boolean updatePharmacien(Pharmacien ph) {
		 try {
	            em.merge(ph);
	            return true;
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la modification du pharmacien", e);
	        }
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		 try {
	            em.merge(admin);
	            return true;
	        } catch (Exception e) {
	            throw new RuntimeException("Erreur lors de la modification de l'admin ", e);
	        }
	}

	@Override
	public Admin findAdminById(Long id) {
		if (id == null) {
            throw new IllegalArgumentException("L'ID du l'admin ne peut pas être null");
        }
        try {
            Admin admin = em.find(Admin.class, id);
            if (admin == null) {
                throw new RuntimeException("Admin introuvable avec l'ID : " + id);
            }
            return admin;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du l'admin avec l'ID : " + id, e);
        }
	}

	@Override
	public Pharmacien findPharmacienById(Long id) {
		if (id == null) {
            throw new IllegalArgumentException("L'ID du pharmacien ne peut pas être null");
        }
        try {
            Pharmacien pharmacien = em.find(Pharmacien.class, id);
            if (pharmacien == null) {
                throw new RuntimeException("pharmacien introuvable avec l'ID : " + id);
            }
            return pharmacien;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du pharmacien avec l'ID : " + id, e);
        }
	}

	@Override
	public String getRole(String email, String password) {
	    try {
	        // Using the TYPE() function to determine the subclass (role)
	        String jpql = "SELECT TYPE(u) FROM Utilisateur u WHERE u.email = :email AND u.motpasse = :motpasse";
	        TypedQuery<Class> query = em.createQuery(jpql, Class.class);
	        query.setParameter("email", email);
	        query.setParameter("motpasse", password);

	        // Extracting the class of the result, which represents the role
	        Class<?> resultClass = query.getSingleResult();

	        // Returning the simple name of the class (role) or any mapped logic
	        return resultClass.getSimpleName();
	    } catch (Exception e) {
		        return null;
	    }
	}


	@Override
	public long getTotalPatients() {
	    String jpql = "SELECT COUNT(p) FROM Patient p";
	    Query query = em.createQuery(jpql);
	    return (long) query.getSingleResult();
	}

	@Override
	public boolean deletePharmacien(Long id) {

		try {
	        Pharmacien ph = em.find(Pharmacien.class, id);
	        if (ph != null) {
	            em.remove(ph);
	            return true;
	        }
	        return false;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}



}
