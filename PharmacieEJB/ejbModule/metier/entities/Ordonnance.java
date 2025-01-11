package metier.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ordonnances")
public class Ordonnance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrdonnace; 
	
	@Column(nullable = false)
	private String image;
	
	@OneToOne(mappedBy = "ordonnance")
	private Commande commande;
	
	@OneToOne(mappedBy = "ordonnance")
	private Traitement traitement;

	public Ordonnance(Long idOrdonnace, String image) {
		super();
		this.idOrdonnace = idOrdonnace;
		this.image = image;
	}

	public Long getIdOrdonnace() {
		return idOrdonnace;
	}

	public void setIdOrdonnace(Long idOrdonnace) {
		this.idOrdonnace = idOrdonnace;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Ordonnance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Traitement getTraitement() {
		return traitement;
	}

	public void setTraitement(Traitement traitement) {
		this.traitement = traitement;
	}
	
	

}
