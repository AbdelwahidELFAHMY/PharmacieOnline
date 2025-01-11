package Web.Services.JsonSerialisation;

import java.io.Serializable;
import java.util.List;

public class PharmaciesWithPharmaciens implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long idPharmacie;
	
	private String nom;
	
	private String adresse;
	
	private String image;
	
	private String localisation;

	private boolean isActive;
	
	private List<PharmacienData> pharmaciens;
	
	public PharmaciesWithPharmaciens(Long idPharmacie, String nom, String adresse, String image, String localisation,
			boolean isActive, List<PharmacienData> pharmaciens) {
		super();
		this.idPharmacie = idPharmacie;
		this.nom = nom;
		this.adresse = adresse;
		this.setImage(image);
		this.setLocalisation(localisation);
		this.isActive = isActive;
		this.pharmaciens = pharmaciens;
	}



	public PharmaciesWithPharmaciens() {
		super();
		// TODO Auto-generated constructor stub
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	public List<PharmacienData> getPharmaciens() {
		return pharmaciens;
	}



	public void setPharmaciens(List<PharmacienData> pharmaciens) {
		this.pharmaciens = pharmaciens;
	}



	public Long getIdPharmacie() {
		return idPharmacie;
	}

	public void setIdPharmacie(Long idPharmacie) {
		this.idPharmacie = idPharmacie;
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



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getLocalisation() {
		return localisation;
	}



	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
}
