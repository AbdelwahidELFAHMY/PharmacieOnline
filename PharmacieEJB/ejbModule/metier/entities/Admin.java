package metier.entities;

import javax.persistence.*;

@DiscriminatorValue("ADMIN")
@Entity
public class Admin extends Utilisateur{
private static final long serialVersionUID = 1L;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
