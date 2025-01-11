package metier.UtilisateurEJB;

import org.mindrot.jbcrypt.BCrypt;

public class TestBCrypt {
    public static void main(String[] args) {
        String password = "MonMotDePasse";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Haché : " + hashed);

        boolean match = BCrypt.checkpw(password, hashed);
        System.out.println("Correspondance : " + match);
    }
}
