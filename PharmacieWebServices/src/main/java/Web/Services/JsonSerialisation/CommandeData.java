package Web.Services.JsonSerialisation;

public class CommandeData {
	private Long idCommande;
    private String dateCommande;
    private String status;
    private String ordonnanceImage;
    private String pharmacieNom;

    public CommandeData(Long idCommande, String dateCommande, String status, String ordonnanceImage, String pharmacieNom) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.status = status;
        this.ordonnanceImage = ordonnanceImage;
        this.pharmacieNom = pharmacieNom;
    }

	public Long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}

	public String getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrdonnanceImage() {
		return ordonnanceImage;
	}

	public void setOrdonnanceImage(String ordonnanceImage) {
		this.ordonnanceImage = ordonnanceImage;
	}

	public String getPharmacieNom() {
		return pharmacieNom;
	}

	public void setPharmacieNom(String pharmacieNom) {
		this.pharmacieNom = pharmacieNom;
	}

    
}
