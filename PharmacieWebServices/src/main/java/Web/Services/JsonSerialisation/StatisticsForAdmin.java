package Web.Services.JsonSerialisation;

import java.io.Serializable;

public class StatisticsForAdmin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Long nbrPatients;
	private Long nbrOrdonnances;
	private Long nbrPharmaciesActives;
	private Long nbrPharmaciesPartenaires;
	public StatisticsForAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatisticsForAdmin(Long nbrPatients, Long nbrOrdonnances, Long nbrPharmaciesActives,
			Long nbrPharmaciesPartenaires) {
		super();
		this.nbrPatients = nbrPatients;
		this.nbrOrdonnances = nbrOrdonnances;
		this.nbrPharmaciesActives = nbrPharmaciesActives;
		this.nbrPharmaciesPartenaires = nbrPharmaciesPartenaires;
	}
	public Long getNbrPatients() {
		return nbrPatients;
	}
	public void setNbrPatients(Long nbrPatients) {
		this.nbrPatients = nbrPatients;
	}
	public Long getNbrOrdonnances() {
		return nbrOrdonnances;
	}
	public void setNbrOrdonnances(Long nbrOrdonnances) {
		this.nbrOrdonnances = nbrOrdonnances;
	}
	public Long getNbrPharmaciesActives() {
		return nbrPharmaciesActives;
	}
	public void setNbrPharmaciesActives(Long nbrPharmaciesActives) {
		this.nbrPharmaciesActives = nbrPharmaciesActives;
	}
	public Long getNbrPharmaciesPartenaires() {
		return nbrPharmaciesPartenaires;
	}
	public void setNbrPharmaciesPartenaires(Long nbrPharmaciesPartenaires) {
		this.nbrPharmaciesPartenaires = nbrPharmaciesPartenaires;
	}
	
}
