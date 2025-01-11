package metier.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Pharmacies")
public class Pharmacie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPharmacie;
	
	@Column(nullable = true)
	private String image;
	
	@Column(nullable = false)
	private String nom;
	
	@Column(nullable = false)
	private String adresse;
	
	@Column(nullable = false)
	private String localisation;
	
	@Column(nullable = false)
    private boolean isActive = true;
	
	@OneToMany(mappedBy = "pharmacie", fetch = FetchType.LAZY)
	private List<Pharmacien> mesPharmaciens;

	@OneToMany(mappedBy = "pharmacie", fetch = FetchType.LAZY)
	private List<Commande> mesCommandes;
	
	@OneToMany(mappedBy = "pharmacie", fetch = FetchType.LAZY)
	private List<Medicament> mesMedicaments;

	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

	public Pharmacie(Long idPharmacie, String image, String nom, String adresse, String localisation,
			boolean isActive) {
		super();
		this.idPharmacie = idPharmacie;
		this.image = image;
		this.nom = nom;
		this.adresse = adresse;
		this.localisation = localisation;
		this.isActive = isActive;
	}

	public Pharmacie() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Long getIdPharmacie() {
		return idPharmacie;
	}

	public void setIdPharmacie(Long idPharmacie) {
		this.idPharmacie = idPharmacie;
	}

	
	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
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

	public List<Pharmacien> getMesPharmaciens() {
		return mesPharmaciens;
	}

	public void setMesPharmaciens(List<Pharmacien> mesPharmaciens) {
		this.mesPharmaciens = mesPharmaciens;
	}

	public List<Commande> getMesCommandes() {
		return mesCommandes;
	}

	public void setMesCommandes(List<Commande> mesCommandes) {
		this.mesCommandes = mesCommandes;
	}

	public List<Medicament> getMesMedicaments() {
		return mesMedicaments;
	}

	public void setMesMedicaments(List<Medicament> mesMedicaments) {
		this.mesMedicaments = mesMedicaments;
	}	
	
}
