package metier.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Utilisateurs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;
	
	@Column(length = 100, nullable = false)
    private String nom;
	
	@Column(length = 100, nullable = false)
    private String prenom;
	
    @Column(unique = true, nullable = false)
    private String email;

	@Column(length = 100, nullable = false)
	private String motpasse;

	@Column(length = 100, nullable = false)
    private String telephone;
	
	@Column(length = 250, nullable = true)
    private String image;
	
	@Column(length = 100, nullable = false)
    private String adresse;

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(Long id, String nom, String prenom, String email, String password, String telephone,
			String adresse, String image) {
		super();
		this.id_utilisateur = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motpasse = password;
		this.telephone = telephone;
		this.adresse = adresse;
		this.image=image;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMotpasse() {
		return motpasse;
	}

	public void setMotpasse(String motpasse) {
		this.motpasse = motpasse;
	}

	public Long getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(Long id) {
		this.id_utilisateur = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}    
}
