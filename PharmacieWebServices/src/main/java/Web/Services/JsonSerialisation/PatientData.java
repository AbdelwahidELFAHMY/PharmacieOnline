package Web.Services.JsonSerialisation;

import java.io.Serializable;

public class PatientData implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id_utilisateur;
	
    private String nom;
	
    private String prenom;
	
    private String email;

    private String telephone;
	
    private String image;

    private String adresse;
    
    private String role="patient";

	public PatientData(Long id_utilisateur, String nom, String prenom, String email, String telephone, String image,
			String adresse) {
		super();
		this.id_utilisateur = id_utilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.image = image;
		this.adresse = adresse;
	}

	public PatientData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(Long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    

	
	
}
