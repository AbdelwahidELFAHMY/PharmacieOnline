package Web.Services.JsonSerialisation;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Commande;
import metier.entities.Medicament;


public class CommandeDetails {

    private String pharmacieNom;
    private String dateCommande;
    private String dateTraitement;
    private List<MedicamentData> medicaments;
    
    public CommandeDetails(Commande commande) {
        this.pharmacieNom = commande.getPharmacie().getNom();
        this.dateCommande = commande.getDateCommande().toString();
        this.dateTraitement = commande.getOrdonnance().getTraitement().getDateTraitement().toString();
        this.medicaments = new ArrayList<>();
        for (Medicament medicament : commande.getOrdonnance().getTraitement().getMedicaments()) {
            medicaments.add(new MedicamentData(medicament));
        }
    }

	public String getPharmacieNom() {
		return pharmacieNom;
	}

	public void setPharmacieNom(String pharmacieNom) {
		this.pharmacieNom = pharmacieNom;
	}
	
	

	public String getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(String dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public List<MedicamentData> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(List<MedicamentData> medicaments) {
		this.medicaments = medicaments;
	}

   
}

