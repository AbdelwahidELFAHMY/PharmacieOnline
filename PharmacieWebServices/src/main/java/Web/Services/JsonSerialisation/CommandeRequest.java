package Web.Services.JsonSerialisation;

import java.io.Serializable;

public class CommandeRequest implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private Long userId;
    private Long pharmacyId;
    private String imageUrl;
    

    // Getters et setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

	public CommandeRequest(Long userId, Long pharmacyId, String imageUrl) {
		super();
		this.userId = userId;
		this.pharmacyId = pharmacyId;
		this.imageUrl = imageUrl;
	}

	public CommandeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}

