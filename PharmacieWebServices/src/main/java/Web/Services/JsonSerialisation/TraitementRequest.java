package Web.Services.JsonSerialisation;

import java.io.Serializable;
import java.util.List;

public class TraitementRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Long> medicamentIds;
	private Long commandeId;
	private Long pharmacienId;
	
	public TraitementRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TraitementRequest(List<Long> medicamentIds, Long commandeId, Long pharmacienId) {
		super();
		this.medicamentIds = medicamentIds;
		this.commandeId = commandeId;
		this.pharmacienId = pharmacienId;
	}
	public List<Long> getMedicamentIds() {
		return medicamentIds;
	}
	public void setMedicamentIds(List<Long> medicamentIds) {
		this.medicamentIds = medicamentIds;
	}
	public Long getCommandeId() {
		return commandeId;
	}
	public void setCommandeId(Long commandeId) {
		this.commandeId = commandeId;
	}
	public Long getPharmacienId() {
		return pharmacienId;
	}
	public void setPharmacienId(Long pharmacienId) {
		this.pharmacienId = pharmacienId;
	}
	
	
	
}