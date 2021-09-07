package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Article;
import bhci.dmg.bhLogistique.dao.Sortie;
import bhci.dmg.bhLogistique.repository.ArticleRepository;
import bhci.dmg.bhLogistique.repository.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortieService {


    @Autowired
    SortieRepository sortieRepository;

    public List<Sortie> getAllSorties() {
        /*List<Sortie> sorties= new ArrayList<Sortie>();
        sortieRepository.findAll().forEach(sorties::add);*/
        return sortieRepository.findAll();
    }

    public Sortie getSortieById(Long idSortie){
        return  sortieRepository.findById(idSortie).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idSortie +" n'existe pas")
        );
    }

    public Sortie getSortieByReference(String reference){
        return  sortieRepository.findByReference(reference).orElseThrow(() ->
                new IllegalStateException(" La reference:" + reference +" n'existe pas")
        );
    }

    public Sortie deleteSortieById(Long idSortie){
        return  sortieRepository.findById(idSortie).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idSortie +" n'existe pas")
        );
    }


    public Sortie createSortie(Sortie sortie) {

        return sortieRepository.save(sortie);
    }
}
