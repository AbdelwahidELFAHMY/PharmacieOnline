package Web.Services.JsonSerialisation;

import metier.entities.Medicament;

public class MedicamentData {
	private Long idMed;
    private String libelle;
    private Double prixUnitaire;
    private int quantite;
    
    public MedicamentData(Long idMed, String libelle, Double prixUnitaire, int quantite) {
		this.idMed = idMed;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
	}

    
	public MedicamentData() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MedicamentData(Medicament medicament) {
    	this.idMed = medicament.getIdMedicament();    			
        this.libelle = medicament.getLibelle();
        this.prixUnitaire = medicament.getPrixUnitaire();
        this.quantite = medicament.getQuantite();
    }

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Long getIdMed() {
		return idMed;
	}

	public void setIdMed(Long idMed) {
		this.idMed = idMed;
	}

    
}