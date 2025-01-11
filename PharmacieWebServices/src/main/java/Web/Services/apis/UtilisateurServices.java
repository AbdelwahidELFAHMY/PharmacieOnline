package Web.Services.apis;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Web.Services.JsonSerialisation.AdminData;
import Web.Services.JsonSerialisation.PatientData;
import Web.Services.JsonSerialisation.PharmacienData;
import metier.UtilisateurEJB.IUtilisateurLocal;
import metier.entities.Admin;
import metier.entities.Patient;
import metier.entities.Pharmacien;

@Stateless
@Path("/utilisateurs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UtilisateurServices {

    @Inject
    private IUtilisateurLocal utilisateurMetier;
    
    @Path("/patient")
    @POST
    public Response registerPatient(Patient patient) {
        try {
            utilisateurMetier.addPatient(patient);

            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Votre Compte a été créé avec succès\"}")
                           .build();
        } catch (Exception e) {
        	e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\": \"" + e.getMessage() + "\"}")
                           .build();
        }
    }
 
 
    @Path("/pharmacien")
    @POST
    public Response registerPharmacien(Pharmacien pharmacien,@QueryParam("idPharmacie")Long idPharmacie) {
        try {
            utilisateurMetier.addPharmacien(pharmacien,Long.valueOf(idPharmacie));

            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Votre Compte a été créé avec succès\"}")
                           .build();
        } catch (Exception e) {
        	e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\": \"" + e.getMessage() + "\"}")
                           .build();
        }
    }
 
    @Path("/admin")
    @POST
    public Response registerAdmin(Admin admin) {
        try {
        	
            utilisateurMetier.addAdmin(admin);

            return Response.status(Response.Status.CREATED)
                           .entity("{\"message\": \"Votre Compte a été créé avec succès\"}")
                           .build();
        } catch (Exception e) {
        	e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\": \"" + e.getMessage() + "\"}")
                           .build();
        }
    }
    

    @Path("/login")
    @GET
    public Response recupererUtilisateur(@QueryParam("email") String email, @QueryParam("motPasse") String motPasse, @QueryParam("role") String role) {
        try {
        	if(role.toLowerCase().equals("patient")) {
        		return recupererPatient(email,motPasse);
        	}else if(role.toLowerCase().equals("pharmacien")){
        		return recupererPharmacien(email, motPasse);
        	}else if(role.toLowerCase().equals("admin")) {
        		return recupererAdmin(email, motPasse);
        	}else {
        		 return Response.status(Response.Status.BAD_REQUEST)
                         .entity("{\"error\": \"Rôle incorrect. Veuillez spécifier un rôle valide (patient, pharmacien, admin).\"}")
                         .build();
        	}
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    
    @Path("/patient")
    @PUT
    public Response modifierPatient(PatientData patient) {
        try {
            if (patient == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"le Corps de la requette est vide\"}")
                        .build();
            }

            // Appeler la logique métier
            Patient patientToUpdate = utilisateurMetier.findPatientById(patient.getId_utilisateur());
            patientToUpdate.setAdresse(patient.getAdresse());
            patientToUpdate.setEmail(patient.getEmail());
            patientToUpdate.setImage(patient.getImage());
            patientToUpdate.setNom(patient.getNom());
            patientToUpdate.setPrenom(patient.getPrenom());
            patientToUpdate.setTelephone(patient.getTelephone());
            
            utilisateurMetier.updatePatient(patientToUpdate);

            return Response.ok("{\"message\": \"La modification a ete faite avec succès\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    
    
    @Path("/pharmacien")
    @PUT
    public Response modifierPharmacien(PharmacienData pharmacien) {
        try {
            if (pharmacien == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"le Corps de la requette est vide\"}")
                        .build();
            }

            // Appeler la logique métier
            Pharmacien pharmacienToUpdate = utilisateurMetier.findPharmacienById(pharmacien.getId_utilisateur());
            pharmacienToUpdate.setAdresse(pharmacien.getAdresse());
            pharmacienToUpdate.setEmail(pharmacien.getEmail());
            pharmacienToUpdate.setImage(pharmacien.getImage());
            pharmacienToUpdate.setNom(pharmacien.getNom());
            pharmacienToUpdate.setPrenom(pharmacien.getPrenom());
            pharmacienToUpdate.setTelephone(pharmacien.getTelephone());
            
            utilisateurMetier.updatePharmacien(pharmacienToUpdate);
            
            return Response.ok("{\"message\": \"La modification a ete faite avec succès\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    @Path("/admin")
    @PUT
    public Response modifierAdmin(AdminData admin) {
        try {
            if (admin == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"le Corps de la requette est vide\"}")
                        .build();
            }

            // Appeler la logique métier
            Admin adminToUpdate = utilisateurMetier.findAdminById(admin.getId_utilisateur());
            adminToUpdate.setAdresse(admin.getAdresse());
            adminToUpdate.setEmail(admin.getEmail());
            adminToUpdate.setImage(admin.getImage());
            adminToUpdate.setNom(admin.getNom());
            adminToUpdate.setPrenom(admin.getPrenom());
            adminToUpdate.setTelephone(admin.getTelephone());
            
            utilisateurMetier.updateAdmin(adminToUpdate);
            
            return Response.ok("{\"message\": \"La modification a ete faite avec succès\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    
    private Response recupererPatient(String email, String motPasse) {
        try {
            Patient pat = utilisateurMetier.getPatient(email,motPasse);

            if (pat == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Compte n'existe pas\"}")
                        .build();
            }
            
            PatientData patientJson=new PatientData(pat.getId_utilisateur(),pat.getNom(),pat.getPrenom(),
            		pat.getEmail(),pat.getTelephone(),pat.getImage(),pat.getAdresse());

            return Response.ok(patientJson).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

   private Response recupererPharmacien(String email, String motPasse) {
    try {
        Pharmacien ph = utilisateurMetier.getPharmacien(email, motPasse);
        
        
        if (ph == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Compte n'existe pas\"}")
                    .build();
        }
        if (!ph.getStatus().equals("actif")) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"error\": \"Votre compte a été restreint. Veuillez contacter votre administrateur.\"}")
                    .build();
        }
        
        PharmacienData pharmacienJson=new PharmacienData(ph.getId_utilisateur(),ph.getNom(), ph.getPrenom(), ph.getEmail()
        		, ph.getTelephone(), ph.getImage(), ph.getAdresse(), ph.getStatus());

        return Response.ok(pharmacienJson).build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"" + e.getMessage() + "\"}")
                .build();
    }
}

    private Response recupererAdmin(String email, String motPasse) {
        try {
            Admin admin = utilisateurMetier.getAdmin(email, motPasse);

            if (admin == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Compte n'existe pas\"}")
                        .build();
            }
            
            AdminData adminJson=new AdminData(admin.getId_utilisateur(),admin.getNom(),admin.getPrenom(),admin.getEmail(),
            		admin.getTelephone(),admin.getImage(),admin.getAdresse());

            return Response.ok(adminJson).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

}

