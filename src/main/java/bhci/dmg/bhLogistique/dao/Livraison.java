package bhci.dmg.bhLogistique.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_livraison")
@Data
public class Livraison implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "date_livraison")
    private LocalDateTime dateLivraison;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseurId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livraison", nullable = false)
    private Long idLivraison;

    @Column(name = "numero_bl")
    private String numeroBl;

    @OneToMany(mappedBy = "livraison",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<LivraisonDetail> livraisonDetails;

    public void addLivraisonDetail(LivraisonDetail livDet) {
        livraisonDetails.add(livDet);
        livDet.setLivraison(this);
    }

    public void removeLivraisonDetail(LivraisonDetail livDet) {
        livraisonDetails.remove(livDet);
        livDet.setLivraison(null);
    }
}