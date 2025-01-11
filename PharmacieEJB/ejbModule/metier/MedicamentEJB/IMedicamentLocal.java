package metier.MedicamentEJB;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Medicament;

@Local
public interface IMedicamentLocal {
	void addMedicament(Medicament md);
    public Medicament findMedicamentById(Long id);
	public boolean deleteMedicament(Long id);
    void updateMedicament(Medicament md);
    List<Medicament> getMedicamentsByIds(List<Long> idsMed);
	 public void decreaseQuantityByOne(List<Long> idsMed);
}
