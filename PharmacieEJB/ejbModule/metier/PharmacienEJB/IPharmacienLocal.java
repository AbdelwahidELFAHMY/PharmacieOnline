package metier.PharmacienEJB;

import java.util.List;
import metier.entities.Commande;
import metier.entities.Medicament;

public interface IPharmacienLocal {
    List<Commande> getCommandesByPharmacien(Long idPharmacien);
    List<Medicament> listMedicaments(Long idPharmacien);
}
