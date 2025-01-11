package Web.Services.apis;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Web.Services.JsonSerialisation.PharmaciesData;
import metier.PharmacieEJB.IPharmacieLocal;
import metier.entities.Pharmacie;

@Stateless
@Path("/pharmacies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PharmacieServices {

   @Inject
    IPharmacieLocal pharmacieMetier;

    @POST
    public Response ajouterPharmacie(Pharmacie pharmacie) {
        try {
            pharmacieMetier.addPharmacie(pharmacie);
            return Response.status(Response.Status.CREATED).entity("Pharmacie ajoutée avec succès").build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur interne : " + e.getMessage()).build();
        }
    }

    @GET
    public Response listPharmacies() {
        try {
            // Récupération de la liste des entités Pharmacie depuis la couche métier
            List<Pharmacie> pharmacies = pharmacieMetier.getPharmacies();

            // Vérification si la liste est vide
            if (pharmacies.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\": \"Aucune pharmacie trouvée\"}")
                        .build();
            }

            // Transformation des entités Pharmacie en DTO PharmacieJson
            List<PharmaciesData> pharmaciesJson = pharmacies.stream()
                .map(pharmacie -> new PharmaciesData(
                    pharmacie.getIdPharmacie(),
                    pharmacie.getImage(),
                    pharmacie.getNom(),
                    pharmacie.getAdresse(),
                    pharmacie.getLocalisation()
                ))
                .collect(Collectors.toList()); // Utilisation de Collectors.toList()

            // Retourne la liste sérialisée en JSON
            return Response.ok(pharmaciesJson).build();
        } catch (Exception e) {
            // Gestion des erreurs
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @Path("/{id}")
    @GET
    public Response recupererPharmacie(@PathParam("id") Long id) {
        try {
        	
         return Response.ok(pharmacieMetier.findPharmacieById(id)).build();
        	
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Modifier un utilisateur
    @PUT
    public Response modifierPharmacie(Pharmacie pharmacie) {
        try {
            // Appeler la logique métier
            pharmacieMetier.updatePharmacie(pharmacie);

            return Response.ok("{\"message\": \"Pharmacie modifié avec succès\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
