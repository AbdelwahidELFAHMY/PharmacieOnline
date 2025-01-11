package metier.CommandeEJB;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Commande;

@Stateless
public class CommandeEJBImp implements ICommandeLocal {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void addCommande(Commande cmd) {
		if (cmd == null) {
            throw new IllegalArgumentException("La Commande ne peut pas être null");
        }
        try {
            em.persist(cmd);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout de la commande : ", e);
        }
		
	}

	@Override
	public boolean deleteCommande(Long idCommande) {
	    try {
	        Commande commande = em.find(Commande.class, idCommande);
	        if (commande != null) {
	            em.remove(commande);
	            return true;
	        }
	        return false;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public void updateCommande(Commande cmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Commande> getCommandes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void sendEmailNotificationToPharmaciens(String to, String subject, String body) {
    try {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abdelwahidfhmy@gmail.com", "yjvt blgt eswp bpfx");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("abdelwahidfhmy@gmail.com"));
        //BCC Pour masquer les e-mails des destinataires entre eux
        message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to));
        message.setSubject(subject);

        // Contenu HTML de l'email
        String htmlContent = 
        	    "<html>" +
        	    "<head>" +
        	    "<style>" +
        	    "body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; }" +
        	    ".email-container { max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }" +
        	    ".logo { text-align: center; margin-bottom: 20px; }" +
        	    ".svg-container { height: 56px; width: auto; margin-top: 16px; display: flex; justify-content: center; }" +
        	    ".content { font-size: 16px; color: #333; line-height: 1.6; }" +
        	    ".footer { text-align: center; font-size: 12px; color: #777; margin-top: 20px; }" +
        	    "</style>" +
        	    "</head>" +
        	    "<body>" +
        	    "<div class='email-container'>" +
        	    "<div class='logo'>" +
        	    "<div class='svg-container'>" +
        	    "<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 400 200'>" +
        	    "<g>" +
        	    "<text x='50%' y='50%' font-family='Arial, sans-serif' font-size='70' text-anchor='middle' alignment-baseline='middle' fill='white'>Pharmacie</text>" +
        	    "<text x='50%' y='74%' font-family='Arial, sans-serif' font-size='52' text-anchor='middle' font-weight='bold' alignment-baseline='middle' fill='green'>Online</text>" +
        	    "</g>" +
        	    "</svg>" +
        	    "</div>" +
        	    "</div>" +
        	    "<div class='content'>%s</div>" +
        	    "<div class='footer'>" +
        	    "<p>Pharmacie Online - Merci pour votre confiance</p>" +
        	    "</div>" +
        	    "</body>" +
        	    "</html>";



        // Insert the body content directly into the HTML template
        String formattedHtmlContent = htmlContent.replace("%s", body);

        // Set the content type to HTML
        message.setContent(formattedHtmlContent, "text/html; charset=utf-8");

        Transport.send(message);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	
	
	@Override
	public List<Commande> listCommandesNonTraites(Long idPatient) {
	    if (idPatient == null) {
	        throw new IllegalArgumentException("L'identifiant du patient ne peut pas être null");
	    }
	    try {
	        return em.createQuery(
	                "SELECT c FROM Commande c " +
	                "JOIN FETCH c.ordonnance o " +
	                "WHERE c.patient.id_utilisateur = :idPatient", 
	                Commande.class)
	                .setParameter("idPatient", idPatient)
	                .getResultList();
	    } catch (Exception e) {
	        throw new RuntimeException("Erreur lors de la récupération des commandes : ", e);
	    }
	}

	@Override
    public boolean annulerCommande(Long idCommande) {
	        try {
	            Commande commande = em.find(Commande.class, idCommande);
	            
	            if (commande != null) {
	                commande.setStatus("annulé");
	                
	                em.merge(commande);
	                
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	  
	@Override
	public List<Commande> listCommandesTraites(Long idPatient) {
		    if (idPatient == null) {
		        throw new IllegalArgumentException("L'identifiant du patient ne peut pas être null");
		    }
		    try {
		        return em.createQuery(
		                "SELECT c FROM Commande c " +
		                "JOIN FETCH c.ordonnance o " +
		                "WHERE c.patient.id_utilisateur = :idPatient " +
		                "AND c.status IN (:statuses)", 
		                Commande.class)
		                .setParameter("idPatient", idPatient)
		                .setParameter("statuses", Arrays.asList("prête", "annulé")) 
		                .getResultList();
		    } catch (Exception e) {
		        throw new RuntimeException("Erreur lors de la récupération des commandes : ", e);
		    }
		}

	@Override
	public Commande getCommandeDetails(Long idCommande) {
		    if (idCommande == null) {
		        throw new IllegalArgumentException("L'identifiant de la commande ne peut pas être null");
		    }
		    try {
		        Commande commande = em.find(Commande.class, idCommande);
		        
		        // You can add additional logic if needed, for example:
		        if (commande == null) {
		            throw new RuntimeException("Commande introuvable pour l'id : " + idCommande);
		        }
		        
		        // Make sure to initialize associated entities if lazy loading is used
		        if (commande.getOrdonnance() != null) {
		            commande.getOrdonnance().getImage(); // Force lazy loading for example
		        }
		        if (commande.getPatient() != null) {
		            commande.getPatient().getId_utilisateur(); // Fetch patient details if needed
		        }
		        if (commande.getPharmacie() != null) {
		            commande.getPharmacie().getIdPharmacie(); // Fetch pharmacy details if needed
		        }
		        
		        return commande;
		    } catch (Exception e) {
		        throw new RuntimeException("Erreur lors de la récupération des détails de la commande : ", e);
		    }
		}

	
	@Override
	public void sendEmailNotificationToPatient(String to, String subject, String body) {
	    try {
	        // Set up email properties
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        // Create a session with authentication
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("abdelwahidfhmy@gmail.com", "yjvt blgt eswp bpfx");
	            }
	        });

	        // Create a message object
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("abdelwahidfhmy@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  // Send to patient directly
	        message.setSubject(subject);

	        // HTML content for the email
	        String htmlContent = 
	            "<html>" +
	            "<head>" +
	            "<style>" +
	            "body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; }" +
	            ".email-container { max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }" +
	            ".logo { text-align: center; margin-bottom: 20px; }" +
	            ".content { font-size: 16px; color: #333; line-height: 1.6; }" +
	            ".footer { text-align: center; font-size: 12px; color: #777; margin-top: 20px; }" +
	            "</style>" +
	            "</head>" +
	            "<body>" +
	            "<div class='email-container'>" +
	            "<div class='logo'>" +
	            "<h2>Pharmacie Online</h2>" +
	            "</div>" +
	            "<div class='content'>%s</div>" +
	            "<div class='footer'>" +
	            "<p>Pharmacie Online - Merci pour votre confiance</p>" +
	            "</div>" +
	            "</body>" +
	            "</html>";

	        // Format the body message and replace placeholder
	        String formattedHtmlContent = String.format(htmlContent, body);

	        // Set the content type to HTML
	        message.setContent(formattedHtmlContent, "text/html; charset=utf-8");

	        // Send the email
	        Transport.send(message);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public long getTotalOrders() {
	    String jpql = "SELECT COUNT(c) FROM Commande c";
	    Query query = em.createQuery(jpql);
	    return (long) query.getSingleResult();
	}

		

}
