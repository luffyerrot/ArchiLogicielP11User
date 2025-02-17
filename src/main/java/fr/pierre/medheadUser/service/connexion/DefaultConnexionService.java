package fr.pierre.medheadUser.service.connexion;

import fr.pierre.medheadUser.model.dto.UtilisateurDto;
import fr.pierre.medheadUser.model.entity.UtilisateurEntity;
import fr.pierre.medheadUser.repository.UtilisateurRepository;
import fr.pierre.medheadUser.service.connexion.api.ConnexionService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultConnexionService implements ConnexionService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UtilisateurEntity register(UtilisateurDto input) {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setMail(input.getMail());
        utilisateur.setMotDePasse(passwordEncoder.encode(input.getMotdepasse()));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public UtilisateurEntity authenticate(UtilisateurDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getMail(),
                        input.getMotdepasse()
                )
        );

        return utilisateurRepository
                .findByMail(input.getMail())
                .orElseThrow();
    }
}
