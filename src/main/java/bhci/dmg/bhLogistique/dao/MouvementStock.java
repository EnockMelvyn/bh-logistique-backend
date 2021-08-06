package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mouvement_stock")
@Data
public class MouvementStock {

    private Long idMouvementStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    @JsonIgnore
    private Article article;
    private String reference;
    private int stockInitial;
    private int qteMouvement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    @JsonIgnore
    private Fournisseur fournisseur;
    private LocalDate dateMouvement;
    private String username;
    private Double prixUnitaire;
}
