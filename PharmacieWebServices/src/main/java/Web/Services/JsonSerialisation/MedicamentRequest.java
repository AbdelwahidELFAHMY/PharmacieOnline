package Web.Services.JsonSerialisation;

import java.io.Serializable;

public class MedicamentRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String libelle;
    private Double prixUnitaire;
    private int quantite;

   
    public MedicamentRequest(String libelle, Double prixUnitaire, int quantite) {
		super();
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MedicamentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(Double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
