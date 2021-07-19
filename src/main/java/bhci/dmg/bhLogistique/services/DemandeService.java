package bhci.dmg.bhLogistique.services;

import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {

    @Autowired
    DemandeRepository demandeRepository;

    public List<Demande> getAllDemandes(){
        return demandeRepository.findAll();
    }

    public Demande getDemandeById(Long idDemande){
       return demandeRepository.findById(idDemande).orElseThrow(() ->
               new IllegalStateException(" La demande id:" + idDemande+" n'existe pas"));
    }

    public Demande getDemandeByReference(String numRefDemande){
        return demandeRepository.findByNumRef(numRefDemande).orElseThrow(() ->
                new IllegalStateException(" La demande n°:" + numRefDemande+" n'existe pas"));
    }

    public Demande updateDemande (Long idDemande, Demande demande){
        Demande demande1 = demandeRepository.findById(idDemande).orElseThrow(() ->
                new IllegalStateException(" L'id famille:" + idDemande+" n'existe pas"));

        demande1.setStatutDemande(demande.getStatutDemande());

        return demandeRepository.save(demande1);
    }

    public Demande createDemande(Demande demande){
        return demandeRepository.save(demande);
    }
}
