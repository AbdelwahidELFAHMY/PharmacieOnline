package metier.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Traitements")
public class Traitement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTraitement; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTraitement;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Ordonnance ordonnance;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Medicament> medicaments;
	
	@ManyToOne
	private Pharmacien pharmacien;
	

	
	public Pharmacien getPharmacien() {
		return pharmacien;
	}

	public void setPharmacien(Pharmacien pharmacien) {
		this.pharmacien = pharmacien;
	}

	public Long getIdTraitement() {
		return idTraitement;
	}

	public void setIdTraitement(Long idTraitement) {
		this.idTraitement = idTraitement;
	}

	public Date getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public Ordonnance getOrdonnance() {
		return ordonnance;
	}

	public void setOrdonnance(Ordonnance ordonnance) {
		this.ordonnance = ordonnance;
	}

	public List<Medicament> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(List<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

	public Traitement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
