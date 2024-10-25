package groupe.EJB;

import groupe.models.CD;
import groupe.models.Emprunt;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class CDController {

    @EJB
    private GestionCDService cdService;

    private List<CD> cdsDisponibles;
    private List<Emprunt> empruntsUtilisateur;

    private String utilisateur = "user123"; // Remplacez par une authentification réelle

    @PostConstruct
    public void init() {
        cdsDisponibles = cdService.getCDDisponibles();
        empruntsUtilisateur = cdService.getEmpruntsUtilisateur(utilisateur);
    }

    public void emprunterCD(Long cdId) {
        cdService.emprunterCD(cdId, utilisateur);
        cdsDisponibles = cdService.getCDDisponibles();
        empruntsUtilisateur = cdService.getEmpruntsUtilisateur(utilisateur);
    }

    public void retournerCD(Long empruntId) {
        cdService.retournerCD(empruntId);
        cdsDisponibles = cdService.getCDDisponibles();
        empruntsUtilisateur = cdService.getEmpruntsUtilisateur(utilisateur);
    }

    public List<CD> getCdsDisponibles() {
        return cdsDisponibles;
    }

    public List<Emprunt> getEmpruntsUtilisateur() {
        return empruntsUtilisateur;
    }
}
