package metier.OrdonnanceEJB;

import javax.ejb.Local;

import metier.entities.Ordonnance;


@Local
public interface IOrdonnanceLocal {
	
	
	void addOrdonnance(Ordonnance newOrdo);

}
