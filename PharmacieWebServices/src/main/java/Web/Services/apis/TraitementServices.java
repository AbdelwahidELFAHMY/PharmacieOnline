package Web.Services.apis;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Web.Services.JsonSerialisation.TraitementRequest;
import metier.CommandeEJB.ICommandeLocal;
import metier.MedicamentEJB.IMedicamentLocal;
import metier.TraitementEJB.ITraitementLocal;
import metier.UtilisateurEJB.IUtilisateurLocal;
import metier.entities.Commande;
import metier.entities.Traitement;


@Stateless
@Path("/traitements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TraitementServices {

	@Inject
    private ITraitementLocal traitementMetier;
	
	@Inject
	private IUtilisateurLocal utilisateurMetier;
	
	@Inject 
	private ICommandeLocal commandeMetier;
	
	@Inject
	private IMedicamentLocal medicamentMetier;
	

    @Path("/newtraitement")
    @POST
    public Response addTraitement(TraitementRequest traitementRequest) {
        try {
            Long pharmacienId = traitementRequest.getPharmacienId();
            Long commandeId = traitementRequest.getCommandeId();
            List<Long> medicamentIds = traitementRequest.getMedicamentIds();
            
            Traitement traitement = new Traitement();
            traitement.setDateTraitement(new Date());
            Commande cmd = commandeMetier.getCommandeDetails(commandeId);
            traitement.setOrdonnance(cmd.getOrdonnance());
            traitement.setMedicaments(medicamentMetier.getMedicamentsByIds(medicamentIds));
            traitement.setPharmacien(utilisateurMetier.findPharmacienById(pharmacienId));


         // Send email notification to the patient
            commandeMetier.sendEmailNotificationToPatient(
                cmd.getPatient().getEmail(),
                "Votre commande est prête à être récupérée",  
                "Bonjour, <br><br> Votre commande est prête à être récupérée à la pharmacie. Merci de passer la chercher dès que possible.<br><br> Cordialement," 
            );

            cmd.setStatus("prête");
            commandeMetier.updateCommande(cmd);
            medicamentMetier.decreaseQuantityByOne(medicamentIds);
            traitementMetier.addTraitement(traitement);
            

            // 6. Retourner un statut 201 Created
            return Response.status(Response.Status.CREATED)
                    .entity("Traitement envoyée avec succès")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la création de la traitement")
                    .build();
        }
    }

}
