package Web.Services.apis;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Web.Services.JsonSerialisation.MedicamentData;
import Web.Services.JsonSerialisation.PharmacienCommandes;
import metier.PharmacienEJB.IPharmacienLocal;
import metier.entities.Commande;
import metier.entities.Medicament;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/pharmacien")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PharmacienServices {

    @Inject
    private IPharmacienLocal pharmacienMetier;

    @Path("/getCommandes")
    @GET
    public Response getCommandes(@QueryParam("idPharmacien") Long idPharmacien) {
        // Call the business layer to get the list of Commandes for the given pharmacien
        List<Commande> commandes = pharmacienMetier.getCommandesByPharmacien(idPharmacien);

        // Transform Commandes to the desired response format
        List<PharmacienCommandes> responseList = commandes.stream().map(commande -> {
        	PharmacienCommandes response = new PharmacienCommandes(commande.getIdCommande(),commande.getDateCommande().toString(),
        			commande.getStatus(),commande.getOrdonnance().getImage(),commande.getPatient().getPrenom()+" "+commande.getPatient().getNom(),commande.getPatient().getId_utilisateur());

            return response;
        }).collect(Collectors.toList());

        // Return the list of Commandes as a response
        return Response.ok(responseList).build();
    }
    
    
    @Path("/getMedicaments")
    @GET
    public Response getMedicaments(@QueryParam("idPharmacien") Long idPharmacien) {
        try {
            // Fetch the list of Medicament entities for the given Pharmacien
            List<Medicament> medicaments = pharmacienMetier.listMedicaments(idPharmacien);

            // If no medicaments are found, return a 204 No Content response
            if (medicaments == null || medicaments.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT)
                        .entity("No medicaments found for this pharmacist.")
                        .build();
            }

            // Convert the list of Medicament entities to MedicamentData DTOs
            List<MedicamentData> medicamentDataList = medicaments.stream()
                    .map(MedicamentData::new)  // Convert each Medicament to a MedicamentData
                    .collect(Collectors.toList());

            // Return the list of MedicamentData as JSON response
            return Response.ok(medicamentDataList).build();

        } catch (Exception e) {
            // Handle any errors that occur during processing
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while fetching medicaments.")
                    .build();
        }
    }
}