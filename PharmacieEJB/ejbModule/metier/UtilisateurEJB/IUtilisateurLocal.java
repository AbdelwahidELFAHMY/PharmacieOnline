package metier.UtilisateurEJB;

import java.util.List;
import javax.ejb.Local;

import metier.entities.Admin;
import metier.entities.Patient;
import metier.entities.Pharmacien;
import metier.entities.Utilisateur;

@Local
public interface IUtilisateurLocal {
	 public void addPatient(Patient patient);
	 public void addPharmacien(Pharmacien pharmacien,Long idPharmacie);
	 public void addAdmin(Admin admin); 
	 public Pharmacien getPharmacien(String email, String password);
    public Patient getPatient(String email, String password);
    public String getRole(String email, String password);
    public Patient findPatientById(Long id);
    public Admin findAdminById(Long id);
    public boolean deletePharmacien(Long id);
    public Pharmacien findPharmacienById(Long id);
    public Admin getAdmin(String email, String password);
    public boolean updatePatient(Patient pat);
    public List<Utilisateur> getAll();
    public boolean updatePharmacien(Pharmacien ph);
    public boolean updateAdmin(Admin admin);
	public long getTotalPatients();
}
