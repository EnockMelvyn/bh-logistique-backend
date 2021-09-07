package bhci.dmg.bhLogistique.controllers;

import bhci.dmg.bhLogistique.dao.Commande;
import bhci.dmg.bhLogistique.dao.Demande;
import bhci.dmg.bhLogistique.services.CommandeService;
import bhci.dmg.bhLogistique.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @GetMapping
    public ResponseEntity<List<Commande>> getAllDemandes(@RequestParam(required = false) String numeroCommande) {

        try {
            List<Commande> commandes = new ArrayList<Commande>();
            if (numeroCommande == null) {
                commandes = commandeService.getAllDemandes();
            }
            else {
                commandes.add(commandeService.getCommandeByNumber(numeroCommande));
            }
            if (commandes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idCommande}")
    public ResponseEntity<Commande> getDemandeById(@PathVariable Long idCommande){
        Commande commande = new Commande();
        commande = commandeService.getCommandeById(idCommande);

        if (commande == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        return new ResponseEntity<>(commandeService.createCommande(commande), HttpStatus.CREATED);
    }
/*
    @PutMapping("/{idDemande}")
    public ResponseEntity<Commande> updateDemande(@PathVariable Long idDemande, @RequestBody Commande demande) {
        return new ResponseEntity<>(commandeService.updateCommande(idDemande, demande), HttpStatus.OK);
    }

    @PutMapping("/validate/{idDemande}")
    public ResponseEntity<Demande> validateOrRefuseDemande(@PathVariable Long idDemande) {
        return new ResponseEntity<>(commandeService.validateDemande(idDemande), HttpStatus.OK);
    }

    @PutMapping("/refuse/{idDemande}")
    public ResponseEntity<Demande> refuseDemande(@PathVariable Long idDemande) {
        return new ResponseEntity<>(commandeService.refuseDemande(idDemande), HttpStatus.OK);
    }*/
}
