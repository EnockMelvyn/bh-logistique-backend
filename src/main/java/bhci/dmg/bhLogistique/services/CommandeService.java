package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Commande;
import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.dao.DemandeArticle;
import bhci.dmg.bhLogistique.dao.StatutDemande;
import bhci.dmg.bhLogistique.repository.CommandeRepository;
import bhci.dmg.bhLogistique.repository.DemandeRepository;
import bhci.dmg.bhLogistique.repository.StatusRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log
@Service
public class CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    StatusRepository statusRepository;

    public List<Commande> getAllDemandes(){
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long idCommande){
       return commandeRepository.findById(idCommande).orElseThrow(() ->
               new IllegalStateException(" La demande id:" + idCommande+" n'existe pas"));
    }

    public Commande getCommandeByNumber(String numeroCommande){
        return commandeRepository.findByNumeroCommande(numeroCommande).orElseThrow(() ->
                new IllegalStateException(" La demande n°:" + numeroCommande+" n'existe pas"));
    }

    public Commande updateCommande (Long idCommande, Commande commande){
        Commande commande1 = commandeRepository.findById(idCommande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idCommande+" n'existe pas"));

        commande1.setStatus(commande.getStatus());

        return commandeRepository.save(commande1);
    }
/*
    public Demande validateCommande (Long idDemande ){
        Demande demande1 = demandeRepository.findById(idDemande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idDemande+" n'existe pas"));

        demande1.setStatutCommande(StatutDemande.VALIDEE);

        return demandeRepository.save(demande1);
    }

    public Commande refuseCommande (Long idCommande){
        Demande demande1 = commandeRepository.findById(idCommande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idCommande+" n'existe pas"));

        demande1.setStatutDemande(StatutDemande.REFUSEE);

        return demandeRepository.save(demande1);
    }
*/
    public Commande createCommande(Commande commande){
        Commande newCommande = new Commande();

        log.info("-- Création d'une nouvelle commande ! -- ");
       /* newDemande.setDateDemande(demande.getDateDemande());
        newDemande.setDemandeur(demande.getDemandeur());
        newDemande.setStatutDemande(demande.getStatutDemande());
        newDemande.setEstimation(demande.getEstimation());
        newDemande.setUrgent(demande.isUrgent());
        newDemande.setJustifUrgence(demande.getJustifUrgence());
        newDemande.setNumRef(demande.getNumRef());
        newDemande.setObservation(demande.getObservation());
//        newDemande.setDemandeArticles(demande.getDemandeArticles());
        newDemande.setDemandeArticles(new ArrayList<DemandeArticle>())  ;
        for(DemandeArticle demandeArticle: demande.getDemandeArticles()){
            DemandeArticle newDemArticle = new DemandeArticle(demandeArticle.getQuantite(), demandeArticle.getDemande(), demandeArticle.getArticle());
            newDemande.addArticles(newDemArticle);
        }
//        System.out.println(newDemande.getDemandeArticles().toString());*/
        log.info("-- Création d'une nouvelle commande ! -- ");

        if(commande == null) {
            throw  new IllegalStateException(" La commande n'pas été renseignée ");
        }
        if(commande.getNumeroCommande() == null) {
            throw  new IllegalStateException(" Le numéro de la commande est obligatoire ");
        }
        if(commande.getDateCommande() == null) {
            commande.setDateCommande(LocalDate.now());
        }

        if(commande.getCommandeDetails() == null || commande.getCommandeDetails().isEmpty())
            throw new IllegalStateException(" la commande ne contient aucun article ");

        commande.setStatus(statusRepository.findByCodeStatut("ATT"));

        return commandeRepository.save(commande);
    }
}
