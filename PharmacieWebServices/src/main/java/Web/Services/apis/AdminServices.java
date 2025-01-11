package Web.Services.apis;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Web.Services.JsonSerialisation.PharmacieCommandes;
import Web.Services.JsonSerialisation.PharmacienData;
import Web.Services.JsonSerialisation.PharmaciesData;
import Web.Services.JsonSerialisation.PharmaciesWithPharmaciens;
import Web.Services.JsonSerialisation.StatisticsForAdmin;
import metier.AdminEJB.IAdminLocal;
import metier.CommandeEJB.ICommandeLocal;
import metier.PharmacieEJB.IPharmacieLocal;
import metier.UtilisateurEJB.IUtilisateurLocal;
import metier.entities.Pharmacie;

@Stateless
@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminServices {

	@Inject
	private ICommandeLocal commandeMetier;
	
	@Inject 
	private IPharmacieLocal pharmacieMetier;
	
	@Inject 
	private IUtilisateurLocal utilisateurMetier;
	
	@Inject
	private IAdminLocal adminMetier;
	
	@GET
	@Path("/statistiques")
	public Response getStatistiques() {
		try {
            Long nbrPatients = utilisateurMetier.getTotalPatients();
            Long nbrOrdonnances = commandeMetier.getTotalOrders();
            Long nbrPharmaciesActives = pharmacieMetier.getActivePharmacies();
            Long nbrPharmaciesPartenairs = pharmacieMetier.getTotalPharmacies();
            
            StatisticsForAdmin stats = new StatisticsForAdmin(nbrPatients,nbrOrdonnances,nbrPharmaciesActives,nbrPharmaciesPartenairs);
            
            return Response.ok(stats).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
	}
	
	
	@Path("/analytics")
	@GET
	public Response getAnalytics() {
	    try {
	        // Get raw data from adminMetier
	        List<Object> rawData = adminMetier.getOrdersByPharmacyAndMonth();

	        // Transform raw data into List<PharmacieCommandes>
	        List<PharmacieCommandes> analyticsData = rawData.stream()
	            .map(record -> {
	                Object[] row = (Object[]) record;
	                String nomPharmacie = (String) row[0];
	                String mois = String.valueOf(row[1]); 
	                Long nbrOrdonnances = (Long) row[2];
	                return new PharmacieCommandes(nomPharmacie, mois, nbrOrdonnances);
	            })
	            .collect(Collectors.toList()); // Replace `toList()` with `collect(Collectors.toList())`

	        // Return the serialized JSON response
	        return Response.ok(analyticsData).build();

	    } catch (Exception e) {
	        e.printStackTrace();

	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity("An error occurred while fetching analytics data.")
	                       .build();
	    }
	}



	@Path("/pharmacies")
	@GET
	public Response getPharmaciesWithPharmaciens() {
	    try {
	        List<Pharmacie> pharmacies = adminMetier.getPharmaciesWithPharmaciens();
	        
	        // Convert Pharmacie entities to PharmaciesData DTOs
	        List<PharmaciesWithPharmaciens> pharmaciesData = pharmacies.stream().map(pharmacie -> {
	            // Convert Pharmaciens from Pharmacie to PharmacienData
	            List<PharmacienData> pharmaciensData = pharmacie.getMesPharmaciens().stream()
	                .map(pharmacien -> new PharmacienData(
	                    pharmacien.getId_utilisateur(),
	                    pharmacien.getNom(),
	                    pharmacien.getPrenom(),
	                    pharmacien.getEmail(),
	                    pharmacien.getTelephone(),
	                    pharmacien.getImage(),
	                    pharmacien.getAdresse(),
	                    pharmacien.getStatus()
	                ))
	                .collect(Collectors.toList());

	            // Create PharmaciesData DTO
	            return new PharmaciesWithPharmaciens(
	                pharmacie.getIdPharmacie(),
	                pharmacie.getNom(), 
	                pharmacie.getAdresse(),
	                pharmacie.getImage(),
	                pharmacie.getLocalisation(),
	                pharmacie.isActive(),
	                pharmaciensData
	            );
	        }).collect(Collectors.toList());

	        // Return the response
	        return Response.ok(pharmaciesData).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity("An error occurred while fetching pharmacies and pharmacists data.")
	                       .build();
	    }
	}


	@Path("/pharmacies/add")
	@POST
	public Response addNewPharmacie(PharmaciesData requestData) {
	    try {
	        Pharmacie newPharmacy = new Pharmacie();
	        
	        newPharmacy.setNom(requestData.getNom());
	        newPharmacy.setAdresse(requestData.getAdresse());
	        newPharmacy.setLocalisation(requestData.getLocalisation());
	        newPharmacy.setActive(requestData.isActive());
	        newPharmacy.setImage(requestData.getImage());

	        // Ajout à la base
	        pharmacieMetier.addPharmacie(newPharmacy);

	        return Response.status(Response.Status.CREATED).entity(newPharmacy).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity("Erreur lors de l'ajout de la pharmacie.")
	                       .build();
	    }
	}
	
	@Path("/{id}")
	@PUT
	public Response updatePharmacie(@PathParam("id") Long id, PharmaciesData requestData) {
	    try {
	        Pharmacie existingPharmacy = new Pharmacie(requestData.getIdPharmacie(),requestData.getImage(), requestData.getNom(), requestData.getAdresse(),
	        		requestData.getLocalisation(),requestData.isActive());
	        
	        if ( pharmacieMetier.updatePharmacie(existingPharmacy))
	        	return Response.ok(existingPharmacy).build();
	        else
	        	return  Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity("Erreur lors de la mise à jour de la pharmacie.")
	                       .build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity("Erreur lors de la mise à jour de la pharmacie.")
	                       .build();
	    }
	}

	@Path("/{id}")
	@DELETE
	public Response deletePharmacie(@PathParam("id") Long id) {
	    try {
	        boolean deleted = pharmacieMetier.deletePharmacie(id);
	        if (deleted) {
	            return Response.ok("Pharmacie supprimée avec succès.").build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND)
	                .entity("Pharmacie introuvable avec l'ID: " + id).build();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	            .entity("Erreur lors de la suppression de la pharmacie.").build();
	    }
	}

	@Path("pharmacien/{id}")
	@DELETE
	public Response deletePharmacien(@PathParam("id") Long id) {
	    try {
	        boolean deleted = utilisateurMetier.deletePharmacien(id);
	        if (deleted) {
	            return Response.ok("Pharmacien supprimée avec succès.").build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND)
	                .entity("Pharmacien introuvable avec l'ID: " + id).build();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	            .entity("Erreur lors de la suppression de la pharmacien.").build();
	    }
	}

}
