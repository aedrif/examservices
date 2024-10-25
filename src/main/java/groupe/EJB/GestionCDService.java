package groupe.EJB;

import groupe.models.CD;
import groupe.models.Emprunt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
@Stateless
public class GestionCDService {

    @PersistenceContext
    private EntityManager em;

    public List<CD> getCDDisponibles() {
        return em.createQuery("SELECT cd FROM CD cd WHERE cd.disponible = true", CD.class).getResultList();
    }

    public void emprunterCD(Long cdId, String utilisateur) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.getDisponible()) {
            cd.setDisponible(false);
            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setUtilisateur(utilisateur);
            emprunt.setDateEmprunt(LocalDate.now());
            em.persist(emprunt);
        }
    }

    public List<Emprunt> getEmpruntsUtilisateur(String utilisateur) {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.utilisateur = :utilisateur AND e.dateRetour IS NULL", Emprunt.class)
                .setParameter("utilisateur", utilisateur)
                .getResultList();
    }

    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null && emprunt.getDateRetour() == null) {
            emprunt.setDateRetour(LocalDate.now());
            emprunt.getCd().setDisponible(true);
            em.merge(emprunt);
        }
    }
}

