package Web.Services.JsonSerialisation;

import java.io.Serializable;

public class PharmacieCommandes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomPharmacie;
	private String mois;
	private Long nbrOrdonnances;
	public PharmacieCommandes(String nomPharmacie, String mois, Long nbrOrdonnances) {
		super();
		this.nomPharmacie = nomPharmacie;
		this.mois = mois;
		this.nbrOrdonnances = nbrOrdonnances;
	}
	public PharmacieCommandes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNomPharmacie() {
		return nomPharmacie;
	}
	public void setNomPharmacie(String nomPharmacie) {
		this.nomPharmacie = nomPharmacie;
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public Long getNbrOrdonnances() {
		return nbrOrdonnances;
	}
	public void setNbrOrdonnances(Long nbrOrdonnances) {
		this.nbrOrdonnances = nbrOrdonnances;
	}
	
	

}
