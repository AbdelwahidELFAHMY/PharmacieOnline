package Web.Services.JsonSerialisation;

import java.io.Serializable;

public class PharmacienCommandes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Long idCommande;
    private String dateCommande;
    private String status;
    private String ordonnanceImage;
    private String nomPatient;
    private Long idPatient;

    

	public PharmacienCommandes(Long idCommande, String dateCommande, String status, String ordonnanceImage,
			String nomPatient, Long idPatient) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.status = status;
		this.ordonnanceImage = ordonnanceImage;
		this.nomPatient = nomPatient;
		this.idPatient = idPatient;
	}

	
	public Long getIdPatient() {
		return idPatient;
	}


	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
	}


	// Getters and setters for all fields
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

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }
}
