package metier.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Medicaments")
public class Medicament {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedicament; 
	
	@NotNull(message = "Le libellé de medicament ne peut pas être nul.")
    private String libelle;

    @PositiveOrZero(message = "La quantité de medicament doit être positive ou égale à zéro.")
    private int quantite;

    @Min(value = 0, message = "Le prix unitaire de medicament doit être supérieur ou égal à zéro.")
    private Double prixUnitaire;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Pharmacie pharmacie;
				
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "medicaments")
	private List<Traitement> traitements;
	
	public Medicament(Long idMedicament,
			@NotNull(message = "Le libellé de medicament ne peut pas être nul.") String libelle,
			@PositiveOrZero(message = "La quantité de medicament doit être positive ou égale à zéro.") int quantite,
			@Min(value = 0, message = "Le prix unitaire de medicament doit être supérieur ou égal à zéro.") Double prixUnitaire,
			Pharmacie pharmacie) {
		super();
		this.idMedicament = idMedicament;
		this.libelle = libelle;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.pharmacie = pharmacie;
	}


	public Pharmacie getPharmacie() {
		return pharmacie;
	}


	public void setPharmacie(Pharmacie pharmacie) {
		this.pharmacie = pharmacie;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public List<Traitement> getTraitements() {
		return traitements;
	}


	public void setTraitements(List<Traitement> traitements) {
		this.traitements = traitements;
	}


	public Medicament() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdMedicament() {
		return idMedicament;
	}

	public void setIdMedicament(Long idMedicament) {
		this.idMedicament = idMedicament;
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
	
	
}
