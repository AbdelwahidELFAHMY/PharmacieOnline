package metier.TraitementEJB;

import javax.ejb.Local;

import metier.entities.Medicament;
import metier.entities.Traitement;

@Local
public interface ITraitementLocal {

	void addTraitement(Traitement tr);
    public Traitement findTraitementById(Long id);
	public boolean deleteTraitement(Long id);
    void updateTraitement(Medicament md);
}
