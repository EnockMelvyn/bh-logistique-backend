package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.DemandeArticle;
import bhci.dmg.bhLogistique.dao.Livraison;
import bhci.dmg.bhLogistique.dao.LivraisonDetail;
import bhci.dmg.bhLogistique.repository.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivraisonService {

    @Autowired
    LivraisonRepository livraisonRepository;

    public List<Livraison> getAllLivraisons() {
        List<Livraison> livraisons= new ArrayList<Livraison>();
        livraisonRepository.findAll().forEach(livraisons::add);
        return livraisons;
    }


    public Livraison getLivraisonById(Long idLivraison){
        return  livraisonRepository.findById(idLivraison).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idLivraison +" n'existe pas")
        );
    }

    public Livraison getLivraisonByNumeroBl(String numBl){
        return  livraisonRepository.findByNumeroBl(numBl).orElseThrow(() ->
                new IllegalStateException(" Le n° de BL:" + numBl +" n'existe pas")
        );
    }

    public Livraison deleteLivraisonById(Long idLivraison){
        return  livraisonRepository.findById(idLivraison).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idLivraison +" n'existe pas")
        );
    }

    public Livraison updateLivraison(Long idLivraison, Livraison livraison){
        Livraison livraisonToUpdate = livraisonRepository.findById(idLivraison).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idLivraison +" n'existe pas")
        );


        livraisonToUpdate.setFournisseurId(livraison.getFournisseurId());
        livraisonToUpdate.setDateLivraison(livraison.getDateLivraison());
        livraisonToUpdate.setNumeroBl(livraison.getNumeroBl());

        return livraisonRepository.save(livraisonToUpdate);
    }

    public Livraison createLivraison(Livraison livraison) {
        boolean livraisonExists = livraisonRepository.findByNumeroBl(livraison.getNumeroBl()).isPresent();

        if (livraisonExists) {
            throw new IllegalStateException("Cette livraison a déjà été enregistrée");
        }

        Livraison livraison1 = new Livraison();

        livraison1.setDateLivraison(livraison.getDateLivraison());
        livraison1.setNumeroBl(livraison.getNumeroBl());
        livraison1.setFournisseurId(livraison.getFournisseurId());
        livraison1.setLivraisonDetails(new ArrayList<>());
        for(LivraisonDetail livraisonDetail: livraison.getLivraisonDetails()){
            LivraisonDetail newLivDet = new LivraisonDetail();
            newLivDet.setLivraison(livraisonDetail.getLivraison());
            newLivDet.setArticle(livraisonDetail.getArticle());
            newLivDet.setQuantite(livraisonDetail.getQuantite());
            newLivDet.setPrixUnitaire(livraisonDetail.getPrixUnitaire());

            livraison1.addLivraisonDetail(newLivDet);
        }
        return livraisonRepository.save(livraison);
    }
}
