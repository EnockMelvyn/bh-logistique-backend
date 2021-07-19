package bhci.dmg.bhLogistique.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Demande")
@Data
@NoArgsConstructor
public class Demande implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_demande", nullable = false)
    private Long idDemande;

    @Column(name = "num_ref")
    private String numRef;

    @Column(name = "estimation")
    private int estimation;

    @Column(name = "observations")
    private String observation;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Column(name = "demandeur")
    private String demandeur;

    @Column(name = "statut")
    private StatutDemande statutDemande;

    @Column(name = "urgent")
    private boolean urgent;

    @Column(name = "justif_urgence")
    private String justifUrgence;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

}