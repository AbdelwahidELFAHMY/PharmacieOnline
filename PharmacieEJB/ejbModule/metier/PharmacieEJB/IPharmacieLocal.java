package metier.PharmacieEJB;

import java.util.List;

import javax.ejb.Local;
import metier.entities.Pharmacie;

@Local
public interface IPharmacieLocal {

	void addPharmacie(Pharmacie pharmacie);
    public Pharmacie findPharmacieById(Long id);
    public boolean deletePharmacie(Long id);
    public boolean updatePharmacie(Pharmacie pharmacie);
    List<Pharmacie> getPharmacies();
    public long getActivePharmacies();
    public long getTotalPharmacies();

}
