package Web.Services.apis;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Web.Services.JsonSerialisation.CommandeData;
import Web.Services.JsonSerialisation.CommandeDetails;
import Web.Services.JsonSerialisation.CommandeRequest;
import metier.CommandeEJB.ICommandeLocal;
import metier.OrdonnanceEJB.IOrdonnanceLocal;
import metier.PharmacieEJB.IPharmacieLocal;
import metier.UtilisateurEJB.IUtilisateurLocal;
import metier.entities.Commande;
import metier.entities.Ordonnance;
import metier.entities.Pharmacie;
import metier.entities.Pharmacien;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/commandes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommandeServices {

    @Inject
    private ICommandeLocal commandeMetier;
    
    @Inject 
    private IPharmacieLocal pharmacieMetier;
    
    @Inject 
    private IUtilisateurLocal utilisateurMetier;
    
    @Inject 
    private IOrdonnanceLocal ordonnanceMetier;
    
   
    @Path("/newcommande")
    @POST
    public Response addCommande(CommandeRequest commandeRequest) {
        try {
            Long userId = commandeRequest.getUserId();
            Long pharmacyId = commandeRequest.getPharmacyId();
            String imageUrl = commandeRequest.getImageUrl();
            
            // 1. Créer une nouvelle ordonnance
            Ordonnance ordonnance = new Ordonnance();
            ordonnance.setImage(imageUrl);
            ordonnanceMetier.addOrdonnance(ordonnance);

            // 2. Créer une nouvelle commande
            Commande commande = new Commande();
            commande.setDateCommande(new Date());
            commande.setStatus("en cours");
            commande.setOrdonnance(ordonnance);
            commande.setPatient(utilisateurMetier.findPatientById(userId));

            // 3. Récupérer la pharmacie associée et lier la commande
            Pharmacie pharmacie = pharmacieMetier.findPharmacieById(pharmacyId);
            if (pharmacie == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Pharmacie non trouvée")
                        .build();
            }
            commande.setPharmacie(pharmacie);
            commandeMetier.addCommande(commande);

            // 4. Ajouter la commande à la pharmacie
            pharmacie.getMesCommandes().add(commande);
            pharmacieMetier.updatePharmacie(pharmacie);

         // Récupérer les e-mails de tous les pharmaciens
            List<Pharmacien> pharmaciens = new ArrayList<>(pharmacie.getMesPharmaciens());
            List<String> emails = new ArrayList<>();
            for (Pharmacien pharmacien : pharmaciens) {
                emails.add(pharmacien.getEmail());
            }

            // Convertir la liste des e-mails en une chaîne séparée par des virgules
            String emailAddresses = String.join(",", emails);

            // Appeler la fonction sendEmailNotification une seule fois
            commandeMetier.sendEmailNotificationToPharmaciens(
                emailAddresses,
                "Nouvelle commande reçue",
                "<p>Bonjour,</p><p>Une nouvelle commande a été reçue. Veuillez la vérifier en vous connectant à votre tableau de bord.</p><p>Cordialement,</p>"
            );


            // 6. Retourner un statut 201 Created
            return Response.status(Response.Status.CREATED)
                    .entity("Commande créée avec succès")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la création de la commande")
                    .build();
        }
    }

 
    @POST
    @Path("/{idCommande}/annuler")
    public Response annulerCommande(@PathParam("idCommande") Long idCommande) {
        boolean success = commandeMetier.annulerCommande(idCommande);
        
        if (success) {
            return Response.ok().entity("{\"message\": \"Commande annulée avec succès\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"message\": \"Erreur lors de l'annulation de la commande\"}")
                           .build();
        }
    }
    
    @GET
    public Response listCommandes(@QueryParam("idPatient") Long idPatient) {
        try {
            if (idPatient == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("L'identifiant du patient est requis")
                        .build();
            }

            // Récupérer les commandes non complètes du patient
            List<Commande> commandes = commandeMetier.listCommandesNonTraites(idPatient);

            // Convertir les commandes en une réponse JSON
            List<CommandeData> commandesData = commandes.stream()
                    .map((Commande c) -> new CommandeData(
                            c.getIdCommande(),
                            c.getDateCommande().toString(),
                            c.getStatus(),
                            c.getOrdonnance() != null ? c.getOrdonnance().getImage() : null,
                            c.getPharmacie() != null ? c.getPharmacie().getNom() : null
                    ))
                    .collect(Collectors.toList());

            return Response.ok(commandesData).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la récupération des commandes")
                    .build();
        }
    }


    @GET
    @Path("/historique")
    public Response listCommandesTraites(@QueryParam("idPatient") Long idPatient) {
        try {
            if (idPatient == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("L'identifiant du patient est requis")
                        .build();
            }

            // Récupérer les commandes non complètes du patient
            List<Commande> commandes = commandeMetier.listCommandesTraites(idPatient);

            // Convertir les commandes en une réponse JSON
            List<CommandeData> commandesData = commandes.stream()
                    .map((Commande c) -> new CommandeData(
                            c.getIdCommande(),
                            c.getDateCommande().toString(),
                            c.getStatus(),
                            c.getOrdonnance() != null ? c.getOrdonnance().getImage() : null,
                            c.getPharmacie() != null ? c.getPharmacie().getNom() : null
                    ))
                    .collect(Collectors.toList());

            return Response.ok(commandesData).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la récupération des commandes")
                    .build();
        }
    }

    @DELETE
    @Path("delete")
    public Response deleteCommande(@QueryParam("idCommande") Long idCommande) {
        if (idCommande == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("L'identifiant de la commande est requis.")
                           .build();
        }
        boolean isDeleted = commandeMetier.deleteCommande(idCommande);
        if (isDeleted) {
            return Response.ok("Commande supprimée avec succès.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Commande introuvable.")
                           .build();
        }
    }
    
    
    @GET
    @Path("/details")
    public Response getCommandeDetails(@QueryParam("idCommande") Long idCommande) {
        if (idCommande == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("L'identifiant de la commande est requis")
                    .build();
        }
        
        try {
            Commande commande = commandeMetier.getCommandeDetails(idCommande);
            
            if (commande == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Commande introuvable pour l'id : " + idCommande)
                        .build();
            }
            
            // Create the CommandeDetails object using the Commande entity
            CommandeDetails commandeDetails = new CommandeDetails(commande);
            
            return Response.ok(commandeDetails).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de la récupération des détails de la commande : " + e.getMessage())
                    .build();
        }
    }


}

