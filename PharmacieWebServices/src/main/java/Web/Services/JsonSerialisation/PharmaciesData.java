package Web.Services.JsonSerialisation;

import java.io.Serializable;

public class PharmaciesData implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idPharmacie;
	
	private String image;
	
	private String nom;
	
	private String adresse;
	
	private String localisation;
	
	private boolean isActive;

	

	public PharmaciesData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PharmaciesData(Long idPharmacie, String image, String nom, String adresse, String localisation,
			boolean isActive) {
		super();
		this.idPharmacie = idPharmacie;
		this.image = image;
		this.nom = nom;
		this.adresse = adresse;
		this.localisation = localisation;
		this.isActive = isActive;
	}

	public PharmaciesData(Long idPharmacie, String image, String nom, String adresse, String localisation) {
		super();
		this.idPharmacie = idPharmacie;
		this.image = image;
		this.nom = nom;
		this.adresse = adresse;
		this.localisation = localisation;
	}

	public Long getIdPharmacie() {
		return idPharmacie;
	}

	public void setIdPharmacie(Long idPharmacie) {
		this.idPharmacie = idPharmacie;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
