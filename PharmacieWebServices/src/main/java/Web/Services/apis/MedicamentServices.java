package Web.Services.apis;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Web.Services.JsonSerialisation.MedicamentData;
import Web.Services.JsonSerialisation.MedicamentRequest;
import metier.MedicamentEJB.IMedicamentLocal;
import metier.UtilisateurEJB.IUtilisateurLocal;
import metier.entities.Medicament;
import metier.entities.Pharmacien;

@Stateless
@Path("/medicaments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicamentServices {

	@Inject
    IMedicamentLocal medicamentMetier;
	@Inject
	IUtilisateurLocal utilisateurMetier;
	
	@Path("/addMedicament")
	@POST
    public Response ajouterMedicament(MedicamentRequest medicament,@QueryParam("idPharmacien")Long idPharmacien) {
        try {
        	Pharmacien ph = utilisateurMetier.findPharmacienById(idPharmacien);
        	Medicament newMed = new Medicament();
        	newMed.setLibelle(medicament.getLibelle());
        	newMed.setPrixUnitaire(medicament.getPrixUnitaire());
        	newMed.setQuantite(medicament.getQuantite());
        	newMed.setPharmacie(ph.getPharmacie());
        	
        	medicamentMetier.addMedicament(newMed);
            return Response.status(Response.Status.CREATED).entity("Medicament ajouté avec succès").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur : " + e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response supprimerMedicament(@PathParam("id") Long id) {
        try {
            boolean deleted = medicamentMetier.deleteMedicament(id);
            if (deleted) {
                return Response.ok("Medicament supprimé avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Medicament introuvable").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur : " + e.getMessage()).build();
        }
    }

    @Path("/edit")
    @PUT
    public Response modifierMedicament(MedicamentData medicament) {
        try {
        	Medicament medToUpdate = medicamentMetier.findMedicamentById(medicament.getIdMed());
        	medToUpdate.setLibelle(medicament.getLibelle());
        	medToUpdate.setPrixUnitaire(medicament.getPrixUnitaire());
        	medToUpdate.setQuantite(medicament.getQuantite());
        	
        	medicamentMetier.updateMedicament(medToUpdate);
        	
            return Response.ok("Medicament modifié avec succès").build();
        } catch (Exception e) {
        	e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur : " + e.getMessage()).build();
        }
    }

}
