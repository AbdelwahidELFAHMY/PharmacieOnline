package metier.entities;

import java.util.Set;

import javax.persistence.*;


@Entity
@DiscriminatorValue("PHARMACIEN")
public class Pharmacien extends Utilisateur{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "STATUS", length = 50)
	String Status="actif";

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "pharmacien")
	private Set<Traitement> mesTraitements;

	@ManyToOne(fetch = FetchType.EAGER)
	private Pharmacie pharmacie;
	

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	public Pharmacie getPharmacie() {
		return pharmacie;
	}

	public Pharmacien(String status, Set<Traitement> mesTraitements, Pharmacie pharmacie) {
		super();
		Status = status;
		this.mesTraitements = mesTraitements;
		this.pharmacie = pharmacie;
	}

	public Pharmacien(Long id, String nom, String prenom, String email, String password, String telephone,
			String adresse, String image) {
		super(id, nom, prenom, email, password, telephone, adresse, image);
		// TODO Auto-generated constructor stub
	}

	public void setPharmacie(Pharmacie pharmacie) {
		this.pharmacie = pharmacie;
	}

	public Set<Traitement> getMesTraitements() {
		return mesTraitements;
	}

	public void setMesTraitements(Set<Traitement> mesTraitements) {
		this.mesTraitements = mesTraitements;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Pharmacien(String status) {
		super();
		Status = status;
	}

	public Pharmacien() {
		super();
	}
    
    
}
