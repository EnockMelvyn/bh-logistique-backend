package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_livraison_detail")
@Data
public class LivraisonDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livraison_detail", nullable = false)
    private Long idLivraisonDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livraison_id")
    private Livraison livraison;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "quantite")
    private Integer quantite;

}
