package fr.pierre.medheadUser.service;

import fr.pierre.medheadUser.model.dto.UtilisateurDto;
import fr.pierre.medheadUser.model.entity.UtilisateurEntity;
import fr.pierre.medheadUser.repository.UtilisateurRepository;
import fr.pierre.medheadUser.service.connexion.DefaultConnexionService;
import fr.pierre.medheadUser.utils.MockAuthentication;
import fr.pierre.medheadUser.utils.MockGrantedAuthority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ConnexionServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private DefaultConnexionService connexionService;

    @Test
    public void register_expectToBeOk_ReturnUtilisateurEntity() {
        // Given
        final String MAIL = "mail@test.fr";
        final String MOT_DE_PASSE = "motdepasse";
        final UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .mail(MAIL)
                .motdepasse(MOT_DE_PASSE)
                .build();
        final UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(1);
        utilisateurEntity.setMail(MAIL);
        utilisateurEntity.setMotDePasse(MOT_DE_PASSE);
        Mockito
                .when(utilisateurRepository.save(Mockito.any(UtilisateurEntity.class)))
                .thenReturn(utilisateurEntity);
        Mockito
                .when(passwordEncoder.encode(MOT_DE_PASSE))
                .thenReturn(MOT_DE_PASSE);

        // When
        UtilisateurEntity response = connexionService.register(utilisateurDto);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getId());
        Assertions.assertEquals(MAIL, response.getMail());
        Assertions.assertEquals(MOT_DE_PASSE, response.getMotDePasse());
    }

    @Test
    public void authenticate_expectToBeOk_ReturnUtilisateurEntity() {
        // Given
        final String MAIL = "mail@test.fr";
        final String MOT_DE_PASSE = "motdepasse";
        final UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .mail(MAIL)
                .motdepasse(MOT_DE_PASSE)
                .build();
        final UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(1);
        utilisateurEntity.setMail(MAIL);
        utilisateurEntity.setMotDePasse(MOT_DE_PASSE);
        final Authentication authentication = MockAuthentication.builder()
                .name("nameTest")
                .authenticated(true)
                .authorities(Collections.singletonList(new MockGrantedAuthority("authority")))
                .build();
        Mockito
                .when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        Mockito
                .when(utilisateurRepository.findByMail(MAIL))
                .thenReturn(Optional.of(utilisateurEntity));

        // When
        UtilisateurEntity response = connexionService.authenticate(utilisateurDto);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getId());
        Assertions.assertEquals(MAIL, response.getMail());
        Assertions.assertEquals(MOT_DE_PASSE, response.getMotDePasse());
    }
}
