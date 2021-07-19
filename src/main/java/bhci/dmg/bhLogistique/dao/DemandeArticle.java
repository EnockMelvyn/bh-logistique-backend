package bhci.dmg.bhLogistique.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "demande_article")
@Data
public class DemandeArticle implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id_demande_article", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDemandeArticle;

    @Column(name = "quantite")
    private int quantite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demande_id")
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    public DemandeArticle() {
    }

    public DemandeArticle(int quantite, Demande demande, Article article) {
        this.quantite = quantite;
        this.demande = demande;
        this.article = article;
    }
}
