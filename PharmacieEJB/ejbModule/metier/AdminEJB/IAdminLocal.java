package metier.AdminEJB;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Pharmacie;

@Local
public interface IAdminLocal {

	public List<Object> getOrdersByPharmacyAndMonth();
	public List<Pharmacie> getPharmaciesWithPharmaciens();
}
