package fr.pierre.medheadUser.controller;

import fr.pierre.medheadUser.model.dto.InformationConnexion;
import fr.pierre.medheadUser.model.dto.UtilisateurDto;
import fr.pierre.medheadUser.model.entity.UtilisateurEntity;
import fr.pierre.medheadUser.service.connexion.api.ConnexionService;
import fr.pierre.medheadUser.service.jwtoken.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UtilisateurController {

    private final JwtService jwtService;
    private final ConnexionService connexionService;

    @PostMapping("/auth/subscribe")
    public ResponseEntity<UtilisateurEntity> inscription(@RequestBody UtilisateurDto connexionUtilisateurDto) {
        return ResponseEntity.ok(connexionService.register(connexionUtilisateurDto));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<InformationConnexion> connexion(@RequestBody UtilisateurDto connexionUtilisateurDto) {
        UtilisateurEntity utilisateur = connexionService.authenticate(connexionUtilisateurDto);
        String jwtToken = jwtService.generateToken(utilisateur);
        InformationConnexion informationConnexion = InformationConnexion
                .builder()
                .token(jwtToken)
                .expiresDans(jwtService.getExpirationTime())
                .mail(utilisateur.getMail())
                .build();
        return ResponseEntity.ok(informationConnexion);
    }
}
