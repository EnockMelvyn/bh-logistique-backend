package bhci.dmg.bhLogistique.dao;

import bhci.dmg.bhLogistique.enums.TypeMouvement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "t_mouvement_stock")
@Data
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mouvement_stock", nullable = false)
    private Long idMouvementStock;

    private LocalDate dateMouvement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    @JsonIgnore
    private Article article;
    private double stockInitial;
//    private double qteMouvement;
//    private TypeMouvement typeMouvement;
    private double entree;
    private double sortie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    @JsonIgnore
    private Fournisseur fournisseur;

    private String username;
    private String demandeur;
    private Double prixUnitaire;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    public MouvementStock() {
    }

    public MouvementStock(LocalDate dateMouvement, Article article, double stockInitial, double entree, double sortie, Fournisseur fournisseur, Double prixUnitaire) {
        this.dateMouvement = dateMouvement;
        this.article = article;
        this.stockInitial = stockInitial;
        this.entree = entree;
        this.sortie = sortie;
        this.fournisseur = fournisseur;
        this.prixUnitaire = prixUnitaire;
    }
}
