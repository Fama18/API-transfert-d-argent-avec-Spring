package com.Transfert.controller;

import com.Transfert.entities.Emetteur;
import com.Transfert.repos.EmetteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/TransfertArgent")
public class EmetteurController {

    @Autowired
    private EmetteurRepository emetteurRepository;

    @GetMapping("/emetteurs")
    public List<Emetteur> getAllEmetteurs() {
        return emetteurRepository.findAll();
    }

    @GetMapping("/emetteurs/{id}")
    public ResponseEntity<Emetteur> getEmetteurById(@PathVariable(value = "id") int emetteurId)
            throws ResourceNotFoundException {
        Emetteur emetteur = emetteurRepository.findById(emetteurId)
                .orElseThrow(() -> new ResourceNotFoundException("Emetteur not found for this id :: " + emetteurId));
        return ResponseEntity.ok().body(emetteur);
    }

    @PostMapping("/emetteurs")
    public Emetteur createEmetteur(@ModelAttribute("emetteur")  Emetteur emetteur) {
        return emetteurRepository.save(emetteur);
    }

    @PutMapping("/emetteurs/{id}")
    public ResponseEntity<Emetteur> updateEmetteur(@PathVariable(value = "id") int emetteurId,
                                             @Validated @RequestBody Emetteur emetteurDetails) throws ResourceNotFoundException {
        Emetteur emetteur = emetteurRepository.findById(emetteurId)
                .orElseThrow(() -> new ResourceNotFoundException("Emetteur not found for this id :: " + emetteurId));

        emetteur.setNumCni(emetteurDetails.getNumCni());
        emetteur.setNom(emetteurDetails.getNom());
        emetteur.setPrenom(emetteurDetails.getPrenom());
        emetteur.setTelephone(emetteurDetails.getTelephone());

        final Emetteur updatedEmetteur = emetteurRepository.save(emetteur);
        return ResponseEntity.ok(updatedEmetteur);
    }

    @DeleteMapping("/emetteurs/{id}")
    public Map<String, Boolean> deleteEmetteur(@PathVariable(value = "id") int emetteurId)
            throws ResourceNotFoundException {
        Emetteur emetteur = emetteurRepository.findById(emetteurId)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found for this id :: " + emetteurId));

        emetteurRepository.delete(emetteur);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
