package metier.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends Utilisateur {
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "patient")
	@JsonIgnore
	private List<Commande> mesCommandes; 
	
	public List<Commande> getMesCommandes() {
		return mesCommandes;
	}

	public void setMesCommandes(List<Commande> mesCommandes) {
		this.mesCommandes = mesCommandes;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(List<Commande> mesCommandes) {
		super();
		this.mesCommandes = mesCommandes;
	}
	
	
}
