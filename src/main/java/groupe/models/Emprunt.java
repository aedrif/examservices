package groupe.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Setter
@Getter
@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CD cd;

    private String utilisateur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    // Getters et Setters
}

